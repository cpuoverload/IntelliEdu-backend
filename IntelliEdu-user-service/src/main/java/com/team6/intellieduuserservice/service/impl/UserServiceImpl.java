package com.team6.intellieduuserservice.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team6.intellieduuserservice.constant.Constant;
import com.team6.intellieduuserservice.mapper.UserMapper;
import com.team6.intelliedumodel.dto.user.ListUserRequest;
import com.team6.intelliedumodel.entity.User;
import com.team6.intelliedumodel.vo.UserVo;
import com.team6.intellieduuserservice.service.UserService;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.Err;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 在 insert/update 时校验参数
     * @param user 封装参数
     * @param isUpdate 是否是更新操作
     */
    public void validate(User user, boolean isUpdate) {
        if (user == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Long id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String nickname = user.getNickname();
        String avatar = user.getAvatar();
        String role = user.getRole();

        if (isUpdate) {
            if (id == null) {
                throw new BusinessException(Err.PARAMS_ERROR, "用户 id 为空");
            }
            if (StringUtils.isAllBlank(password, nickname, avatar, role)) {
                throw new BusinessException(Err.PARAMS_ERROR, "没有填写要更新的内容");
            }
        } else {
            if (StringUtils.isAnyBlank(username, password)) {
                throw new BusinessException(Err.PARAMS_ERROR, "用户名、密码不能为空");
            }
        }

        if (StringUtils.isNotBlank(username) && username.length() < 6) {
            throw new BusinessException(Err.PARAMS_ERROR, "用户名长度少于 6 位");
        }
        if (StringUtils.isNotBlank(password) && password.length() < 8) {
            throw new BusinessException(Err.PARAMS_ERROR, "密码长度少于 8 位");
        }
        if (role != null && !role.equals("user") && !role.equals("admin")) {
            throw new BusinessException(Err.PARAMS_ERROR, "角色不合法");
        }
    }

    /**
     * 登录校验
     * @param username
     * @param password
     */
    public void validateLogin(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(Err.PARAMS_ERROR, "用户名、密码不能为空");
        }
        if (username.length() < 6) {
            throw new BusinessException(Err.PARAMS_ERROR, "用户名长度少于 6 位");
        }
        if (password.length() < 8) {
            throw new BusinessException(Err.PARAMS_ERROR, "密码长度少于 8 位");
        }
    }

    /**
     * entity 转换为 vo
     * @param user
     * @return
     */
    public UserVo entityToVo(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public Long getLoginUserId(HttpServletRequest request) {
        UserVo userVo = (UserVo) request.getSession().getAttribute(Constant.LOGIN_USER);
        return userVo.getId();
    }

    @Override
    public Long register(User user) {
        return addUser(user);
    }

    @Override
    public UserVo login(String username, String password, HttpServletRequest request) {
        // 1. 校验参数
        validateLogin(username, password);
        // 2. 判断用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(Err.USER_NOT_FOUND);
        }
        // 3. 判断密码是否正确
        boolean isPwdCorrect = DigestUtil.bcryptCheck(password, user.getPassword());
        if (!isPwdCorrect) {
            throw new BusinessException(Err.PASSWORD_ERROR);
        }
        // 4. 保存用户登录状态到 session
        UserVo userVo = entityToVo(user);
        request.getSession().setAttribute(Constant.LOGIN_USER, userVo);
        // 5. 返回用户信息
        return userVo;
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.LOGIN_USER);
        return true;
    }

    @Override
    public UserVo getMyInfo(HttpServletRequest request) {
        User user = this.getById(getLoginUserId(request));
        return entityToVo(user);
    }

    @Override
    public boolean updateMyInfo(User user) {
        return updateUser(user);
    }

    @Override
    public UserVo getUserById(Long id) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException(Err.USER_NOT_FOUND);
        }
        return entityToVo(user);
    }

    @Override
    public Page<UserVo> listUser(ListUserRequest listUserRequest) {
        Long current = listUserRequest.getCurrent();
        Long pageSize = listUserRequest.getPageSize();
        IPage<User> page = new Page<>(current, pageSize);

        // 由于不是所有字段都是精确查询，有的字段需要模糊查询，有的字段需要排序，所以不能简单地写成 new QueryWrapper(entity)
        // sortField 是动态传入的列名，无法使用 LambdaQueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(listUserRequest.getId() != null, "id", listUserRequest.getId())
                .like(StringUtils.isNotBlank(listUserRequest.getUsername()), "username", listUserRequest.getUsername())
                .like(StringUtils.isNotBlank(listUserRequest.getNickname()), "nickname", listUserRequest.getNickname())
                .eq(listUserRequest.getRole() != null, "role", listUserRequest.getRole())
                .orderBy(listUserRequest.getSortField() != null, listUserRequest.getIsAscend(), StrUtil.toUnderlineCase(listUserRequest.getSortField()));

        IPage<User> userPage = this.page(page, queryWrapper);

        Page<UserVo> userVoPage = new Page<>(current, pageSize, userPage.getTotal());
        List<UserVo> voRecords = userPage.getRecords().stream().map(this::entityToVo).collect(Collectors.toList());
        userVoPage.setRecords(voRecords);

        return userVoPage;
    }

    @Override
    public Long addUser(User user) {
        // 1. 校验参数
        validate(user, false);
        // 2. 判断 username 是否重复
        String username = user.getUsername();
        synchronized (username.intern()) {
            long count = this.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
            // 2.1 若重复，注册失败
            if (count > 0) {
                throw new BusinessException(Err.DUPLICATED_USERNAME_ERROR);
            }
            // 2.2 若不重复，插入记录
            // 密码加密
            user.setPassword(DigestUtil.bcrypt(user.getPassword()));
            boolean success = this.save(user);
            // 3. 返回 userId
            if (!success) {
                throw new BusinessException(Err.SYSTEM_ERROR, "注册失败");
            }
            return user.getId();
        }
    }

    @Override
    public boolean updateUser(User user) {
        // 1. 校验参数
        validate(user, true);
        // 2. 加密密码
        String password = user.getPassword();
        if (password != null) {
            user.setPassword(DigestUtil.bcrypt(password));
        }
        // 3. 更新记录
        return this.updateById(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return this.removeById(id);
    }
}



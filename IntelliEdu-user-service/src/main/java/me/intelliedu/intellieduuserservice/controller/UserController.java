package me.intelliedu.intellieduuserservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.intelliedu.intellieduuserservice.config.RequiresAdmin;
import me.intelliedu.intellieduuserservice.config.RequiresLogin;
import me.intelliedu.intelliedumodel.dto.user.*;
import me.intelliedu.intelliedumodel.entity.User;
import me.intelliedu.intelliedumodel.vo.UserVo;
import me.intelliedu.intellieduuserservice.service.UserService;
import me.intelliedu.intellieducommon.utils.ApiResponse;
import me.intelliedu.intellieducommon.utils.BusinessException;
import me.intelliedu.intellieducommon.utils.Err;
import me.intelliedu.intellieducommon.utils.IdRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<Long> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        Long userId = userService.register(user);
        return ApiResponse.success(userId);
    }

    @PostMapping("/login")
    public ApiResponse<UserVo> login(@RequestBody RegisterRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        if (username == null || password == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        UserVo userVo = userService.login(username, password, request);
        return ApiResponse.success(userVo);
    }

    @PostMapping("/logout")
    public ApiResponse<Boolean> logout(HttpServletRequest request) {
        userService.logout(request);
        return ApiResponse.success(true);
    }

    @GetMapping("/get/me")
    @RequiresLogin
    public ApiResponse<UserVo> getMyInfo(HttpServletRequest request) {
        UserVo userVo = userService.getMyInfo(request);
        return ApiResponse.success(userVo);
    }

    @PostMapping("/update/me")
    @RequiresLogin
    public ApiResponse<Boolean> updateMyInfo(@RequestBody UpdateMyInfoRequest updateMyInfoRequest,
                                             HttpServletRequest request) {
        if (updateMyInfoRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(updateMyInfoRequest, user);
        user.setId(userService.getLoginUserId(request));
        boolean success = userService.updateMyInfo(user);
        if (!success) {
            throw new BusinessException(Err.UPDATE_ERROR);
        }
        return ApiResponse.success(true);
    }

    @GetMapping("/get/{id}")
    @RequiresAdmin
    public ApiResponse<UserVo> getUserById(@PathVariable Long id) {
        if (id == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        UserVo userVo = userService.getUserById(id);
        return ApiResponse.success(userVo);
    }

    @PostMapping("/list")
    @RequiresAdmin
    public ApiResponse<Page<UserVo>> listUser(@RequestBody ListUserRequest listUserRequest) {
        if (listUserRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        Page<UserVo> userVoPage = userService.listUser(listUserRequest);
        return ApiResponse.success(userVoPage);
    }

    @PostMapping("/add")
    @RequiresAdmin
    public ApiResponse<Long> addUser(@RequestBody AddUserRequest addUserRequest) {
        if (addUserRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        if (addUserRequest.getUsername() == null || addUserRequest.getPassword() == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(addUserRequest, user);
        Long userId = userService.addUser(user);
        return ApiResponse.success(userId);
    }

    @PostMapping("/update")
    @RequiresAdmin
    public ApiResponse<Boolean> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        if (updateUserRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(updateUserRequest, user);
        boolean success = userService.updateUser(user);
        if (!success) {
            throw new BusinessException(Err.UPDATE_ERROR);
        }
        return ApiResponse.success(true);
    }

    @PostMapping("/delete")
    @RequiresAdmin
    public ApiResponse<Boolean> deleteUser(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(Err.PARAMS_ERROR);
        }
        boolean success = userService.deleteUser(idRequest.getId());
        if (!success) {
            throw new BusinessException(Err.DELETE_ERROR);
        }
        return ApiResponse.success(true);
    }

}

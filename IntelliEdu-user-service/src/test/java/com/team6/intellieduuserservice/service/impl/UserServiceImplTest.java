package com.team6.intellieduuserservice.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieduuserservice.constant.Constant;
import com.team6.intellieduuserservice.mapper.UserMapper;
import com.team6.intellieduuserservice.model.dto.user.ListRequest;
import com.team6.intellieduuserservice.model.entity.User;
import com.team6.intellieduuserservice.model.vo.UserVo;
import com.team6.intellieduuserservice.utils.BusinessException;
import com.team6.intellieduuserservice.utils.Err;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Spy
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);

        // 设置 baseMapper 字段
        ReflectionTestUtils.setField(userService, "baseMapper", userMapper);
    }

    // ------------------ Tests for validate(User user, boolean isUpdate) ------------------

    @Test
    void testValidate_NullUser() {
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(null, false));
        assertEquals(Err.PARAMS_ERROR.getCode(), exception.getCode());
    }

    @Test
    void testValidate_CreateUser_EmptyUsernameOrPassword() {
        User user = new User();
        user.setUsername("");
        user.setPassword("");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, false));
        assertEquals("用户名、密码不能为空", exception.getMessage());
    }

    @Test
    void testValidate_CreateUser_ShortUsername() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, false));
        assertEquals("用户名长度少于 6 位", exception.getMessage());
    }

    @Test
    void testValidate_CreateUser_ShortPassword() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, false));
        assertEquals("密码长度少于 8 位", exception.getMessage());
    }

    @Test
    void testValidate_CreateUser_InvalidRole() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password123");
        user.setRole("invalid");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, false));
        assertEquals("角色不合法", exception.getMessage());
    }

    @Test
    void testValidate_UpdateUser_NullId() {
        User user = new User();
        user.setPassword("newpassword123");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, true));
        assertEquals("用户 id 为空", exception.getMessage());
    }

    @Test
    void testValidate_UpdateUser_NoContent() {
        User user = new User();
        user.setId(1L);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, true));
        assertEquals("没有填写要更新的内容", exception.getMessage());
    }

    @Test
    void testValidate_UpdateUser_InvalidRole() {
        User user = new User();
        user.setId(1L);
        user.setRole("invalid");
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validate(user, true));
        assertEquals("角色不合法", exception.getMessage());
    }

    @Test
    void testValidate_ValidCreateUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password123");
        user.setRole("user");
        assertDoesNotThrow(() -> userService.validate(user, false));
    }

    @Test
    void testValidate_ValidUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("newpassword123");
        user.setRole("admin");
        assertDoesNotThrow(() -> userService.validate(user, true));
    }

    // ------------------ Tests for validateLogin(String username, String password) ------------------

    @Test
    void testValidateLogin_BlankUsernameOrPassword() {
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validateLogin("", ""));
        assertEquals("用户名、密码不能为空", exception.getMessage());
    }

    @Test
    void testValidateLogin_ShortUsername() {
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validateLogin("user", "password123"));
        assertEquals("用户名长度少于 6 位", exception.getMessage());
    }

    @Test
    void testValidateLogin_ShortPassword() {
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.validateLogin("username", "pass"));
        assertEquals("密码长度少于 8 位", exception.getMessage());
    }

    @Test
    void testValidateLogin_ValidCredentials() {
        assertDoesNotThrow(() -> userService.validateLogin("username", "password123"));
    }

    // ------------------ Tests for login(String username, String password, HttpServletRequest request) ------------------

    @Test
    void testLogin_UserNotFound() {
        when(userMapper.selectOne(any())).thenReturn(null);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.login("username", "password123", request));
        assertEquals(Err.USER_NOT_FOUND.getCode(), exception.getCode());
    }


    /**
     * 测试登录成功的情况
     */
    @Test
    public void testLogin_Success() {
        // 准备数据
        String username = "testUser";
        String password = "testPassword";

        // 模拟数据库返回的用户
        User user = new User();
        user.setUsername(username);
        user.setPassword("hashedPassword"); // 数据库中存储的加密密码

        // 模拟 getOne() 方法返回用户
        doReturn(user).when(userService).getOne(any(LambdaQueryWrapper.class));

        // 模拟 bcryptCheck 方法返回 true
        try (MockedStatic<DigestUtil> mockedDigestUtil = Mockito.mockStatic(DigestUtil.class)) {
            mockedDigestUtil.when(() -> DigestUtil.bcryptCheck(password, "hashedPassword")).thenReturn(true);

            // 执行方法
            UserVo result = userService.login(username, password, request);

            // 验证结果
            assertNotNull(result);
            verify(session).setAttribute(eq(Constant.LOGIN_USER), any(UserVo.class));
        }
    }


    /**
     * 测试密码错误的情况
     */
    @Test
    public void testLogin_PasswordIncorrect() {
        // 准备数据
        String username = "testUser";
        String password = "wrongPassword";

        // 模拟数据库返回的用户
        User user = new User();
        user.setUsername(username);
        user.setPassword("hashedPassword"); // 数据库中存储的加密密码

        // 模拟 getOne() 方法返回用户
        doReturn(user).when(userService).getOne(any(LambdaQueryWrapper.class));

        // 模拟 bcryptCheck 方法返回 false
        try (MockedStatic<DigestUtil> mockedDigestUtil = Mockito.mockStatic(DigestUtil.class)) {
            mockedDigestUtil.when(() -> DigestUtil.bcryptCheck(password, "hashedPassword")).thenReturn(false);

            // 执行方法并期望抛出异常
            try {
                userService.login(username, password, request);
                fail("应当抛出 BusinessException 异常");
            } catch (BusinessException e) {
                assertEquals(Err.PASSWORD_ERROR.getCode(), e.getCode());
            }
        }
    }


    // ------------------ Tests for register(User user) ------------------

    @Test
    void testRegister_DuplicateUsername() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password123");

        when(userMapper.selectCount(any())).thenReturn(1L);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.register(user));
        assertEquals(Err.DUPLICATED_USERNAME_ERROR.getCode(), exception.getCode());
    }

    @Test
    void testRegister_Success() {
        // Arrange
        User user = new User();
        user.setUsername("username");
        user.setPassword("password123");

        // 模拟 selectCount 方法返回 0，表示用户名不重复
        when(userMapper.selectCount(any())).thenReturn(0L);

        // 模拟插入成功，并手动设置用户 ID，模拟数据库生成 ID 的场景
        doAnswer(invocation -> {
            User userArg = invocation.getArgument(0);
            userArg.setId(1L); // 模拟插入后数据库生成的用户 ID
            return 1; // 模拟返回插入成功
        }).when(userMapper).insert(any(User.class));

        // Act
        Long userId = userService.register(user);

        // Assert
        assertNotNull(userId, "User ID should not be null after successful registration");
        assertEquals(1L, userId, "User ID should be 1");
        verify(userMapper, times(1)).insert(user); // 验证插入操作是否被调用
    }

    @Test
    void testRegister_Failure_SaveError() {
        // Arrange
        User user = new User();
        user.setUsername("username");
        user.setPassword("password123");

        // 模拟 selectCount 方法返回 0，表示用户名不重复
        when(userMapper.selectCount(any())).thenReturn(0L);

        // 模拟保存失败，insert 方法返回 0
        when(userMapper.insert(any(User.class))).thenReturn(0);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.register(user));
        assertEquals(Err.SYSTEM_ERROR.getCode(), exception.getCode(), "Should throw SYSTEM_ERROR when save fails");
        verify(userMapper, times(1)).selectCount(any()); // 验证查询是否执行
        verify(userMapper, times(1)).insert(user); // 验证插入是否执行
    }


    // ------------------ Tests for getUserById(Long id) ------------------

    @Test
    void testGetUserById_UserIsNull() {
        when(userMapper.selectById(anyLong())).thenReturn(null);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.getUserById(1L));
        assertEquals(Err.USER_NOT_FOUND.getCode(), exception.getCode());
    }

    @Test
    void testGetUserById_Successful() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");

        when(userMapper.selectById(anyLong())).thenReturn(user);
        UserVo userVo = userService.getUserById(1L);

        assertNotNull(userVo);
        assertEquals(user.getId(), userVo.getId());
    }

    // ------------------ Tests for updateUser(User user) ------------------
    @Test
    void testUpdateUser_PasswordNotNull() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("newPassword123");
        user.setNickname("NewNick");

        // 模拟 updateUser 方法返回 true
        when(userMapper.updateById(user)).thenReturn(1);

        // Act
        boolean result = userService.updateUser(user);

        // Assert
        assertTrue(result, "User should be updated successfully");
        verify(userMapper, times(1)).updateById(user);
    }

    @Test
    void testUpdateUser_PasswordIsNull() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword(null);  // password 为 null
        user.setNickname("NewNick");

        // 模拟 updateUser 方法返回 true
        when(userMapper.updateById(user)).thenReturn(1);

        // Act
        boolean result = userService.updateUser(user);

        // Assert
        assertTrue(result, "User should be updated successfully even when password is null");
        verify(userMapper, times(1)).updateById(user);
    }


    // ------------------ Tests for deleteUser(Long id) ------------------
    @Test
    void testDeleteUser() {
        Long userId = 1L;

        // Mock the removeById method to return true
        doReturn(true).when(userService).removeById(userId);

        // Call the deleteUser method
        boolean result = userService.deleteUser(userId);

        // Verify the result and that removeById was called
        assertTrue(result);
        verify(userService, times(1)).removeById(userId);
    }


    // ------------------ Tests for listUser(ListRequest listRequest) ------------------

    @Test
    void testListUser_Success() {
        ListRequest listRequest = new ListRequest();
        listRequest.setCurrent(1L);
        listRequest.setPageSize(10L);

        User user = new User();
        user.setId(1L);
        user.setUsername("username");

        IPage<User> page = new Page<>();
        page.setRecords(Collections.singletonList(user));

        when(userMapper.selectPage(any(), any())).thenReturn(page);

        List<UserVo> userVoList = userService.listUser(listRequest);

        assertNotNull(userVoList);
        assertEquals(1, userVoList.size());
        assertEquals(user.getId(), userVoList.get(0).getId());
    }

    // ------------------ Tests for logout(HttpServletRequest request) ------------------

    @Test
    void testLogout() {
        boolean result = userService.logout(request);

        assertTrue(result);
        verify(session).removeAttribute(Constant.LOGIN_USER);
    }

    // ------------------ Tests for getMyInfo(HttpServletRequest request) ------------------

    @Test
    void testGetMyInfo_Successful() {
        UserVo userVo = new UserVo();
        userVo.setId(1L);

        when(session.getAttribute(Constant.LOGIN_USER)).thenReturn(userVo);

        User user = new User();
        user.setId(1L);
        user.setUsername("username");

        when(userMapper.selectById(anyLong())).thenReturn(user);

        UserVo result = userService.getMyInfo(request);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    // ------------------ Tests for getLoginUserId(HttpServletRequest request) ------------------

    @Test
    void testGetLoginUserId_Success() {
        UserVo userVo = new UserVo();
        userVo.setId(1L);

        when(session.getAttribute(Constant.LOGIN_USER)).thenReturn(userVo);

        Long userId = userService.getLoginUserId(request);

        assertEquals(1L, userId);
    }

    // ------------------ Tests for entityToVo(User user) ------------------

    @Test
    void testEntityToVo() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");

        UserVo userVo = userService.entityToVo(user);

        assertNotNull(userVo);
        assertEquals(user.getId(), userVo.getId());
        assertEquals(user.getUsername(), userVo.getUsername());
    }


    @Test
    void testUpdateMyInfo_Failure_ValidationException() {
        // Arrange
        User invalidUser = new User();  // invalid user with no fields set
        doThrow(new BusinessException(Err.PARAMS_ERROR)).when(userService).updateUser(invalidUser);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.updateMyInfo(invalidUser));

        assertEquals(Err.PARAMS_ERROR.getCode(), exception.getCode());
        verify(userService, times(1)).updateUser(invalidUser);
    }
}
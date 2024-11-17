package me.intelliedu.intellieduuserservice.controller;

import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.intelliedu.intellieducommon.utils.IdRequest;
import me.intelliedu.intelliedumodel.dto.user.AddUserRequest;
import me.intelliedu.intelliedumodel.dto.user.ListUserRequest;
import me.intelliedu.intelliedumodel.dto.user.RegisterRequest;
import me.intelliedu.intelliedumodel.dto.user.UpdateMyInfoRequest;
import me.intelliedu.intelliedumodel.dto.user.UpdateUserRequest;
import me.intelliedu.intelliedumodel.entity.User;
import me.intelliedu.intelliedumodel.vo.UserVo;
import me.intelliedu.intellieduuserservice.service.UserService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Test {@link UserController#register(RegisterRequest)}.
     * <p>
     * Method under test: {@link UserController#register(RegisterRequest)}
     */
    @Test
    @DisplayName("Test register(RegisterRequest)")
    void testRegister() throws Exception {
        // Arrange
        when(userService.register(Mockito.<User>any())).thenReturn(1L);

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("iloveyou");
        registerRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":1,\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#login(RegisterRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link UserController#login(RegisterRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(RegisterRequest, HttpServletRequest)")
    void testLogin() throws Exception {
        // Arrange
        UserVo userVo = new UserVo();
        userVo.setAvatar("Avatar");
        userVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setId(1L);
        userVo.setNickname("Nickname");
        userVo.setRole("Role");
        userVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setUsername("janedoe");
        when(userService.login(Mockito.<String>any(), Mockito.<String>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(userVo);

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("iloveyou");
        registerRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"username\":\"janedoe\",\"nickname\":\"Nickname\",\"avatar\":\"Avatar\",\"role\":\"Role\","
                                        + "\"createTime\":0,\"updateTime\":0},\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#logout(HttpServletRequest)}.
     * <p>
     * Method under test: {@link UserController#logout(HttpServletRequest)}
     */
    @Test
    @DisplayName("Test logout(HttpServletRequest)")
    void testLogout() throws Exception {
        // Arrange
        when(userService.logout(Mockito.<HttpServletRequest>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/logout");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#getMyInfo(HttpServletRequest)}.
     * <p>
     * Method under test: {@link UserController#getMyInfo(HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyInfo(HttpServletRequest)")
    void testGetMyInfo() throws Exception {
        // Arrange
        UserVo userVo = new UserVo();
        userVo.setAvatar("Avatar");
        userVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setId(1L);
        userVo.setNickname("Nickname");
        userVo.setRole("Role");
        userVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setUsername("janedoe");
        when(userService.getMyInfo(Mockito.<HttpServletRequest>any())).thenReturn(userVo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get/me");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"username\":\"janedoe\",\"nickname\":\"Nickname\",\"avatar\":\"Avatar\",\"role\":\"Role\","
                                        + "\"createTime\":0,\"updateTime\":0},\"message\":\"success\"}"));
    }

    /**
     * Test
     * {@link UserController#updateMyInfo(UpdateMyInfoRequest, HttpServletRequest)}.
     * <p>
     * Method under test:
     * {@link UserController#updateMyInfo(UpdateMyInfoRequest, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test updateMyInfo(UpdateMyInfoRequest, HttpServletRequest)")
    void testUpdateMyInfo() throws Exception {
        // Arrange
        when(userService.updateMyInfo(Mockito.<User>any())).thenReturn(true);
        when(userService.getLoginUserId(Mockito.<HttpServletRequest>any())).thenReturn(1L);

        UpdateMyInfoRequest updateMyInfoRequest = new UpdateMyInfoRequest();
        updateMyInfoRequest.setAvatar("Avatar");
        updateMyInfoRequest.setNickname("Nickname");
        updateMyInfoRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(updateMyInfoRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#getUserById(Long)}.
     * <p>
     * Method under test: {@link UserController#getUserById(Long)}
     */
    @Test
    @DisplayName("Test getUserById(Long)")
    void testGetUserById() throws Exception {
        // Arrange
        UserVo userVo = new UserVo();
        userVo.setAvatar("Avatar");
        userVo.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setId(1L);
        userVo.setNickname("Nickname");
        userVo.setRole("Role");
        userVo.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userVo.setUsername("janedoe");
        when(userService.getUserById(Mockito.<Long>any())).thenReturn(userVo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get/42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"username\":\"janedoe\",\"nickname\":\"Nickname\",\"avatar\":\"Avatar\",\"role\":\"Role\","
                                        + "\"createTime\":0,\"updateTime\":0},\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#listUser(ListUserRequest)}.
     * <p>
     * Method under test: {@link UserController#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest)")
    void testListUser() throws Exception {
        // Arrange
        when(userService.listUser(Mockito.<ListUserRequest>any())).thenReturn(new Page<>());

        ListUserRequest listUserRequest = new ListUserRequest();
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(listUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"records\":[],\"total\":0,\"size\":10,\"current\":1,\"orders\":[],\"optimizeCountSql\":true,"
                                        + "\"searchCount\":true,\"maxLimit\":null,\"countId\":null,\"pages\":0},\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#addUser(AddUserRequest)}.
     * <p>
     * Method under test: {@link UserController#addUser(AddUserRequest)}
     */
    @Test
    @DisplayName("Test addUser(AddUserRequest)")
    void testAddUser() throws Exception {
        // Arrange
        when(userService.addUser(Mockito.<User>any())).thenReturn(1L);

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setAvatar("Avatar");
        addUserRequest.setNickname("Nickname");
        addUserRequest.setPassword("iloveyou");
        addUserRequest.setRole("Role");
        addUserRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(addUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":1,\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#updateUser(UpdateUserRequest)}.
     * <p>
     * Method under test: {@link UserController#updateUser(UpdateUserRequest)}
     */
    @Test
    @DisplayName("Test updateUser(UpdateUserRequest)")
    void testUpdateUser() throws Exception {
        // Arrange
        when(userService.updateUser(Mockito.<User>any())).thenReturn(true);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setAvatar("Avatar");
        updateUserRequest.setId(1L);
        updateUserRequest.setNickname("Nickname");
        updateUserRequest.setPassword("iloveyou");
        updateUserRequest.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(updateUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }

    /**
     * Test {@link UserController#deleteUser(IdRequest)}.
     * <p>
     * Method under test: {@link UserController#deleteUser(IdRequest)}
     */
    @Test
    @DisplayName("Test deleteUser(IdRequest)")
    void testDeleteUser() throws Exception {
        // Arrange
        when(userService.deleteUser(Mockito.<Long>any())).thenReturn(true);

        IdRequest idRequest = new IdRequest();
        idRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(idRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":true,\"message\":\"success\"}"));
    }
}

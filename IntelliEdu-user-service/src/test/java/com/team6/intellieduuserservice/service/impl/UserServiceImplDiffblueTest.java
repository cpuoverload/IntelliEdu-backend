package com.team6.intellieduuserservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team6.intellieducommon.utils.BusinessException;
import com.team6.intellieducommon.utils.TableRequest;
import com.team6.intelliedumodel.dto.user.ListUserRequest;
import com.team6.intelliedumodel.entity.User;
import com.team6.intelliedumodel.vo.UserVo;
import com.team6.intellieduuserservice.mapper.UserMapper;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplDiffblueTest {
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Test {@link UserServiceImpl#entityToVo(User)}.
     * <p>
     * Method under test: {@link UserServiceImpl#entityToVo(User)}
     */
    @Test
    @DisplayName("Test entityToVo(User)")
    void testEntityToVo() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act
        UserVo actualEntityToVoResult = userServiceImpl.entityToVo(user);

        // Assert
        assertEquals("Avatar", actualEntityToVoResult.getAvatar());
        assertEquals("Nickname", actualEntityToVoResult.getNickname());
        assertEquals("Role", actualEntityToVoResult.getRole());
        assertEquals("janedoe", actualEntityToVoResult.getUsername());
        assertEquals(1L, actualEntityToVoResult.getId().longValue());
    }

    /**
     * Test {@link UserServiceImpl#getLoginUserId(HttpServletRequest)}.
     * <ul>
     *   <li>When {@link MockHttpServletRequest#MockHttpServletRequest()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#getLoginUserId(HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getLoginUserId(HttpServletRequest); when MockHttpServletRequest()")
    @Disabled("TODO: Complete this test")
    void testGetLoginUserId_whenMockHttpServletRequest() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.team6.intellieduuserservice.service.impl.UserServiceImpl.getLoginUserId(UserServiceImpl.java:102)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        userServiceImpl.getLoginUserId(new MockHttpServletRequest());
    }

    /**
     * Test {@link UserServiceImpl#register(User)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link User} {@link User#getPassword()} return empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    @DisplayName("Test register(User); given empty string; when User getPassword() return empty string")
    void testRegister_givenEmptyString_whenUserGetPasswordReturnEmptyString() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.register(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#register(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getPassword()} return {@code foo}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    @DisplayName("Test register(User); given 'foo'; when User getPassword() return 'foo'; then calls getAvatar()")
    void testRegister_givenFoo_whenUserGetPasswordReturnFoo_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("foo");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.register(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#register(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getPassword()} return
     * {@code iloveyou}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    @DisplayName("Test register(User); given 'foo'; when User getPassword() return 'iloveyou'; then calls getAvatar()")
    void testRegister_givenFoo_whenUserGetPasswordReturnIloveyou_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("foo");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.register(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#register(User)}.
     * <ul>
     *   <li>When {@link User} (default constructor) Avatar is {@code Avatar}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    @DisplayName("Test register(User); when User (default constructor) Avatar is 'Avatar'")
    void testRegister_whenUserAvatarIsAvatar() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.register(user));
    }

    /**
     * Test {@link UserServiceImpl#login(String, String, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link User} (default constructor) Avatar is {@code Avatar}.</li>
     *   <li>When {@code janedoe}.</li>
     *   <li>Then calls {@link BaseMapper#selectOne(Wrapper, boolean)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link UserServiceImpl#login(String, String, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(String, String, HttpServletRequest); given User (default constructor) Avatar is 'Avatar'; when 'janedoe'; then calls selectOne(Wrapper, boolean)")
    void testLogin_givenUserAvatarIsAvatar_whenJanedoe_thenCallsSelectOne() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");
        when(userMapper.selectOne(Mockito.<Wrapper<User>>any(), anyBoolean())).thenReturn(user);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> userServiceImpl.login("janedoe", "iloveyou", new MockHttpServletRequest()));
        verify(userMapper).selectOne(isA(Wrapper.class), eq(true));
    }

    /**
     * Test {@link UserServiceImpl#login(String, String, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link User} {@link User#getPassword()} return empty string.</li>
     *   <li>When {@code janedoe}.</li>
     *   <li>Then calls {@link User#getPassword()}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link UserServiceImpl#login(String, String, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(String, String, HttpServletRequest); given User getPassword() return empty string; when 'janedoe'; then calls getPassword()")
    void testLogin_givenUserGetPasswordReturnEmptyString_whenJanedoe_thenCallsGetPassword() {
        // Arrange
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");
        when(userMapper.selectOne(Mockito.<Wrapper<User>>any(), anyBoolean())).thenReturn(user);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> userServiceImpl.login("janedoe", "iloveyou", new MockHttpServletRequest()));
        verify(userMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(user).getPassword();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#login(String, String, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link User} {@link User#setAvatar(String)} does nothing.</li>
     *   <li>When {@code 42}.</li>
     *   <li>Then calls {@link User#setAvatar(String)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link UserServiceImpl#login(String, String, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(String, String, HttpServletRequest); given User setAvatar(String) does nothing; when '42'; then calls setAvatar(String)")
    void testLogin_givenUserSetAvatarDoesNothing_when42_thenCallsSetAvatar() {
        // Arrange
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.login("42", "iloveyou", new MockHttpServletRequest()));
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#login(String, String, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link User} {@link User#setAvatar(String)} does nothing.</li>
     *   <li>When {@code 42}.</li>
     *   <li>Then calls {@link User#setAvatar(String)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link UserServiceImpl#login(String, String, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(String, String, HttpServletRequest); given User setAvatar(String) does nothing; when '42'; then calls setAvatar(String)")
    void testLogin_givenUserSetAvatarDoesNothing_when42_thenCallsSetAvatar2() {
        // Arrange
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.login("janedoe", "42", new MockHttpServletRequest()));
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#login(String, String, HttpServletRequest)}.
     * <ul>
     *   <li>Given {@link User} {@link User#setAvatar(String)} does nothing.</li>
     *   <li>When empty string.</li>
     *   <li>Then calls {@link User#setAvatar(String)}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link UserServiceImpl#login(String, String, HttpServletRequest)}
     */
    @Test
    @DisplayName("Test login(String, String, HttpServletRequest); given User setAvatar(String) does nothing; when empty string; then calls setAvatar(String)")
    void testLogin_givenUserSetAvatarDoesNothing_whenEmptyString_thenCallsSetAvatar() {
        // Arrange
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.login("", "iloveyou", new MockHttpServletRequest()));
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#logout(HttpServletRequest)}.
     * <ul>
     *   <li>When {@link MockHttpServletRequest#MockHttpServletRequest()}.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#logout(HttpServletRequest)}
     */
    @Test
    @DisplayName("Test logout(HttpServletRequest); when MockHttpServletRequest(); then return 'true'")
    void testLogout_whenMockHttpServletRequest_thenReturnTrue() {
        // Arrange, Act and Assert
        assertTrue(userServiceImpl.logout(new MockHttpServletRequest()));
    }

    /**
     * Test {@link UserServiceImpl#getMyInfo(HttpServletRequest)}.
     * <ul>
     *   <li>When {@link MockHttpServletRequest#MockHttpServletRequest()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#getMyInfo(HttpServletRequest)}
     */
    @Test
    @DisplayName("Test getMyInfo(HttpServletRequest); when MockHttpServletRequest()")
    @Disabled("TODO: Complete this test")
    void testGetMyInfo_whenMockHttpServletRequest() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.team6.intellieduuserservice.service.impl.UserServiceImpl.getLoginUserId(UserServiceImpl.java:102)
        //       at com.team6.intellieduuserservice.service.impl.UserServiceImpl.getMyInfo(UserServiceImpl.java:141)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        userServiceImpl.getMyInfo(new MockHttpServletRequest());
    }

    /**
     * Test {@link UserServiceImpl#updateMyInfo(User)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link User} {@link User#getPassword()} return empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#updateMyInfo(User)}
     */
    @Test
    @DisplayName("Test updateMyInfo(User); given empty string; when User getPassword() return empty string")
    void testUpdateMyInfo_givenEmptyString_whenUserGetPasswordReturnEmptyString() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.updateMyInfo(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#updateMyInfo(User)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link User} {@link User#getUsername()} return empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#updateMyInfo(User)}
     */
    @Test
    @DisplayName("Test updateMyInfo(User); given empty string; when User getUsername() return empty string")
    void testUpdateMyInfo_givenEmptyString_whenUserGetUsernameReturnEmptyString() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.updateMyInfo(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#updateMyInfo(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getPassword()} return {@code foo}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#updateMyInfo(User)}
     */
    @Test
    @DisplayName("Test updateMyInfo(User); given 'foo'; when User getPassword() return 'foo'; then calls getAvatar()")
    void testUpdateMyInfo_givenFoo_whenUserGetPasswordReturnFoo_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("foo");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.updateMyInfo(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#updateMyInfo(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getUsername()} return {@code foo}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#updateMyInfo(User)}
     */
    @Test
    @DisplayName("Test updateMyInfo(User); given 'foo'; when User getUsername() return 'foo'; then calls getAvatar()")
    void testUpdateMyInfo_givenFoo_whenUserGetUsernameReturnFoo_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("foo");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.updateMyInfo(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#updateMyInfo(User)}.
     * <ul>
     *   <li>When {@link User} (default constructor) Avatar is {@code Avatar}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#updateMyInfo(User)}
     */
    @Test
    @DisplayName("Test updateMyInfo(User); when User (default constructor) Avatar is 'Avatar'")
    void testUpdateMyInfo_whenUserAvatarIsAvatar() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.updateMyInfo(user));
    }

    /**
     * Test {@link UserServiceImpl#getUserById(Long)}.
     * <ul>
     *   <li>Given {@link User} (default constructor) Avatar is {@code Avatar}.</li>
     *   <li>Then return {@code Avatar}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#getUserById(Long)}
     */
    @Test
    @DisplayName("Test getUserById(Long); given User (default constructor) Avatar is 'Avatar'; then return 'Avatar'")
    void testGetUserById_givenUserAvatarIsAvatar_thenReturnAvatar() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");
        when(userMapper.selectById(Mockito.<Serializable>any())).thenReturn(user);

        // Act
        UserVo actualUserById = userServiceImpl.getUserById(1L);

        // Assert
        verify(userMapper).selectById(isA(Serializable.class));
        assertEquals("Avatar", actualUserById.getAvatar());
        assertEquals("Nickname", actualUserById.getNickname());
        assertEquals("Role", actualUserById.getRole());
        assertEquals("janedoe", actualUserById.getUsername());
        assertEquals(1L, actualUserById.getId().longValue());
    }

    /**
     * Test {@link UserServiceImpl#listUser(ListUserRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link ListUserRequest} {@link ListUserRequest#getNickname()} return
     * empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest); given empty string; when ListUserRequest getNickname() return empty string")
    void testListUser_givenEmptyString_whenListUserRequestGetNicknameReturnEmptyString() {
        // Arrange
        IPage<User> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(userMapper.selectPage(Mockito.<IPage<User>>any(), Mockito.<Wrapper<User>>any())).thenReturn(iPage);
        ListUserRequest listUserRequest = mock(ListUserRequest.class);
        when(listUserRequest.getIsAscend()).thenReturn(true);
        when(listUserRequest.getCurrent()).thenReturn(1L);
        when(listUserRequest.getPageSize()).thenReturn(3L);
        when(listUserRequest.getId()).thenReturn(1L);
        when(listUserRequest.getSortField()).thenReturn("Sort Field");
        when(listUserRequest.getNickname()).thenReturn("");
        when(listUserRequest.getRole()).thenReturn("Role");
        when(listUserRequest.getUsername()).thenReturn("janedoe");
        doNothing().when(listUserRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listUserRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listUserRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listUserRequest).setSortField(Mockito.<String>any());
        doNothing().when(listUserRequest).setId(Mockito.<Long>any());
        doNothing().when(listUserRequest).setNickname(Mockito.<String>any());
        doNothing().when(listUserRequest).setRole(Mockito.<String>any());
        doNothing().when(listUserRequest).setUsername(Mockito.<String>any());
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");

        // Act
        Page<UserVo> actualListUserResult = userServiceImpl.listUser(listUserRequest);

        // Assert
        verify(userMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listUserRequest).getCurrent();
        verify(listUserRequest).getIsAscend();
        verify(listUserRequest).getPageSize();
        verify(listUserRequest, atLeast(1)).getSortField();
        verify(listUserRequest).setCurrent(eq(1L));
        verify(listUserRequest).setIsAscend(eq(true));
        verify(listUserRequest).setPageSize(eq(3L));
        verify(listUserRequest).setSortField(eq("Sort Field"));
        verify(listUserRequest, atLeast(1)).getId();
        verify(listUserRequest, atLeast(1)).getNickname();
        verify(listUserRequest, atLeast(1)).getRole();
        verify(listUserRequest, atLeast(1)).getUsername();
        verify(listUserRequest).setId(eq(1L));
        verify(listUserRequest).setNickname(eq("Nickname"));
        verify(listUserRequest).setRole(eq("Role"));
        verify(listUserRequest).setUsername(eq("janedoe"));
        assertEquals(1L, actualListUserResult.getCurrent());
        assertEquals(1L, actualListUserResult.getPages());
        assertEquals(1L, actualListUserResult.getTotal());
        assertFalse(actualListUserResult.hasPrevious());
    }

    /**
     * Test {@link UserServiceImpl#listUser(ListUserRequest)}.
     * <ul>
     *   <li>Given {@code false}.</li>
     *   <li>When {@link ListUserRequest} {@link TableRequest#getIsAscend()} return
     * {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest); given 'false'; when ListUserRequest getIsAscend() return 'false'")
    void testListUser_givenFalse_whenListUserRequestGetIsAscendReturnFalse() {
        // Arrange
        IPage<User> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(userMapper.selectPage(Mockito.<IPage<User>>any(), Mockito.<Wrapper<User>>any())).thenReturn(iPage);
        ListUserRequest listUserRequest = mock(ListUserRequest.class);
        when(listUserRequest.getIsAscend()).thenReturn(false);
        when(listUserRequest.getCurrent()).thenReturn(1L);
        when(listUserRequest.getPageSize()).thenReturn(3L);
        when(listUserRequest.getId()).thenReturn(1L);
        when(listUserRequest.getSortField()).thenReturn("Sort Field");
        when(listUserRequest.getNickname()).thenReturn("Nickname");
        when(listUserRequest.getRole()).thenReturn("Role");
        when(listUserRequest.getUsername()).thenReturn("janedoe");
        doNothing().when(listUserRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listUserRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listUserRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listUserRequest).setSortField(Mockito.<String>any());
        doNothing().when(listUserRequest).setId(Mockito.<Long>any());
        doNothing().when(listUserRequest).setNickname(Mockito.<String>any());
        doNothing().when(listUserRequest).setRole(Mockito.<String>any());
        doNothing().when(listUserRequest).setUsername(Mockito.<String>any());
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");

        // Act
        Page<UserVo> actualListUserResult = userServiceImpl.listUser(listUserRequest);

        // Assert
        verify(userMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listUserRequest).getCurrent();
        verify(listUserRequest).getIsAscend();
        verify(listUserRequest).getPageSize();
        verify(listUserRequest, atLeast(1)).getSortField();
        verify(listUserRequest).setCurrent(eq(1L));
        verify(listUserRequest).setIsAscend(eq(true));
        verify(listUserRequest).setPageSize(eq(3L));
        verify(listUserRequest).setSortField(eq("Sort Field"));
        verify(listUserRequest, atLeast(1)).getId();
        verify(listUserRequest, atLeast(1)).getNickname();
        verify(listUserRequest, atLeast(1)).getRole();
        verify(listUserRequest, atLeast(1)).getUsername();
        verify(listUserRequest).setId(eq(1L));
        verify(listUserRequest).setNickname(eq("Nickname"));
        verify(listUserRequest).setRole(eq("Role"));
        verify(listUserRequest).setUsername(eq("janedoe"));
        assertEquals(1L, actualListUserResult.getCurrent());
        assertEquals(1L, actualListUserResult.getPages());
        assertEquals(1L, actualListUserResult.getTotal());
        assertFalse(actualListUserResult.hasPrevious());
    }

    /**
     * Test {@link UserServiceImpl#listUser(ListUserRequest)}.
     * <ul>
     *   <li>Given twenty.</li>
     *   <li>Then return Current is twenty.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest); given twenty; then return Current is twenty")
    void testListUser_givenTwenty_thenReturnCurrentIsTwenty() {
        // Arrange
        IPage<User> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(userMapper.selectPage(Mockito.<IPage<User>>any(), Mockito.<Wrapper<User>>any())).thenReturn(iPage);
        ListUserRequest listUserRequest = mock(ListUserRequest.class);
        when(listUserRequest.getIsAscend()).thenReturn(true);
        when(listUserRequest.getCurrent()).thenReturn(20L);
        when(listUserRequest.getPageSize()).thenReturn(3L);
        when(listUserRequest.getId()).thenReturn(1L);
        when(listUserRequest.getSortField()).thenReturn("Sort Field");
        when(listUserRequest.getNickname()).thenReturn("Nickname");
        when(listUserRequest.getRole()).thenReturn("Role");
        when(listUserRequest.getUsername()).thenReturn("janedoe");
        doNothing().when(listUserRequest).setCurrent(Mockito.<Long>any());
        doNothing().when(listUserRequest).setIsAscend(Mockito.<Boolean>any());
        doNothing().when(listUserRequest).setPageSize(Mockito.<Long>any());
        doNothing().when(listUserRequest).setSortField(Mockito.<String>any());
        doNothing().when(listUserRequest).setId(Mockito.<Long>any());
        doNothing().when(listUserRequest).setNickname(Mockito.<String>any());
        doNothing().when(listUserRequest).setRole(Mockito.<String>any());
        doNothing().when(listUserRequest).setUsername(Mockito.<String>any());
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");

        // Act
        Page<UserVo> actualListUserResult = userServiceImpl.listUser(listUserRequest);

        // Assert
        verify(userMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        verify(listUserRequest).getCurrent();
        verify(listUserRequest).getIsAscend();
        verify(listUserRequest).getPageSize();
        verify(listUserRequest, atLeast(1)).getSortField();
        verify(listUserRequest).setCurrent(eq(1L));
        verify(listUserRequest).setIsAscend(eq(true));
        verify(listUserRequest).setPageSize(eq(3L));
        verify(listUserRequest).setSortField(eq("Sort Field"));
        verify(listUserRequest, atLeast(1)).getId();
        verify(listUserRequest, atLeast(1)).getNickname();
        verify(listUserRequest, atLeast(1)).getRole();
        verify(listUserRequest, atLeast(1)).getUsername();
        verify(listUserRequest).setId(eq(1L));
        verify(listUserRequest).setNickname(eq("Nickname"));
        verify(listUserRequest).setRole(eq("Role"));
        verify(listUserRequest).setUsername(eq("janedoe"));
        assertEquals(1L, actualListUserResult.getPages());
        assertEquals(1L, actualListUserResult.getTotal());
        assertEquals(20L, actualListUserResult.getCurrent());
        assertTrue(actualListUserResult.hasPrevious());
    }

    /**
     * Test {@link UserServiceImpl#listUser(ListUserRequest)}.
     * <ul>
     *   <li>Given {@link UserMapper} {@link BaseMapper#selectPage(IPage, Wrapper)}
     * return {@link Page#Page()}.</li>
     *   <li>Then return Pages is zero.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest); given UserMapper selectPage(IPage, Wrapper) return Page(); then return Pages is zero")
    void testListUser_givenUserMapperSelectPageReturnPage_thenReturnPagesIsZero() {
        // Arrange
        when(userMapper.selectPage(Mockito.<IPage<User>>any(), Mockito.<Wrapper<User>>any())).thenReturn(new Page<>());

        ListUserRequest listUserRequest = new ListUserRequest();
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");

        // Act
        Page<UserVo> actualListUserResult = userServiceImpl.listUser(listUserRequest);

        // Assert
        verify(userMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        assertEquals(0L, actualListUserResult.getPages());
        assertEquals(0L, actualListUserResult.getTotal());
        assertEquals(1L, actualListUserResult.getCurrent());
        assertFalse(actualListUserResult.hasPrevious());
    }

    /**
     * Test {@link UserServiceImpl#listUser(ListUserRequest)}.
     * <ul>
     *   <li>When {@link ListUserRequest} (default constructor) Current is one.</li>
     *   <li>Then return Current is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#listUser(ListUserRequest)}
     */
    @Test
    @DisplayName("Test listUser(ListUserRequest); when ListUserRequest (default constructor) Current is one; then return Current is one")
    void testListUser_whenListUserRequestCurrentIsOne_thenReturnCurrentIsOne() {
        // Arrange
        IPage<User> iPage = mock(IPage.class);
        when(iPage.getRecords()).thenReturn(new ArrayList<>());
        when(iPage.getTotal()).thenReturn(1L);
        when(userMapper.selectPage(Mockito.<IPage<User>>any(), Mockito.<Wrapper<User>>any())).thenReturn(iPage);

        ListUserRequest listUserRequest = new ListUserRequest();
        listUserRequest.setCurrent(1L);
        listUserRequest.setId(1L);
        listUserRequest.setIsAscend(true);
        listUserRequest.setNickname("Nickname");
        listUserRequest.setPageSize(3L);
        listUserRequest.setRole("Role");
        listUserRequest.setSortField("Sort Field");
        listUserRequest.setUsername("janedoe");

        // Act
        Page<UserVo> actualListUserResult = userServiceImpl.listUser(listUserRequest);

        // Assert
        verify(userMapper).selectPage(isA(IPage.class), isA(Wrapper.class));
        verify(iPage).getRecords();
        verify(iPage).getTotal();
        assertEquals(1L, actualListUserResult.getCurrent());
        assertEquals(1L, actualListUserResult.getPages());
        assertEquals(1L, actualListUserResult.getTotal());
        assertFalse(actualListUserResult.hasPrevious());
    }

    /**
     * Test {@link UserServiceImpl#addUser(User)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link User} {@link User#getPassword()} return empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    @DisplayName("Test addUser(User); given empty string; when User getPassword() return empty string")
    void testAddUser_givenEmptyString_whenUserGetPasswordReturnEmptyString() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.addUser(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#addUser(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getPassword()} return {@code foo}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    @DisplayName("Test addUser(User); given 'foo'; when User getPassword() return 'foo'; then calls getAvatar()")
    void testAddUser_givenFoo_whenUserGetPasswordReturnFoo_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("foo");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.addUser(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#addUser(User)}.
     * <ul>
     *   <li>Given {@code foo}.</li>
     *   <li>When {@link User} {@link User#getPassword()} return
     * {@code iloveyou}.</li>
     *   <li>Then calls {@link User#getAvatar()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    @DisplayName("Test addUser(User); given 'foo'; when User getPassword() return 'iloveyou'; then calls getAvatar()")
    void testAddUser_givenFoo_whenUserGetPasswordReturnIloveyou_thenCallsGetAvatar() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getAvatar()).thenReturn("Avatar");
        when(user.getNickname()).thenReturn("Nickname");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        when(user.getUsername()).thenReturn("foo");
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setCreateTime(Mockito.<Date>any());
        doNothing().when(user).setDeleted(Mockito.<Integer>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickname(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdateTime(Mockito.<Date>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.addUser(user));
        verify(user).getAvatar();
        verify(user).getId();
        verify(user).getNickname();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setAvatar(eq("Avatar"));
        verify(user).setCreateTime(isA(Date.class));
        verify(user).setDeleted(eq(1));
        verify(user).setId(eq(1L));
        verify(user).setNickname(eq("Nickname"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdateTime(isA(Date.class));
        verify(user).setUsername(eq("janedoe"));
    }

    /**
     * Test {@link UserServiceImpl#addUser(User)}.
     * <ul>
     *   <li>When {@link User} (default constructor) Avatar is {@code Avatar}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    @DisplayName("Test addUser(User); when User (default constructor) Avatar is 'Avatar'")
    void testAddUser_whenUserAvatarIsAvatar() {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setCreateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(1);
        user.setId(1L);
        user.setNickname("Nickname");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdateTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUsername("janedoe");

        // Act and Assert
        assertThrows(BusinessException.class, () -> userServiceImpl.addUser(user));
    }

    /**
     * Test {@link UserServiceImpl#deleteUser(Long)}.
     * <ul>
     *   <li>When one.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserServiceImpl#deleteUser(Long)}
     */
    @Test
    @DisplayName("Test deleteUser(Long); when one")
    @Disabled("TODO: Complete this test")
    void testDeleteUser_whenOne() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.removeById(ServiceImpl.java:290)
        //       at com.team6.intellieduuserservice.service.impl.UserServiceImpl.deleteUser(UserServiceImpl.java:225)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        userServiceImpl.deleteUser(1L);
    }
}

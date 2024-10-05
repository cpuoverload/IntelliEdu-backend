package com.team6.intellieduuserservice.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色
     */
    private String role;

    private static final long serialVersionUID = 1L;
}
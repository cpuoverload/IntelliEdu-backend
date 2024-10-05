package com.team6.intellieduuserservice.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateRequest implements Serializable {
    private static final long serialVersionUID = 5200123986732432652L;

    /**
     * id
     */
    private Long id;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private String role;
}

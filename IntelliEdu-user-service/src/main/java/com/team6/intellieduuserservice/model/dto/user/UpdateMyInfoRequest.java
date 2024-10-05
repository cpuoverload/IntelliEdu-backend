package com.team6.intellieduuserservice.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateMyInfoRequest implements Serializable {
    private static final long serialVersionUID = 5200123986732432653L;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;
}

package com.team6.intellieduuserservice.model.dto.user;

import com.team6.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListRequest extends TableRequest implements Serializable {

    private static final long serialVersionUID = -3890744654110351039L;

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
}

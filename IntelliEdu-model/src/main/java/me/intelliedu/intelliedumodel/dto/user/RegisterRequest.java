package me.intelliedu.intelliedumodel.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 5200123986732432651L;
    private String username;
    private String password;
}

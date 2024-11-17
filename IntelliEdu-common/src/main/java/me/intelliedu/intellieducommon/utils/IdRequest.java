package me.intelliedu.intellieducommon.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdRequest implements Serializable {
    private static final long serialVersionUID = -691722172890054458L;
    private Long id;
}

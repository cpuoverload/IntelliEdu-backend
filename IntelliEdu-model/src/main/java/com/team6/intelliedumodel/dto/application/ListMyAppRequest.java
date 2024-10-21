package com.team6.intelliedumodel.dto.application;

import com.team6.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListMyAppRequest extends TableRequest implements Serializable {
    /**
     * Application ID
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
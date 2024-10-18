package com.team6.intelliedumodel.dto.application;

import com.team6.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListPublicAppRequest extends TableRequest implements Serializable {
    /**
     * Application Name
     */
    private String appName;

    private static final long serialVersionUID = 5654129428599598117L;
}

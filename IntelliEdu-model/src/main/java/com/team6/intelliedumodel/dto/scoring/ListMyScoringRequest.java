package com.team6.intelliedumodel.dto.scoring;

import com.team6.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListMyScoringRequest extends TableRequest implements Serializable {
    private static final long serialVersionUID = -364684559763216033L;

    /**
     * Application ID
     */
    private Long appId;
}

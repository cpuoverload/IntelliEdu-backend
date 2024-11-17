package me.intelliedu.intelliedumodel.dto.scoring;

import me.intelliedu.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListScoringRequest extends TableRequest implements Serializable {

    private static final long serialVersionUID = -8543001962339334880L;

    /**
     * Application ID
     */
    private Long appId;
}

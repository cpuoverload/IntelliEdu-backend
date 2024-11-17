package me.intelliedu.intelliedumodel.dto.question;

import me.intelliedu.intellieducommon.utils.TableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListQuestionRequest extends TableRequest implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Application ID
     */
    private Long appId;

    /**
     * Creator User ID
     */
    private Long userId;

    private static final long serialVersionUID = 8140630148540699928L;
}

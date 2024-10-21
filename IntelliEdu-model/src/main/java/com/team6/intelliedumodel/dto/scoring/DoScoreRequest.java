package com.team6.intelliedumodel.dto.scoring;

import com.team6.intelliedumodel.entity.Application;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: DoScoreRequest
 * Package: com.team6.intelliedumodel.dto.scoring
 * Description:
 *
 * @Author CBX
 * @Create 21/10/24 11:22
 * @Version 1.0
 */
@Data
public class DoScoreRequest implements Serializable {
    private static final long serialVersionUID = 6504624122615045534L;

    private List<String> AnswerList;

    private Application application;
}

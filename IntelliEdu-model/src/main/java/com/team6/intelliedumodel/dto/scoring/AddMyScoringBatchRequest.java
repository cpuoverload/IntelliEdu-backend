package com.team6.intelliedumodel.dto.scoring;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddMyScoringBatchRequest implements Serializable {
    private static final long serialVersionUID = 4073739394497080087L;

    private List<AddMyScoringRequest> scorings;
}

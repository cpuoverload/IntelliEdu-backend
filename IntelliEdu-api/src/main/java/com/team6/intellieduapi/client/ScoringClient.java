package com.team6.intellieduapi.client;

import com.team6.intellieducommon.utils.ApiResponse;
import com.team6.intelliedumodel.dto.scoring.DoScoreRequest;
import com.team6.intelliedumodel.entity.AnswerRecord;
import com.team6.intelliedumodel.entity.Application;
import com.team6.intelliedumodel.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "IntelliEdu-scoring-service", path = "/api/scoring")
public interface ScoringClient {

    @PostMapping("/doScore")
    AnswerRecord doScore(@RequestBody DoScoreRequest doScoreRequest);





}

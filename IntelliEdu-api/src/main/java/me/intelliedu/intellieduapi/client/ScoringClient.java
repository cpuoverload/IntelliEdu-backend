package me.intelliedu.intellieduapi.client;

import me.intelliedu.intelliedumodel.dto.scoring.DoScoreRequest;
import me.intelliedu.intelliedumodel.entity.AnswerRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "IntelliEdu-scoring-service", path = "/api/scoring")
public interface ScoringClient {

    @PostMapping("/doScore")
    AnswerRecord doScore(@RequestBody DoScoreRequest doScoreRequest);





}

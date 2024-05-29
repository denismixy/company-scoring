package ru.mikhailin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.DecisionService;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhailin.dto.ScoringCompanyRequest;
import ru.mikhailin.dto.ScoringCompanyResponse;
import ru.mikhailin.entity.ScoringOrder;
import ru.mikhailin.mybatis.ScoringOrderRepository;

import java.util.HashMap;

import static ru.mikhailin.enums.ScoringCompanyDmnParameters.*;

@Profile("!prod")
@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Тестовый контроллер для проверки работоспособности скоринга компании")
@Validated
public class TestController {
    private final DecisionService decisionService;
    private final ScoringOrderRepository scoringOrderRepository;

    private final ObjectMapper objectMapper;

    @Operation(method = "POST", summary = "Проверка компании на ограничения")
    @PostMapping("/scoring-company")
    public ScoringCompanyResponse scoringCompany(@RequestBody @Valid ScoringCompanyRequest scoringCompanyRequest) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(INN.getVariableName(), scoringCompanyRequest.getInn());
        params.put(REGION.getVariableName(), scoringCompanyRequest.getRegion());
        params.put(CAPITAL.getVariableName(), scoringCompanyRequest.getCapital());
        DmnDecisionResult dmnDecisionResult = decisionService.evaluateDecisionByKey("Scoring_decision_id")
                .variables(params)
                .evaluate();
        val scoringCompanyResponse = new ScoringCompanyResponse();
        if (!dmnDecisionResult.isEmpty()) {
            scoringCompanyResponse.setResult(false);
            ScoringOrder scoringOrder = objectMapper.convertValue(scoringCompanyRequest, ScoringOrder.class);
            scoringOrderRepository.insertScoringOrder(scoringOrder);
            log.info("[INSERT] Order ID: " + scoringOrder.getId());
        } else {
            scoringCompanyResponse.setResult(true);
        }
        return scoringCompanyResponse;
    }
}

package com.act.carrefourcashflow.business.controller;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.usecase.GenerateReportUseCase;
import com.act.carrefourcashflow.business.usecase.GetFinancialPostingByDateUseCase;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generateDailyReport")
public class GenerateDailyReportController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDailyReportController.class);

  private final GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase;
  private final GenerateReportUseCase generateReportUseCase;

  public GenerateDailyReportController(
      GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase,
      GenerateReportUseCase generateReportUseCase) {
    this.getFinancialPostingByDateUseCase = getFinancialPostingByDateUseCase;
    this.generateReportUseCase = generateReportUseCase;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> generateDailyReport(
      @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    LOGGER.debug("Starting GenerateDailyReportController.generateDailyReport");

    List<FinancialPosting> financialPostingsResponse =
        getFinancialPostingByDateUseCase.execute(date);

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("total", generateReportUseCase.execute(date));
    responseBody.put("daily report:", financialPostingsResponse);

    LOGGER.debug("Finished GenerateDailyReportController.generateDailyReport");
    return ResponseEntity.ok().body(responseBody);
  }
}

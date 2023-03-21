package com.act.carrefourcashflow.business.controller;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.usecase.GetFinancialPostingByDateUseCase;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("get-financial-posting")
public class GetFinancialPostingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetFinancialPostingController.class);

  private final GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase;

  public GetFinancialPostingController(
      GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase) {
    this.getFinancialPostingByDateUseCase = getFinancialPostingByDateUseCase;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<FinancialPosting>> getFinancialPost(
      @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

    LOGGER.debug("Starting GetFinancialPostingController.getFinancialPost");

    List<FinancialPosting> financialPostingsResponse =
        getFinancialPostingByDateUseCase.execute(date);

    LOGGER.debug("Finished GetFinancialPostingController.getFinancialPost");
    return ResponseEntity.ok().body(financialPostingsResponse);
  }
}

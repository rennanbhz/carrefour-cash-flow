package com.act.carrefourcashflow.business.controller;

import com.act.carrefourcashflow.business.controller.assembler.FinancialPostingRequestToFinancialPostingAssembler;
import com.act.carrefourcashflow.business.controller.model.FinancialPostingRequest;
import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.usecase.CreateFinancialPostingUseCase;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("create-financial-posting")
public class CreateFinancialPostingController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(CreateFinancialPostingController.class);

  private final CreateFinancialPostingUseCase createFinancialPostingUseCase;
  private final FinancialPostingRequestToFinancialPostingAssembler
      financialPostingRequestToFinancialPostingAssembler;

  public CreateFinancialPostingController(
      CreateFinancialPostingUseCase createFinancialPostingUseCase,
      FinancialPostingRequestToFinancialPostingAssembler
          financialPostingRequestToFinancialPostingAssembler) {
    this.createFinancialPostingUseCase = createFinancialPostingUseCase;
    this.financialPostingRequestToFinancialPostingAssembler =
        financialPostingRequestToFinancialPostingAssembler;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FinancialPosting> createFinancialPost(
      @Valid @RequestBody final FinancialPostingRequest financialPostingRequest) {
    LOGGER.debug("Starting CreateFinancialPostingController.createFinancialPost");

    FinancialPosting financialPosting =
        createFinancialPostingUseCase.execute(
            financialPostingRequestToFinancialPostingAssembler.assemble(financialPostingRequest));

    LOGGER.debug("Finished CreateFinancialPostingController.createFinancialPost");
    return ResponseEntity.status(HttpStatus.CREATED).body(financialPosting);
  }
}

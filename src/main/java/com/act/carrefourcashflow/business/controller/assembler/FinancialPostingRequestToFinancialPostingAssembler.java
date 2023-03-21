package com.act.carrefourcashflow.business.controller.assembler;

import com.act.carrefourcashflow.business.controller.model.FinancialPostingRequest;
import com.act.carrefourcashflow.business.dto.FinancialPosting;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinancialPostingRequestToFinancialPostingAssembler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(FinancialPostingRequestToFinancialPostingAssembler.class);

  public FinancialPosting assemble(FinancialPostingRequest financialPostingRequest) {
    LOGGER.debug("Starting FinancialPostingRequestToFinancialPostingAssembler.assemble");
    FinancialPosting financialPosting = new FinancialPosting();

    financialPosting.setTransactionId(UUID.randomUUID().toString());
    financialPosting.setCreatedAt(LocalDate.now());
    financialPosting.setType(financialPostingRequest.getType());
    financialPosting.setValue(financialPostingRequest.getValue());
    financialPosting.setDescription(financialPostingRequest.getDescription());

    return financialPosting;
  }
}

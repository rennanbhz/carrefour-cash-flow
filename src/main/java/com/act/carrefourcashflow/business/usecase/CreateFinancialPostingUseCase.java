package com.act.carrefourcashflow.business.usecase;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateFinancialPostingUseCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateFinancialPostingUseCase.class);

  private final IFinancialPostingRepository financialPostRepository;

  public CreateFinancialPostingUseCase(IFinancialPostingRepository financialPostRepository) {
    this.financialPostRepository = financialPostRepository;
  }

  public FinancialPosting execute(FinancialPosting financialPosting) {
    LOGGER.info("Started CreateFinancialPostingUseCase.execute");
    financialPostRepository.save(financialPosting);
    return financialPosting;
  }
}

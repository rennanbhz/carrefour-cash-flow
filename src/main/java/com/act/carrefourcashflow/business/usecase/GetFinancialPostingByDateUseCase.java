package com.act.carrefourcashflow.business.usecase;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetFinancialPostingByDateUseCase {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GetFinancialPostingByDateUseCase.class);

  private final IFinancialPostingRepository financialPostRepository;

  public GetFinancialPostingByDateUseCase(IFinancialPostingRepository financialPostRepository) {
    this.financialPostRepository = financialPostRepository;
  }

  public List<FinancialPosting> execute(LocalDate date) {
    LOGGER.info("Started GetFinancialPostingByDateUseCase.execute");
    return financialPostRepository.findAllByDate(date);
  }
}

package com.act.carrefourcashflow.business.usecase;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.dto.PostingType;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GenerateReportUseCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportUseCase.class);

  private final GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase;

  public GenerateReportUseCase(GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase) {
    this.getFinancialPostingByDateUseCase = getFinancialPostingByDateUseCase;
  }

  public double execute(LocalDate date) {
    LOGGER.info("Started GenerateReportUseCase.execute");
    return generateReport(date);
  }

  private double generateReport(LocalDate date) {
    List<FinancialPosting> financialPostings = getFinancialPostingByDateUseCase.execute(date);
    financialPostings.stream()
        .sorted(Comparator.comparing(FinancialPosting::getType))
        .collect(Collectors.toList());

    return financialPostings.stream()
        .mapToDouble(
            posting ->
                posting.getType() == PostingType.CREDIT ? posting.getValue() : -posting.getValue())
        .sum();
  }
}

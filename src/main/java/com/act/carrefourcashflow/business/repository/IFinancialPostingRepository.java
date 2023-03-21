package com.act.carrefourcashflow.business.repository;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import java.time.LocalDate;
import java.util.List;

public interface IFinancialPostingRepository {

  FinancialPosting save(FinancialPosting financialPosting);

  List<FinancialPosting> findAllByDate(LocalDate date);
}

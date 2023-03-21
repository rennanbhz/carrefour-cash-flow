package com.act.carrefourcashflow.business.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class DailyReport {

  private OffsetDateTime date;
  private BigDecimal balance;
  private List<FinancialPosting> financialPostings;

  public DailyReport() {}

  public DailyReport(
      OffsetDateTime date, BigDecimal balance, List<FinancialPosting> financialPostings) {
    this.date = date;
    this.balance = balance;
    this.financialPostings = financialPostings;
  }

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public List<FinancialPosting> getFinancialPostings() {
    return financialPostings;
  }

  public void setFinancialPostings(List<FinancialPosting> financialPostings) {
    this.financialPostings = financialPostings;
  }
}

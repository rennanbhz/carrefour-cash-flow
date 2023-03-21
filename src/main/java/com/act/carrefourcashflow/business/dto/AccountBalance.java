package com.act.carrefourcashflow.business.dto;

import java.math.BigDecimal;

public class AccountBalance {

  private BigDecimal balance;

  public AccountBalance() {}

  public AccountBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}

package com.act.carrefourcashflow.business.controller.model;

import static com.act.carrefourcashflow.business.controller.exceptions.ExceptionConstants.MISSING_TYPE_INFORMATION;
import static com.act.carrefourcashflow.business.controller.exceptions.ExceptionConstants.MISSING_VALUE;

import com.act.carrefourcashflow.business.dto.PostingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FinancialPostingRequest {

  private String transactionId;

  @NotNull(message = MISSING_VALUE)
  @Positive(message = "value must be positive")
  @JsonProperty
  private double value;

  @NotNull(message = MISSING_TYPE_INFORMATION)
  @JsonProperty
  private PostingType type;

  @JsonProperty private String description;

  public FinancialPostingRequest() {}

  public FinancialPostingRequest(
      String transactionId, double value, PostingType type, String description) {
    this.transactionId = transactionId;
    this.value = value;
    this.type = type;
    this.description = description;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public PostingType getType() {
    return type;
  }

  public void setType(PostingType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

package com.act.carrefourcashflow.business.dto;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;

public class FinancialPosting {

  @Id private String id;
  private String transactionId;
  private double value;
  private PostingType type;
  private LocalDate createdAt;
  private String description;

  public FinancialPosting() {}

  public FinancialPosting(
      String id,
      String transactionId,
      double value,
      PostingType type,
      LocalDate createdAt,
      String description) {
    this.id = id;
    this.transactionId = transactionId;
    this.value = value;
    this.type = type;
    this.createdAt = createdAt;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

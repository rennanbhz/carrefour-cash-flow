package com.act.carrefourcashflow.business.controller.exceptions;

public class GlobalException extends RuntimeException {

  private static final long serialVersionUID = 3226753888682202000L;
  private final Issue issue;

  protected GlobalException(final Issue issue) {
    this.issue = issue;
  }

  public Issue getIssue() {
    return issue;
  }
}

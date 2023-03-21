package com.act.carrefourcashflow.business.controller.exceptions;

import java.util.Collections;

public class InternalServerErrorException extends GlobalException {
  private static final long serialVersionUID = 1L;

  protected InternalServerErrorException(Issue issue) {
    super(issue);
  }

  public static InternalServerErrorException internalServerErrorException(
      IssueEnum issue, String missingField) {
    return new InternalServerErrorException(
        new Issue(issue, Collections.singletonList(missingField)));
  }

  public static InternalServerErrorException internalServerErrorException(IssueEnum issue) {
    return new InternalServerErrorException(new Issue(issue));
  }
}

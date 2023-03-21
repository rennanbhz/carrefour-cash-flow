package com.act.carrefourcashflow.business.controller.exceptions;

import java.util.Collections;

public class NotFoundException extends GlobalException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(Issue issue) {
    super(issue);
  }

  public static NotFoundException notFoundException(IssueEnum issue, String missingField) {
    return new NotFoundException(new Issue(issue, Collections.singletonList(missingField)));
  }

  public static NotFoundException notFoundException(IssueEnum issue) {
    return new NotFoundException(new Issue(issue));
  }
}

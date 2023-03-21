package com.act.carrefourcashflow.business.controller.exceptions;

import java.util.Collections;

public class BadRequestException extends GlobalException {

  private static final long serialVersionUID = -8168410722076596801L;

  private BadRequestException(final Issue issue) {
    super(issue);
  }

  public static BadRequestException badRequestException(IssueEnum issue, String missingField) {
    return new BadRequestException(new Issue(issue, Collections.singletonList(missingField)));
  }

  public static BadRequestException badRequestException(IssueEnum issue) {
    return new BadRequestException(new Issue(issue));
  }
}

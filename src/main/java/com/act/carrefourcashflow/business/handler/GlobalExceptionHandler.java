package com.act.carrefourcashflow.business.handler;

import com.act.carrefourcashflow.business.controller.exceptions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({BadRequestException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue processBadRequestException(final GlobalException ex, final WebRequest request) {
    return ex.getIssue();
  }

  @ExceptionHandler({NotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected Issue notFoundException(final GlobalException ex, final WebRequest request) {
    return ex.getIssue();
  }

  @ExceptionHandler({InternalServerErrorException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  protected Issue internalServerErrorException(final GlobalException ex, final WebRequest request) {
    return ex.getIssue();
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    Map<String, List<String>> body = new HashMap<>();

    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

    body.put("errors", errors);

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}

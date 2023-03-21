package com.act.carrefourcashflow.business.controller.exceptions;

import java.util.IllegalFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum IssueEnum {
  MISSING_REQUIRED_FIELDS(1, "Missing required fields."),
  INVALID_PRODUCT_PROPERTY(
      2, "Invalid Product Property. It should be: Name, Description or Enabled"),
  INVALID_UUID(3, "It's not a valid UUID."),
  REQUEST_TRACE_ID_HEADER_REQUIRED(4, "Missing required header: RequestTraceId"),
  AUTHORIZATION_HEADER_REQUIRED(5, "Missing required header: Token"),
  MISSING_COUNTRY_FILTER(6, "It should pass at least one Filter Type: COUNTRY"),
  INVALID_PROCESS_TYPE(7, "Invalid process type, it should be: EXPORT or IMPORT"),
  MISSING_REQUIRED_HEADER_TYPE(8, "Missing required header: 'type'"),
  PROCESS_ID_NOT_FOUND(9, "Process ID not found."),
  STATUS_IS_NOT_DONE(10, "The file requested is still in progress or it has failed."),
  ERROR_DOWNLOAD_AZURE_STORAGE(11, "Error when downloading the file from Azure Storage."),
  INVALID_URL(12, "Invalid URL."),
  FILE_MANAGEMENT_INTEGRATION_ERROR(
      13, "Failed to backup the CSV file on Azure Storage. Please contact the support team."),
  FAILED_READ_FILE(14, "Failed to read file"),
  HEADER_IS_BLANK(15, "There is a blank header in the CSV file. Please contact the support team."),
  MISSING_HEADER_FIELDS(
      16, "Missing the ProductId or SKU header(s). Please contact the support team."),
  MAXIMUM_LINES_QUANTITY_ERROR(
      17,
      "You're trying to import %s, which is above the maximum limit of 2000 products. Please contact the support team."),
  UNSUPPORTED_MEDIA_TYPE(18, "File not supported. Only CSV files are accepted."),
  INTERNAL_ERROR_MULTIPARTFILE(19, "Internal error in Multipartfile."),
  NO_PRODUCT_TO_BE_EXPORTED_ERROR(20, "There should be at least one product to be imported."),
  MORE_THAN_ONE_COUNTRY_FILTER(
      21,
      "Cannot export products from multiple countries. Please filter the result by the maximum of one country."),
  IMPORT_INVALID_STATUS(
      22, "The file requested is still in progress or it was successfully processed.");

  private final Logger logger = LogManager.getLogger(IssueEnum.class);

  private int code;
  private String message;

  IssueEnum(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getFormattedMessage(final Object... args) {
    if (message == null) {
      return "";
    }

    try {
      return String.format(message, args);
    } catch (final IllegalFormatException e) {
      logger.warn(e.getMessage(), e);
      return message.replace("%s", "");
    }
  }
}

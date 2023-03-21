package com.act.carrefourcashflow.business.dto;

public enum PostingType {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String value;

    PostingType(String value) {this.value = value;}

    public String getValue() {return value;}
}

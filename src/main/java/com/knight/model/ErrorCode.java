package com.knight.model;

public enum ErrorCode {
    OK("OK", "OK"),
    SOLD_OUT("SOLD_OUT", "상품이 매진되었습니다."),
    ERROR("ERROR", "알 수 없는 오류");

    private String code;
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return this.code; }
    public String getDescription() { return this.description; }
}

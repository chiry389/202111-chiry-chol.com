package com.knight.model;

public enum ProductStatus {
    OK("ING", "모집중"),
    ERROR("DONE", "모집완료");

    private String code;
    private String name;

    ProductStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return this.code; }
    public String getName() { return this.name; }
    public static ProductStatus getFromCode(String code) {
        ProductStatus found = null;
        for(ProductStatus e : ProductStatus.values()) {
            if(e.code.equals(code)) {
                found = e;
            }
        }

        return found;
    }
}

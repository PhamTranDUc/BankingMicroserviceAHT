package org.example.transactionservice.common;

public enum AccountType {
    SAVINGS("Tiết kiệm"),
    CHECKING("Thanh toán");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

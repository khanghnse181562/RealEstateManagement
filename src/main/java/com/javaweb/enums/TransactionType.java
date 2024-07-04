package com.javaweb.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");


    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> transactionType(){
        Map<String, String> transaction = new LinkedHashMap<>();
        for (TransactionType item : TransactionType.values()) {
            transaction.put(item.toString(), item.getName());
        }
        return transaction;
    }

    public static List<String> transactionKey(){
        List<String> transactionKey = new ArrayList<>();
        for (TransactionType item : TransactionType.values()) {
            transactionKey.add(item.toString());
        }
        return transactionKey;
    }
}

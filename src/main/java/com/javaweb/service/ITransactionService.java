package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;

import java.util.List;

public interface ITransactionService {
    List<TransactionEntity> getTransactionTypeList(String code, Long customerId);
    void deleteTransaction(List<Long> ids);
}

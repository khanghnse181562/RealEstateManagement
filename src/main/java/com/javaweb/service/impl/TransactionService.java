package com.javaweb.service.impl;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    @Override
    public List<TransactionEntity> getTransactionTypeList(String code, Long customerId) {
        return transactionRepository.findByCodeAndCustomerId(code, customerId);
    }

    @Override
    public void deleteTransaction(List<Long> ids) {
        List<TransactionEntity> list = transactionRepository.findAllById(ids);
        transactionRepository.deleteAll(list);
    }
}

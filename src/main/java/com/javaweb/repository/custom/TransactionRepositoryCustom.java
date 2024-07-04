package com.javaweb.repository.custom;

import com.javaweb.entity.TransactionEntity;

public interface TransactionRepositoryCustom {
    void saveWithNullModifiedFields(TransactionEntity entity);
}

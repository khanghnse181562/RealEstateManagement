package com.javaweb.repository.custom.impl;

import com.javaweb.entity.TransactionEntity;

import com.javaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveWithNullModifiedFields(TransactionEntity entity) {
       // Save the entity first
            entityManager.persist(entity);
            entityManager.flush();

            // Set modifiedDate and modifiedBy to null
            entity.setModifiedDate(null);
            entity.setModifiedBy(null);

            // Merge the entity to update it
            entityManager.merge(entity);
    }

}

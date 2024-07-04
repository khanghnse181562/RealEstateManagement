package com.javaweb.repository.custom;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;


import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> searchByField(CustomerSearchRequest customerRequest);
    int countTotalItems(CustomerSearchRequest customerRequest);
}

package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.ResponseDTO;


import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> getAllCustomer(CustomerSearchRequest customerSearchRequest);
    ResponseDTO loadStaff(Long id);
    void delete(List<Long> id);
    void updateAssignmentCustomer(AssignmentCustomerDTO customer);
    int countTotalItems(CustomerSearchRequest customerRequest);
    void addCustomer(CustomerDTO customer);
    CustomerEntity findById(Long id);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
}

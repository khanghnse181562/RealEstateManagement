package com.javaweb.repository;


import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long> {
    List<AssignmentCustomerEntity> findByCustomer_Id(Long id);
    AssignmentCustomerEntity findByStaffAndCustomer(UserEntity user, CustomerEntity customer);
}

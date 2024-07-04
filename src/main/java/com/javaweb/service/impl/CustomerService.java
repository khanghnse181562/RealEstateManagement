package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AssignmentCustomerRepository assignmentCustomerRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public List<CustomerEntity> getAllCustomer(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> customerList = customerRepository.searchByField(customerSearchRequest);
        return customerList;
    }

    @Override
    public ResponseDTO loadStaff(Long id) {
        // lấy tất cả nhân viên
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // lấy tất cả nhân viên quản lí khách hàng có id trên
        List<UserEntity> userEntitiesWithId = new ArrayList<>();
        List<AssignmentCustomerEntity> listAssignment = assignmentCustomerRepository.findByCustomer_Id(id);
        for (AssignmentCustomerEntity item : listAssignment) {
            userEntitiesWithId.add(item.getStaff());
        }
        List<StaffResponseDTO> staffAssignment = new ArrayList<>();
        for (UserEntity item : userEntities) {
            StaffResponseDTO staffExisted = new StaffResponseDTO();
            staffExisted.setStaffId(item.getId());
            staffExisted.setFullName(item.getFullName());
            staffExisted.setChecked("");
            if (userEntitiesWithId.contains(item)){
                staffExisted.setChecked("checked");
            }
            staffAssignment.add(staffExisted);
        }
        ResponseDTO result = new ResponseDTO();
        result.setData(staffAssignment);
        result.setMessage("Successfully loaded staff");
        return result;
    }

    @Override
    public void delete(List<Long> id) {
        List<CustomerEntity> listCustomers = customerRepository.findAllById(id);
        for (CustomerEntity item: listCustomers){
            item.setIsActive(0L);
        }
    }

    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO customer) {
         CustomerEntity customerEntity = customerRepository.findById(customer.getCustomerId()).orElse(null);
         List<UserEntity> userEntities = userRepository.findAllById(customer.getStaffs());
         for (UserEntity item : userEntities) {
             AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
             assignmentCustomerEntity.setCustomer(customerEntity);
             assignmentCustomerEntity.setStaff(item);
             assignmentCustomerRepository.save(assignmentCustomerEntity);
         }
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerRequest) {
        return customerRepository.countTotalItems(customerRequest);
    }

    @Override
    public void addCustomer(CustomerDTO customer) {
         CustomerEntity newCustomer = modelMapper.map(customer, CustomerEntity.class);
         // status default "Chưa xử lí" in liên-hệ page
         if ( customer.getStatus() == null ){
             newCustomer.setStatus("Chưa xử lí");
         } else {
             newCustomer.setStatus(Status.getValueByKey(customer.getStatus()));
         }
         newCustomer.setIsActive(1L);
         customerRepository.save(newCustomer);
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        // Thêm mới transaction
        if (transactionDTO.getIdTransaction() == null ){
            TransactionEntity transactionEntity = new TransactionEntity();
            CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
            transactionEntity.setCode(transactionDTO.getCode());
            transactionEntity.setNote(transactionDTO.getTransactionDetail());
            transactionEntity.setCustomer(customerEntity);
            transactionEntity.setCreatedDate(new Date());
            transactionEntity.setCreatedBy(SecurityUtils.getPrincipal().getUsername());
            transactionRepository.save(transactionEntity);
        } else { // Chỉnh sửa transaction
             TransactionEntity transactionEntity = transactionRepository.findById(transactionDTO.getIdTransaction()).get();
             transactionEntity.setNote(transactionDTO.getTransactionDetail());
             transactionEntity.setModifiedDate(new Date());
             transactionEntity.setModifiedBy(SecurityUtils.getPrincipal().getUsername());
             UserEntity userEntity = userRepository.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), 1);
             transactionEntity.setStaffId(userEntity.getId());
             transactionRepository.save(transactionEntity);
        }
    }
}

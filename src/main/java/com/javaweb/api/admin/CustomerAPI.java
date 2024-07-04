package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaff(@PathVariable("id") Long id){

        ResponseDTO result = customerService.loadStaff(id);
        return result;
    }

    @PostMapping
    public String addCustomer(@RequestBody CustomerDTO customerDTO){
        // xuống tầng service xử lí customerDTO
        customerService.addCustomer(customerDTO);
        return new String("Add new customer successfully");
    }

    @DeleteMapping("/{ids}")
    public String deleteCustomer(@PathVariable List<Long> ids){
        // Xuống DB xóa data
        customerService.delete(ids);
        return new String("Delete customer successfully");
    }

    @PutMapping
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        // Xuống service để xử lí nhé :>
        customerService.updateAssignmentCustomer(assignmentCustomerDTO);
    }


    @PostMapping("/transaction")
    public String addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        customerService.addOrUpdateTransaction(transactionDTO);
        return new String("Add or update new transaction successfully");
    }

}

package com.javaweb.api.admin;


import com.javaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;


    @DeleteMapping("/{ids}")
    public String deleteTransaction(@PathVariable List<Long> ids){
        transactionService.deleteTransaction(ids);
        return new String("Delete transaction successfully");
    }
}

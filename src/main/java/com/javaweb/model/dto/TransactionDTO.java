package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private Long idTransaction;
    private Long customerId;
    private String code;
    private String transactionDetail;
}

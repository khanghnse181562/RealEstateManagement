package com.javaweb.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="assignmentcustomer")
@Getter
@Setter
public class AssignmentCustomerEntity  extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staff;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;
}

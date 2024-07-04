package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="building")
@Getter
@Setter
public class BuildingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="street")
    private String street;

    @Column(name="ward")
    private String ward;

    @Column(name="district")
    private String district;

    @Column(name="structure")
    private String structure;

    @Column(name="numberofbasement")
    private Long numberOfBasement;

    @Column(name="floorarea")
    private Long floorArea;

    @Column(name="direction")
    private String direction;

    @Column(name="level")
    private String level;

    @Column(name="rentprice")
    private Long rentPrice;

    @Column(name="rentpricedescription")
    private String rentPriceDescription;

    @Column(name="servicefee")
    private String serviceFee;

    @Column(name="carfee")
    private String carFee;

    @Column(name="motofee")
    private String motorFee;

    @Column(name="overtimefee")
    private String overTimeFee;

    @Column(name="waterfee")
    private String waterFee;

    @Column(name="electricityfee")
    private String electricityFee;

    @Column(name="deposit")
    private String deposit;

    @Column(name="payment")
    private String payment;

    @Column(name="renttime")
    private String rentTime;

    @Column(name="decorationtime")
    private String decorationTime;

    @Column(name="brokeragefee")
    private Double brokerageFee;

    @Column(name="type")
    private String type;

    @Column(name="note")
    private String note;

    @Column(name="linkofbuilding")
    private String linkOfBuilding;

    @Column(name="map")
    private String map;

    @Column(name="avatar")
    private String image;

    @Column(name="managername")
    private String managerName;

    @Column(name="managerphone")
    private String managerPhone;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffs = new ArrayList<>();
}

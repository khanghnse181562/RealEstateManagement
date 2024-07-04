package com.javaweb.model.dto;

import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
public class BuildingDTO extends AbstractDTO{
    private String name;
    private String street;
    private String ward;
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motorFee;
    private String structure;
    private String direction;
    private String note;
    private String rentArea;
    private String managerName;
    private String managerPhone;
    private Long rentPrice;
    private String serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();

    public Map<String, String> getBuildingDTOs() {
        return buildingDTOs;
    }

    public String getNote() {
        return note;
    }

    public String getDirection() {
        return direction;
    }

    public String getStructure() {
        return structure;
    }


    public String getImage() {
        return image;
    }

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public String getRentArea() {
        return rentArea;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public String getDistrict() {
        return district;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public String getLevel() {
        return level;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public String getCarFee() {
        return carFee;
    }

    public String getMotorFee() {
        return motorFee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getPayment() {
        return payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

}
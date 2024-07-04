package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private String ward;
    private String street;
    private Long districtId;
    private Long numberOfBasement;
    private String managerName;
    private String managerPhoneNumber;
    private String direction;
    private String level;
    private List<String> typeCode = new ArrayList<>();
    private Long floorArea;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long staffId;


    public BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.ward = builder.ward;
        this.street = builder.street;
        this.districtId = builder.districtId;
        this.numberOfBasement = builder.numberOfBasement;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;
        this.direction = builder.direction;
        this.level = builder.level;
        this.typeCode = builder.typeCode;
        this.floorArea = builder.floorArea;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.staffId = builder.staffId;
    }

    public String getName() {
        return name;
    }
    public String getWard() {
        return ward;
    }
    public String getStreet() {
        return street;
    }
    public Long getDistrictId() {
        return districtId;
    }
    public Long getNumberOfBasement() {
        return numberOfBasement;
    }
    public String getManagerName() {
        return managerName;
    }
    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }
    public String getDirection() {
        return direction;
    }
    public String getLevel() {
        return level;
    }
    public List<String> getTypeCode() {
        return typeCode;
    }
    public Long getFloorArea() {
        return floorArea;
    }
    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }
    public Long getRentPriceTo() {
        return rentPriceTo;
    }
    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }
    public Long getRentAreaTo() {
        return rentAreaTo;
    }
    public Long getStaffId() {
        return staffId;
    }

    // Builder
    public static class Builder {
        private String name;
        private String ward;
        private String street;
        private Long districtId;
        private Long numberOfBasement;
        private String managerName;
        private String managerPhoneNumber;
        private String direction;
        private String level;
        private List<String> typeCode = new ArrayList<>();
        private Long floorArea;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long staffId;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setDistrictId(Long districtId) {
            this.districtId = districtId;
            return this;
        }
        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public Builder setManagerPhoneNumber(String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
            return this;
        }
        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }
        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }
        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public Builder setRentAreaFrom(Long rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }
        public Builder setRentAreaTo(Long rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }
        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}

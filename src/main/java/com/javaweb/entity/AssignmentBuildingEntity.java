package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="assignmentbuilding")
@Getter
@Setter
public class AssignmentBuildingEntity extends BaseEntity{

//    @ManyToOne
//    @JoinColumn(name="staffid")
//    private UserEntity staffs;
//
//    @ManyToOne
//    @JoinColumn(name="buildingid")
//    private BuildingEntity buildings;
}

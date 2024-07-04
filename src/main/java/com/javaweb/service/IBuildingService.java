package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingRequest);
    BuildingDTO findById(Long id);
    void delete(List<Long> ids);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    ResponseDTO loadStaff(Long id);
    int countTotalItems(BuildingSearchRequest buildingRequest);
}

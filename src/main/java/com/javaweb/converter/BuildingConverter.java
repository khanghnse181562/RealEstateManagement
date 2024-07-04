package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity building){
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(building, BuildingSearchResponse.class);
        // get the district name
        String districtName = districtCode.valueOf(building.getDistrict()).getDistrictName();
        buildingSearchResponse.setAddress(building.getStreet() + ", " + building.getWard() + ", " + districtName);
        String listRentArea = building.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(listRentArea);
        return buildingSearchResponse;
    }
}

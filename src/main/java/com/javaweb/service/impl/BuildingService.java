package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingService implements IBuildingService {


    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingRequest) {
        List<BuildingEntity> listBuilding = buildingRepository.findByField(buildingRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity buildingEntity : listBuilding) {
             BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingSearchResponse(buildingEntity);
             result.add(buildingSearchResponse);
        }
        return result;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BuildingDTO findById(Long id) {
        // get the building by id
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        // get list of RentAreaEntity
        List<RentAreaEntity> listRentAreas = buildingEntity.getRentAreas();
        // get type code
        String typeCode = buildingEntity.getType();
        List<String> typeCodeOptions;
        if (typeCode.contains(",")){
            // split string in typeCode with ", " , and put into the List<String>
            String[] parts = typeCode.split(",");
            typeCodeOptions = Arrays.stream(parts)
                    .map(String::trim) // Trim whitespace from each substring
                    .collect(Collectors.toList());
        } else {
            typeCodeOptions = Collections.singletonList(typeCode);
        }
        // Covert all rent area value into string
        String rentAreaValue = listRentAreas.stream()
                .map(RentAreaEntity::getValue) // Extracting the value attribute
                .map(String::valueOf) // Converting to String
                .collect(Collectors.joining(", "));
        // map from entity to DTO
        BuildingDTO buildingResult = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingResult.setId(id);
        buildingResult.setRentArea(rentAreaValue);
        buildingResult.setTypeCode(typeCodeOptions);
        return buildingResult;
    }

    @Override
    public void delete(List<Long> ids) {
        buildingRepository.deleteAllByIdIn(ids);
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        String typeCode = buildingDTO.getTypeCode().stream().map(String::trim).collect(Collectors.joining(","));
        List<Long> rentAreaValues;
        if (buildingDTO.getRentArea().contains(",")){
            String[] parts = buildingDTO.getRentArea().split(",");
            rentAreaValues = Arrays.stream(parts)
                    .map(String::trim) // Trim whitespace from each substring
                    .map(Long::parseLong) // Parse each substring into a Long
                    .collect(Collectors.toList());
        } else {
            rentAreaValues = Collections.singletonList(Long.parseLong(buildingDTO.getRentArea()));
        }
        List<RentAreaEntity> listRentArea = new ArrayList<>();
        BuildingEntity building = modelMapper.map(buildingDTO, BuildingEntity.class);
        building.setType(typeCode);
        saveThumbnail(buildingDTO, building);
        for (Long it : rentAreaValues) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(it);
            rentAreaEntity.setBuilding(building);
            listRentArea.add(rentAreaEntity);
        }
        building.setRentAreas(listRentArea);
        buildingRepository.save(building);
    }

    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        // list user
        List<UserEntity> listUserEntity = userRepository.findAllById(assignmentBuildingDTO.getStaffs());
        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        building.setStaffs(listUserEntity);
        buildingRepository.save(building);
    }

    @Override
    public ResponseDTO loadStaff(Long id) {
        // lấy tất cả nhân viên
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // lấy tất cả nhân viên quản lí tòa nhà có id trên
        List<UserEntity> userEntitiesWithId = userRepository.findByBuilding_Id(id);
        List<StaffResponseDTO> staffAssignment = new ArrayList<>();
        for (UserEntity item : userEntities) {
            StaffResponseDTO staffExisted = new StaffResponseDTO();
            staffExisted.setStaffId(item.getId());
            staffExisted.setFullName(item.getFullName());
            staffExisted.setChecked("");
            if (userEntitiesWithId.contains(item)){
                staffExisted.setChecked("checked");
            }
            staffAssignment.add(staffExisted);
        }
        ResponseDTO result = new ResponseDTO();
        result.setData(staffAssignment);
        result.setMessage("Successfully loaded staff");
        return result;

    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingRequest) {
        return buildingRepository.countTotalItems(buildingRequest);
    }


    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

}

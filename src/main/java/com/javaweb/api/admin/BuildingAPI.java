package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    IBuildingService buildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        // xuống tầng service xử lí buildingDTO
        buildingService.addOrUpdateBuilding(buildingDTO);
        return new String("Add or update building successfully");
    }

    @DeleteMapping("/{ids}")
    public String deleteBuilding(@PathVariable List<Long> ids){
        // Xuống DB xóa data
        buildingService.delete(ids);
        return new String("Delete building successfully");
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaff(@PathVariable("id") Long id){

        ResponseDTO result = buildingService.loadStaff(id);
        return result;
    }

    @PutMapping
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        System.out.println("OK");
        buildingService.updateAssignmentBuilding(assignmentBuildingDTO);
       // Xuống service để xử lí nhé :>
    }
}

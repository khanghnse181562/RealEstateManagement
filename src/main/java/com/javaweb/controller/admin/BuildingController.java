package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IBuildingService buildingService;
    @Autowired
    IUserService userService;
    @Autowired
    private MessageUtils messageUtils;

    @GetMapping(value="/admin/building-list")
     public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest model, HttpServletRequest request){
         ModelAndView mav = new ModelAndView("admin/building/list");
         DisplayTagUtils.of(request, model);
         mav.addObject("staffs", userService.getStaffs());
         mav.addObject("districtCode", districtCode.district());
         mav.addObject("typeCodes", typeCode.getTypeCode());
         // Xuống DB lấy được dữ liệu lên
        List<BuildingSearchResponse> result;
         if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
             Long staffId = SecurityUtils.getPrincipal().getId();
             model.setStaffId(staffId);
             result = buildingService.findAll(model);
         } else {
             result = buildingService.findAll(model);
         }
         // Đưa dữ liệu cần tìm kiếm xuống DB bằng buildingSearchRequest với 16 field
         model.setListResult(result);
         model.setTotalItems(buildingService.countTotalItems(model));
         mav.addObject(SystemConstant.MODEL, model);
         initMessageResponse(mav, request);
         return mav;
     }

    @GetMapping(value="/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        mav.addObject(SystemConstant.BUTTON, "Thêm");
        return mav;
    }

    @GetMapping(value="/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        // Lay ra toa nha theo id, findByBuildingId
        BuildingDTO result = buildingService.findById(id);
        mav.addObject(SystemConstant.MODEL, result);
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        mav.addObject(SystemConstant.BUTTON, "Chỉnh sửa");
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}

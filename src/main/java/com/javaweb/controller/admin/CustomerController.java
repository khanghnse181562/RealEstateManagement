package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController(value="customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransactionService transactionService;

    @GetMapping(value="/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        DisplayTagUtils.of(request, model);
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("status", Status.type());
        // Xuống DB lấy được dữ liệu lên
        List<CustomerEntity> customerList;
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            model.setStaffId(staffId);
            customerList = customerService.getAllCustomer(model);
        } else {
            customerList = customerService.getAllCustomer(model);
        }
        // Đưa dữ liệu cần tìm kiếm xuống DB bằng customerSearchRequest
        model.setListResult(customerList);
        // tính theo countTotal dựa trên model trả về
        model.setTotalItems(customerService.countTotalItems(model));
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }

    @GetMapping(value="/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customer){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customer);
        mav.addObject("status", Status.type());
        return mav;
    }

    @GetMapping(value="/admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        // Lay ra customer theo id, findByCustomerId
        CustomerEntity result = customerService.findById(id);
        mav.addObject("customerEdit", result);
        mav.addObject("status", Status.type());
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject(SystemConstant.BUTTON, "Chỉnh sửa");
        List<TransactionEntity> listCSKH = transactionService.getTransactionTypeList(TransactionType.transactionKey().get(0), id);
        List<TransactionEntity> listDDX = transactionService.getTransactionTypeList(TransactionType.transactionKey().get(1), id);
        mav.addObject("listCSKH", listCSKH);
        mav.addObject("listDDX", listDDX);
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

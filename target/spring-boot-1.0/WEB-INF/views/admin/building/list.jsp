
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/buildings"/>
<c:url var="formUrl" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>Danh sách tòa nhà
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <span class="widget-toolbar">
											<a href="#" data-action="reload">
												<i class="ace-icon fa fa-refresh"></i>
											</a>
											<a href="#" data-action="collapse">
												<i class="ace-icon fa fa-chevron-up"></i>
											</a>
										</span>
                        </div>

                        <div class="widget-body" style="display: block;">
                            <div class="widget-main">
                                <form:form action="/admin/building-list"
                                           modelAttribute="model"
                                           method="GET" id="listForm">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Tên tòa nhà</label>
                                                    <form:input path="name" class="form-control" />
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>Diện tích sàn</label>
                                                    <form:input path="floorArea" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label>Quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="" label="-----Chọn quận-----"></form:option>
                                                        <form:options items="${districtCode}"></form:options>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Phường</label>
                                                    <form:input path="ward" class="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Đường</label>
                                                    <form:input path="street" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>Số tầng hầm</label>
                                                    <form:input path="numberOfBasement" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hướng</label>
                                                    <form:input path="direction" class="form-control" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hạng</label>
                                                    <form:input path="level" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label>Diện tích từ</label>
                                                    <form:input path="areaFrom" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Diện tích đến</label>
                                                    <form:input path="areaTo" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê từ</label>
                                                    <form:input path="rentPriceFrom" class="form-control" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê đến</label>
                                                    <form:input path="rentPriceTo" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label>Tên quản lý</label>
                                                    <form:input path="managerName" class="form-control" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>SĐT quản lí</label>
                                                    <form:input path="managerPhone" class="form-control" />
                                                </div>
                                                <div class="col-xs-2">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <label>Nhân viên phụ trách</label>
                                                        <form:select path="staffId" class="form-control">
                                                            <form:option value="" label="-----Chọn nhân viên-----"></form:option>
                                                            <form:options items="${staffs}"></form:options>
                                                        </form:select>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes path="typeCode" items="${typeCodes}"></form:checkboxes>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button class="btn btn-sm btn-primary" id="btnSearch">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                  <security:authorize access="hasRole('MANAGER')">
                      <div class="pull-right">
                          <a href="/admin/building-edit">
                              <button title="Thêm tòa nhà" class="btn btn-primary">
                                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                      <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                      <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                      <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                  </svg>
                              </button>
                          </a>
                          <button title="Xóa tòa nhà" class="btn btn-danger" id="btnDeleteBuildings">
                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                  <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                  <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                              </svg>
                          </button>
                      </div>
                  </security:authorize>

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->

            <hr class="hr hr-18 dotted hr-double" >
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive" id="buildingList">
                         <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                       requestURI="${formUrl}" partialList="true" sort="external"
                       size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                       id="tableList" pagesize="${model.maxPageItems}"
                       export="false"
                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                            headerClass="center select-cell">
                <fieldset>
                    <input type="checkbox" name="checkList" value="${tableList.id}"
                           id="checkbox_${tableList.id}" class="check-box-element"/>
                </fieldset>
            </display:column>
            <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
            <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
            <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
            <display:column headerClass="text-left" property="managerName" title="Tên quản lí"/>
            <display:column headerClass="text-left" property="managerPhone" title="SĐT quản lí"/>
            <display:column headerClass="text-left" property="floorArea" title="D.tích sàn"/>
            <display:column headerClass="text-left" property="emptyArea" title="D.tích trống"/>
            <display:column headerClass="text-left" property="rentArea" title="D.tích thuê"/>
            <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
            <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
            <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>
            <display:column headerClass="col-actions" title="Thao tác">
                <security:authorize access="hasRole('MANAGER')">
                <button title="Giao tòa nhà" class="btn btn-xs btn-success"
                        onclick="assignmentBuilding(${tableList.id})">
                    <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                </button>
                </security:authorize>
                <a title="Sửa tòa nhà" class="btn btn-xs btn-primary" href="/admin/building-edit-${tableList.id}">
                    <i class="ace-icon fa fa-pencil-square-o"></i>
                </a>
                <security:authorize access="hasRole('MANAGER')">
                <button title="Xóa tòa nhà" class="btn btn-xs btn-danger"
                        onclick="btnDeleteBuilding(${tableList.id})">
                    <i class="ace-icon fa fa-trash-o"></i>
                </button>
                </security:authorize>
            </display:column>
        </display:table>
    </div>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div>

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">
                            Chọn
                        </th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Hủy thao tác</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId){
        $('#assignmentBuildingModal').modal();
        // đã gán value cho id buildingId
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);
    }

    function loadStaffs(buildingId){
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            dataType: "json",
            success: (response) => {
                var row = "";
                $.each(response.data, function(index, item){
                    row += "<tr>";
                    row += '<td class="center"><input type="checkbox" value=' + item.staffId + '  id="checkbox_"' + item.staffId + " " + item.checked
                     + '/></td>';
                    row += '<td class="text-center">' + item.fullName +'</td>'
                    row += "</tr>";
                });
                $('#staffList tbody').html(row);
            },
            error: function(response){
                alert("Khong thanh cong");
            }
        })
    }

    $('#btnAssignBuilding').click(function(e){
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;

        $.ajax({
            type: "PUT",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            // dataType: "json",
            success: (response) => {
                alert('Giao thanh cong')
                console.log('success');

            },
            error: function(response){
                console.log('Giao khong thanh cong');
            }
        });
    })


    $('#btnDeleteBuildings').click(function(e){
        e.preventDefault();
        var data = {};
        var buildingIds = $('#buildingList').find('fieldset input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        // remove first element name "on" when click checkbox all
        if (buildingIds[0] === "on"){
            buildingIds.shift();
        }
        data['buildingIds'] = buildingIds;
        deleteBuilding(data);
    })

    function btnDeleteBuilding(buildingId){
        var data = {};
        data['buildingIds'] = buildingId;
        deleteBuilding(data);
    }


    function deleteBuilding(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data['buildingIds'],
            data: JSON.stringify(data),
            contentType: "application/json",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            dataType: "text",
            success: (response) => {
                alert(response)
                window.location.replace('/admin/building-list');
            },
            error: function(response){
                alert("Xoa ko thanh cong");
            }
        })
    }

    $('#btnSearch').click(function(e) {
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>

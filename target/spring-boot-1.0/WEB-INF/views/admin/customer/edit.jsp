
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="customerEditURL" value="/admin/customer-edit"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
</head>
<body>
<div id="main-content" class="main-content">
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
                <li class="active">Thêm khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>Thông tin khách hàng
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <form:form modelAttribute="customerEdit"
                           method="get"
                           action="${customerEditURL}"
                           id="form-edit"
                >
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" >
                                <%--           Thêm đoạn này                --%>
                            <form:hidden path="id" class="form-control" value="${customerEdit.id}"/>
                                <%--           Lưu ý check               --%>
                            <div class="form-group">
                                <label class="col-xs-3">Tên khách hàng</label>
                                <div class="col-xs-9">
                                    <form:input path="fullName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại</label>
                                <div class="col-xs-9">
                                    <form:input path="phone" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <form:input path="email" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên công ty</label>
                                <div class="col-xs-9">
                                    <form:input path="companyName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input path="demand" class="form-control"/>
                                </div>
                            </div>
                             <div class="form-group">
                                 <label class="col-xs-3">Tình trạng</label>
                                 <div class="col-xs-9">
                                     <form:select path="status" class="form-control">
                                         <form:options items="${status}"></form:options>
                                     </form:select>
                                 </div>
                             </div>

                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty customerEdit.id}">
                                        <security:authorize access="hasRole('MANAGER')">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">
                                            Chỉnh sửa thông tin
                                        </button>
                                        </security:authorize>
                                    </c:if>
                                    <c:if test="${empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">
                                            Thêm khách hàng
                                        </button>
                                    </c:if>
                                    <a href="/admin/customer-list">
                                        <button class="btn btn-primary" type="button">Quay về trang chủ</button>
                                    </a>
                                </div>
                            </div>
                        </form>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </form:form>

            </div><!-- /.row -->



        </div><!-- /.page-content -->
        <c:forEach var="item" items="${transactionType}">
            <div class="col-xs-12">
                <div class="col-sm-12">
                    <h3 class="header smaller lighter blue">${item.value}</h3>
                    <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}','${customerEdit.id}')">
                        <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                    </button>
                </div>
                <c:if test="${item.key == 'CSKH'}">
                    <div class="col-xs-12">
                        <table id="simple-table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Ngày sửa</th>
                                <th>Người sửa</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cskh" items="${listCSKH}">
                                <tr>
                                    <td>${cskh.createdDate}</td>
                                    <td>${cskh.createdBy}</td>
                                    <td>${cskh.modifiedDate}</td>
                                    <td>${cskh.modifiedBy}</td>
                                    <td>${cskh.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch"
                                                    onclick="UpdateTransaction(${cskh.id},'${cskh.note}')">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button title="Xóa giao dịch" class="btn btn-xs btn-danger"
                                                        onclick="btnDeleteTransaction(${cskh.id})">
                                                    <i class="ace-icon fa fa-trash-o"></i>
                                                </button>
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${item.key == 'DDX'}">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Ngày sửa</th>
                                <th>Người sửa</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="ddx" items="${listDDX}">
                                <tr>
                                    <td>${ddx.createdDate}</td>
                                    <td>${ddx.createdBy}</td>
                                    <td>${ddx.modifiedDate}</td>
                                    <td>${ddx.modifiedBy}</td>
                                    <td>${ddx.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch"
                                                    onclick="UpdateTransaction(${ddx.id}, '${ddx.note}')">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </button>
                                       <%--     xóa giao dịch--%>
                                            <security:authorize access="hasRole('MANAGER')">
                                            <button title="Xóa giao dịch" class="btn btn-xs btn-danger"
                                                    onclick="btnDeleteTransaction(${ddx.id})">
                                                <i class="ace-icon fa fa-trash-o"></i>
                                            </button>
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right" >Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="width-100" value="">
                        </span>
                    </div>
                </div>
                <input type="hidden" id="customerId" name="customerId" value="">
                <input type="hidden" id="code" name="code" value="">
                <input type="hidden" id="idTransaction" name="idTransaction" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    // Thêm mới transaction
    function transactionType(code, customerId){
        // refresh id của transaction sau khi bấm zô chỉnh sửa
        $('#idTransaction').val('');
       $('#transactionTypeModal').modal();
       $('#customerId').val(customerId);
       $('#code').val(code);
       $('#transactionDetail').val('');
    }

    // Update transaction
    function UpdateTransaction(idTransaction, note){
        $('#transactionTypeModal').modal();
        $('#idTransaction').val(idTransaction);
        // content appeared on input field
        $('#transactionDetail').val(note);
    }


    $('#btnAddOrUpdateTransaction').click(function(e){
        e.preventDefault();
        var data = {};
        data["idTransaction"] = $('#idTransaction').val();
        data["customerId"] = $('#customerId').val();
        data["code"] = $('#code').val();
        data["transactionDetail"] = $('#transactionDetail').val();
        addTransaction(data);
    });

    function addTransaction(data){
        $.ajax({
            type: "POST",
            url: '/api/customers/transaction',
            data: JSON.stringify(data),
            contentType: "application/json",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            dataType: "text",
            success:(response) => {
                console.log("success")
                alert(response);
                window.location.replace('/admin/customer-list');
            },
            error: function(response){
                console.log('Failed');
            }
        })
    }

    $('#btnAddOrUpdateCustomer').click(function(){
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function(i, it) {
            data["" + it.name + ""] = it.value;
        })
        if (!data["fullName"]) {
            return alert("Customer name is required !");
        } else if (!data["phone"]) {
            return alert("Phone number is required !");
        } else {
            btnAddOrUpdate(data);
        }
    });

    function btnAddOrUpdate(data){
        $.ajax({
            type: "POST",
            url: '/api/customers',
            data: JSON.stringify(data),
            contentType: "application/json",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            dataType: "text",
            success:(response) => {
                alert(response);
                window.location.replace('/admin/customer-list');
            },
            error: function(response){
                console.log('Failed to add new customer !! Try again ');
            }
        })
    }

    function btnDeleteTransaction(transactionId){
        var data = {};
        data['transactionId'] = transactionId;
        deleteTransaction(data);
    }

    function deleteTransaction(data){
        $.ajax({
            type: "DELETE",
            url: "/api/transaction/" + data['transactionId'],
            data: JSON.stringify(data),
            contentType: "application/json",
            // Kiểu dữ liệu mà server trả ra, nếu như xóa hay thêm mới thì ko trả ra dataType, thì bỏ dataType đi
            dataType: "text",
            success: (response) => {
                alert(response)
                window.location.replace('/admin/customer-list');
            },
            error: function(response){
                alert("Can not delete transaction !! Try again");
            }
        })
    }
</script>
</body>
</html>

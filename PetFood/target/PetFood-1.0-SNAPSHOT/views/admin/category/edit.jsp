<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var ="categoryAPI" value="/api-admin-category"/>
<c:url var="categoryURL" value="/admin-category"/>

<html>
<head>
    <title>Chỉnh sửa loại sản phẩm</title>
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
                    <a href="<c:url value="/admin-home"/>">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa loại sản phẩm</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form id="formSubmit">
                        <c:if test="${not empty messageParam}">
                            <div class="alert alert-${alert}" role="alert">
                                    ${messageParam}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mã loại sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="codeCategory" name="codeCategory" value="${model.codeCategory}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên loại sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="nameCategory" name="nameCategory" value="${model.nameCategory}"/>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateNew"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm" id="btnAddOrUpdateNew"/>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" id="allCode" name="allCode" value="${allcode}">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != "allCode") {
                data["" + v.name+""] = v.value;
            }
        });
        var cat = $('#allCode').val();
        var categories = cat.substring(1, cat.length - 1);
        var arrCode = categories.split(",");
        var id = $('#id').val();
        if (checkInput(data)) {
            if (!existCode(data['codeCategory'], arrCode)) {
                if (id == "") {
                    addCategory(data);
                } else {
                    updateCategory(data);
                }
            } else {
                swal("Mã đã tồn tại!");
            }
        } else {
            swal("Hãy điền các thông tin cần thiết!");
        }
    });

    function existCode(code, arr) {
        var check = 0;
        $.each(arr, function (item){
            if (arr[item].trim() === code.trim()) {
                check =1;
                return false;
            }
        });
        return check;
    }

    function checkInput(data) {
        var flag = 1;
        $.each(data, function (item) {
            if (item != "id") {
                if (jQuery.isEmptyObject(data[item])) {
                    flag = 0;
                    return false;
                }
            }
        })
        return flag;
    }

    function addCategory(data) {
        $.ajax({
            url: '${categoryAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${categoryURL}?action=chinhsua&id="+ result.id +"&message=insert_success&page=1&limit=2&sortName=codecategory&sortBy=DESC";
            },
            error: function (error) {
                window.location.href = "${categoryURL}?action=danhsach&message=error_system&page=1&limit=2&sortName=codecategory&sortBy=DESC";
            }
        });
    }

    function updateCategory(data) {
        $.ajax({
            url: '${categoryAPI}',
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${categoryURL}?action=danhsach&id="+ result.id +"&message=update_success&page=1&limit=2&sortName=codecategory&sortBy=DESC";
            },
            error: function (error) {
                window.location.href = "${categoryURL}?action=danhsach&message=error_system&page=1&limit=2&sortName=codecategory&sortBy=DESC";
            }
        });
    }
</script>

</body>
</html>

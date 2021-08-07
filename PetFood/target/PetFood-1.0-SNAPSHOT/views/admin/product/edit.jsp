<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="productAPI" value="/api-admin-product"/>
<c:url var="productURL" value="/admin-product"/>

<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
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
                <li class="active">Chỉnh sửa sản phẩm</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form id="formSubmit" action="<c:url value="/admin-product"/>" method="POST" enctype="multipart/form-data">
                        <c:if test="${not empty messageParam}">
                            <div class="alert alert-${alert}" role="alert">
                                    ${messageParam}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại sản phẩm</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="producerCode" name="producerCode">
                                   <c:if test="${empty model.producerCode}">
                                       <option value="">Chọn nhà sản xuất</option>
                                       <c:forEach var="item" items="${producers}">
                                           <option value="${item.codeProducer}">${item.nameProducer}</option>
                                       </c:forEach>
                                   </c:if>
                                    <c:if test="${not empty model.producerCode}">
                                        <option value="">Chọn nhà sản xuất</option>
                                        <c:forEach var="item" items="${producers}" >
                                            <option value="${item.codeProducer}" <c:if test="${item.codeProducer == model.producerCode}">selected="selected"</c:if> >${item.nameProducer}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại sản phẩm</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="categoryCode" name="categoryCode">
                                    <c:if test="${empty model.categoryCode}">
                                        <option value="">Chọn loại sản phẩm</option>
                                        <c:forEach var="item" items="${categories}" >
                                            <option value="${item.codeCategory}">${item.nameCategory}</option>
                                        </c:forEach>
                                    </c:if>

                                    <c:if test="${not empty model.categoryCode}">
                                        <option value="">Chọn loại sản phẩm</option>
                                        <c:forEach var="item" items="${categories}" >
                                            <option value="${item.codeCategory}" <c:if test="${item.codeCategory == model.categoryCode}">selected="selected"</c:if> >${item.nameCategory}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mã sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="codeProduct" name="codeProduct" value="${model.codeProduct}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="nameProduct" name="nameProduct" value="${model.nameProduct}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình sản phẩm</label>
                            <div class="col-sm-9">
                                <c:if test="${not empty model.image}">
                                    <img src="${model.image}" style="cursor: pointer" onclick="$('#image').click()" id="img_url" alt="Your image" width="80px" height="60px">
                                    <input type="file" style="display: none" class="form-control" id="image" name="image" value="${model.image}" />
                                    <script>
                                        $("#image").change(function(){
                                            readURL(this);
                                        });
                                        function readURL(input) {
                                            if (input.files && input.files[0]) {
                                                var reader = new FileReader();
                                                reader.onload = function (e) {
                                                    $('#img_url').attr('src', e.target.result);
                                                }
                                                reader.readAsDataURL(input.files[0]);
                                            }
                                        }
                                    </script>
                                </c:if>
                                <c:if test="${empty model.image}">
                                    <img src="" onclick="$('#image').click()" id="editImg" alt="Your image" width="80px" height="60px">
                                    <input type="file" style="margin-bottom: 5px;" class="form-control" id="image" name="image"/>
                                    <script>
                                        $("#image").change(function(){
                                            if (document.querySelector('#image').files[0]) {
                                                $('#editImg').css({
                                                    'cursor': 'pointer',
                                                });
                                                $('#image').css({
                                                   'display': 'none'
                                                });
                                                readURL(this);
                                            }
                                        });
                                        function readURL(input) {
                                            if (input.files && input.files[0]) {
                                                var reader = new FileReader();

                                                reader.onload = function (e) {
                                                    $('#editImg').attr('src', e.target.result);
                                                }
                                                reader.readAsDataURL(input.files[0]);
                                            }
                                        }
                                    </script>
                                </c:if>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mô tả</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="description" name="description" value="${model.description}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số lượng</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="amount" name="amount" value="${model.amount}"/>
                                <script>
                                    $('#amount').change(function () {
                                        var s = $('#amount').val();
                                        if (/^\d+$/.test(s)) {
                                        } else {
                                            alert("Chỉ cho nhập số!")
                                            $("#amout   ").val("");
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá nhập</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="importPrice" name="importPrice" value="${model.importPrice}"/>
                                <script>
                                    $('#importPrice').change(function () {
                                        var s = $('#importPrice').val();
                                        if (/^\d+$/.test(s)) {
                                        } else {
                                            alert("Chỉ cho nhập số!")
                                            $("#importPrice").val("");
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá bán</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="price" name="price" value="${model.price}"/>
                                <script>
                                    $('#price').change(function () {
                                        var s = $('#price').val();
                                        if (/^\d+$/.test(s)) {
                                        } else {
                                            alert("Chỉ cho nhập số!")
                                            $("#price").val("");
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật sản phẩm" id="btnAddOrUpdateNew"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm sản phẩm" id="btnAddOrUpdateNew"/>
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
    $('#btnAddOrUpdateNew').click( async function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != "allCode") {
                data[""+v.name+""] = v.value;
            }
        });
        if (document.querySelector('#image').files[0]) {
            var file = document.querySelector('#image').files[0];
            var endcodeString  = await toBase64(file);
            data['image'] = endcodeString;
        }
        var product = $('#allCode').val();
        var products = product.substring(1, product.length - 1);
        var arrCode = products.split(",");
        var id = $('#id').val();
        if (checkInput(data)) {
            if (!existCode(data['codeProduct'], arrCode)) {
                if (id == "") {
                    addProduct(data);
                } else {
                    updateProduct(data);
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

    function addProduct(data) {
        $.ajax({
           url:  '${productAPI}',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${productURL}?action=chinhsua&id=" + result.id + "&message=insert_success&page=1&limit=4&sortName=createddate&sortBy=DESC";
            },
            error: function (error) {
                window.location.href = "${productURL}?action=chinhsua&message=error_system&page=1&limit=4&sortName=createddate&sortBy=DESC";
            }
        });
    }

    function updateProduct(data) {
        $.ajax({
            url: '${productAPI}',
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${productURL}?action=chinhsua&id=" + result.id +"&message=update_success&page=1&limit=4&sortName=createddate&sortBy=DESC";
            },
            error: function (error) {
                window.location.href = "${productURL}?action=danhsach&message=error_system&page=1&limit=4&sortName=createddate&sortBy=DESC";
            }
        });
    }

    const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });

</script>

</body>
</html>

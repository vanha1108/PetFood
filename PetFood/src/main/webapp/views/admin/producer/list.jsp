<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="producerAPI" value="/api-admin-producer"/>
<c:url var="producerURL" value="/admin-producer"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách nhà sản xuất</title>
</head>

<body>
<div class="main-content">
    <form action="#" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin-home"/>">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty messageParam}">
                            <div class="alert alert-${alert}" role="alert">
                                    ${messageParam}
                            </div>
                        </c:if>
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm nhà sản xuất' href="<c:url value="/admin-producer?action=chinhsua"/>">
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button" onclick="confirmDelete()"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa nhà sản xuất'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="checkAll"></th>
                                            <th>Mã nhà sản xuất</th>
                                            <th>Tên nhà sản xuất</th>
                                            <th>Số điện thoại</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${producerModels}">
                                                <tr>
                                                    <th><input type="checkbox" class="checkArray" id="checkbox_${item.id}" value="${item.id}"></th>
                                                    <td>${item.codeProducer}</td>
                                                    <td>${item.nameProducer}</td>
                                                    <td>${item.phoneNumber}</td>
                                                    <td>
                                                        <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                           title="Cập nhật" href='<c:url value="/admin-producer?action=chinhsua&id=${item.id}"/>'><i
                                                                class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                        </a>
                                                    </td>
                                                    <input type="hidden" id="delFlag" name="delFlag" value="${item.delFlag}">
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <input type="hidden" value="" id="page" name="page"/>
                                    <input type="hidden" value="" id="limit" name="limit"/>
                                    <input type="hidden" value="" id="sortName" name="sortName"/>
                                    <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                    <input type="hidden" value="" id="action" name="action"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->
<script>

    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    var limit = 4;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#limit').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('codeproducer');
                    $('#sortBy').val('desc');
                    $('#action').val('danhsach');
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('#checkAll').change(function (){
        if ($(this).is(":checked")) {
            $('.checkArray').prop("checked", true);
        } else {
            $('.checkArray').prop("checked", false);
        }
    });

    function confirmDelete() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function (){
            return $(this).val();
        }).get();
        if (jQuery.isEmptyObject(ids)) {
            swal("Hãy chọn mục cần xoá!");
        } else {
            data['ids'] = ids;
            swal({
                title: "Xác nhận xóa",
                text: "Bạn có chắc chắn muốn xóa hay không?",
                icon: "warning",
                buttons: [
                    'Huỷ bỏ',
                    'Xác nhận'
                ],
                dangerMode: true,
            }).then(function (isConfirm) {
                if (isConfirm) {
                    var check = $('#delFlag').val();
                    if (check != null && check === "1") {
                        deleteProducer(data);
                    } else {
                        window.location.href = "${producerURL}?action=danhsach&message=no_delete&page=1&limit=2&sortName=codeproducer&sortBy=DESC";
                    }
                }
            });
        }
    }

    function deleteProducer(data) {
        $.ajax({
            url: '${producerAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = '${producerURL}?action=danhsach&message=delete_success';
            },
            error: function (error) {
                window.location.href = '${producerURL}?action=danhsach&message=error_system';
            }
        });
    }
</script>
</body>
</html>
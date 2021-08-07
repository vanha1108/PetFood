<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var ="shoppingcartAPI" value="/api-shoppingcart"/>
<c:url var="shoppingcartURL" value="/shoppingcart"/>
<html>
<head>
    <title>Giỏ hàng</title>
</head>
<body>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="<c:url value="/trang-chu"/>">Trang chủ</a></li>
                <li class="active">Giỏ hàng</li>
            </ol>
        </div>
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Sản phẩm</td>
                    <td class="description"></td>
                    <td class="price">Giá</td>
                    <td class="quantity">Số lượng</td>
                    <td class="total">Tổng tiền</td>
                    <td></td>
                </tr>
                </thead>
                <a href="<c:url value="/thanh-toan?action=thanhtoan"/>" style="background-color: #FE980F; float: right; color: black" type="button" class="btn">
                    Mua hàng
                </a>
                </br>
                <br>
                <tbody>
                    <c:forEach var="item" items="${model}">
                        <tr>
                            <td class="cart_product">
                                <a href=""><img width="80px" height="100px" src="${item.productModel.image}" alt=""></a>
                            </td>
                            <td class="cart_description">
                                <h4><a href="">${item.productModel.nameProduct}</a></h4>
                            </td>
                            <td class="cart_price">
                                <input id="price" style="width: 120px; border: none" type="text" value="${item.productModel.price}" readonly>
                            </td>
                            <td class="cart_quantity">
                                <div class="cart_quantity_button">
                                    <a id="sum" name="sum" class="cart_quantity_up" href=""> + </a>
                                    <input class="cart_quantity_input" type="text" id="quality" name="quality" value="${item.quality}" autocomplete="off" size="2">
                                    <a id="sub" name="sub" class="cart_quantity_down" href=""> - </a>
                                </div>
                            </td>
                            <td class="cart_total">
                                <input type="text" style="width: 120px; border: none" value="${item.productModel.price * item.quality}" readonly id="totalPrice" name="totalPrice" class="cart_total_price">
                            </td>
                            <td class="cart_delete">
                                <button id="btnDeleteProduct" name="btnDeleteProduct" class="cart_quantity_delete" href=""><i class="fa fa-times"></i></button>
                            </td>
                            <input type="hidden" value="${item.productId}" id="productId" name="productId"/>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->
<script>
    $('#sum').click(function (e) {
       e.preventDefault();
        var data = {};
        $('#quality').val(parseInt($('#quality').val()) + 1);
        $('#totalPrice').val(parseInt($('#quality').val()) * parseInt($('#price').val()));
        data['productId'] = $('#productId').val();
        data['quality'] = $('#quality').val();
        updateToCart(data);
    });

    $('#sub').click(function (e){
        e.preventDefault();
        var data = {};
        if ((parseInt($('#quality').val()) - 1 ) == 0) {
            swal({
                title: "Xác nhận xóa",
                text: "Bạn có chắc chắn muốn bỏ sản phẩm này?",
                icon: "warning",
                buttons: [
                    'Không',
                    'Có'
                ],
                dangerMode: true,
            }).then(function (isConfirm) {
                if (isConfirm) {
                    data['productId'] = $('#productId').val();
                    data['quality'] = 0;
                    deleteProduct(data);
                }
            });
        } else {
            $('#quality').val(parseInt($('#quality').val()) - 1);
            $('#totalPrice').val(parseInt($('#quality').val()) * parseInt($('#price').val()));
            data['productId'] = $('#productId').val();
            data['quality'] = $('#quality').val();
            updateToCart(data);
        }
    })

    $('#btnDeleteProduct').click(function (e){
        e.preventDefault();
        var data = {};
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn bỏ sản phẩm này?",
            icon: "warning",
            buttons: [
                'Không',
                'Có'
            ],
            dangerMode: true,
        }).then(function (isConfirm) {
            if (isConfirm) {
                data['productId'] = $('#productId').val();
                data['quality'] = 0;
                deleteProduct(data);
            }
        });
    });

    function deleteProduct(data) {
        $.ajax({
            url: '${shoppingcartAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = '${shoppingcartURL}?action=giohang';
            },
            error: function (error) {
                swal("Lỗi hệ thống!");
            }
        });
    }

    function updateToCart(data) {
        $.ajax({
            url: '${shoppingcartAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            },
            error: function (error) {
                swal("Lỗi hệ thống!");
            }
        });
    }

    function addToCart(data) {
        $.ajax({
            url: '${shoppingcartAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('#quality').val(result.quality + 1);
                $('#totalPrice').val(parseInt($('#quality').val()) * parseInt($('#price').val()));
            },
            error: function (error) {
                swal("Lỗi hệ thống!");
            }
        });
    }
</script>
</body>
</html>

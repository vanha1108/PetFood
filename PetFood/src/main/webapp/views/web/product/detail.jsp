<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var ="shoppingcartAPI" value="/api-shoppingcart"/>
<c:url var="shoppingcartURL" value="/shoppingcart"/>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
</head>
<body>
<div class="col-sm-9 padding-right">
    <div class="product-details"><!--product-details-->
        <div class="col-sm-5">
            <div class="view-product">
                <img src="${model.image}" alt="" />
            </div>
            <div id="similar-product" class="carousel slide" data-ride="carousel">
            </div>

        </div>
        <div class="col-sm-7">
            <div class="product-information"><!--/product-information-->
                <h2>${model.nameProduct}</h2>
                <span>
                    <span>${model.price} $</span>
                    <label>Số lượng:</label>
                    <input id="quality" name="quality" type="text" value="1" />
                    </br>
                    <button id="btnAddToCart" name="btnAddToCart" style="background-color: #FE980F" type="button" class="btn">
                        <i class="fa fa-shopping-cart"></i>
                        Thêm vào giỏ hàng
                    </button>
                    <button id="btnBuyNow" style="background-color: #FE980F" name="btnBuyNow" type="button" class="btn">Mua ngay</button>
				</span>
                <p><b>Mô tả:</b> ${model.description}</p>
                <p><b>Loại sản phẩm:</b> ${model.categoryModel.nameCategory}</p>
                <p><b>Nhà sản xuất:</b> ${model.producerModel.nameProducer}</p>
                <input type="hidden" value="${model.id}" id="productId" name="productId"/>
            </div><!--/product-information-->
        </div>
    </div><!--/product-details-->

</div>
<script>
    $('#btnAddToCart').click(function (e) {
       e.preventDefault();
       var id = $('#productId').val();
       var quality = $('#quality').val();
       var data = {};
       data['productId'] = id;
       data['quality'] = quality;
       addToCart(data);
    });

    $('#btnBuyNow').click(function (e) {
       e.preventDefault();
        var id = $('#productId').val();
        var data = {};
        data['productId'] = id;
        data['quality'] = 1;
        buyNow(data);

    });

    function buyNow(data) {
        $.ajax({
            url: '${shoppingcartAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                swal("Thêm vào giỏ hàng thành công!");
                window.location.href ='${shoppingcartURL}?action=giohang';
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
                if (result == null) {
                    window.location.href = '/dang-nhap?action=dangnhap';
                } else {
                    swal("Thêm vào giỏ hàng thành công!");
                }
            },
            error: function (error) {
                swal("Lỗi hệ thống!");
            }
        });
    }
</script>
</body>
</html>

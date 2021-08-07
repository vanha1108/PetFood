<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var ="shoppingcartAPI" value="/api-shoppingcart"/>
<!DOCTYPE html>
<html lang="en">
<head>
   <title>Trang chủ</title>
</head>

<body>
    <div class="col-sm-9 padding-right">
        <div class="features_items"><!--features_items-->
            <c:forEach var="item" items="${model}">
                <div class="col-sm-4">
                    <div class="product-image-wrapper">
                        <div class="single-products">
                            <div class="productinfo text-center">
                                <img src="${item.image}" alt="" />
                                <h2>${item.price}</h2>
                                <p>${item.description}</p>
                                <a href="<c:url value="product?action=chitiet&id=${item.id}"/>" class="btn btn-default add-to-cart"><i class="fa fa-info-circle"></i>Chi tiết</a>
<%--                                <button id="btnAddToCart1" name="btnAddToCart" type="button" class="btn btn-default add-to-cart">--%>
<%--                                    <i class="fa fa-shopping-cart"></i>--%>
<%--                                    Thêm vào giỏ hàng--%>
<%--                                </button>--%>
                            </div>
                            <div class="product-overlay">
                                <div class="overlay-content">
                                    <h2>${item.price}</h2>
                                    <p>${item.description}</p>
                                    <a href="<c:url value="product?action=chitiet&id=${item.id}"/>" class="btn btn-default add-to-cart"><i class="fa fa-info-circle"></i>Chi tiết</a>
<%--                                    <button id="btnAddToCart_${item.id}" name="btnAddToCart" type="button" class="btn btn-default add-to-cart">--%>
<%--                                        <i class="fa fa-shopping-cart"></i>--%>
<%--                                        Thêm vào giỏ hàng--%>
<%--                                    </button>--%>
                                    <input type="hidden" value="1" id="quality" name="quality"/>
                                    <input type="hidden" value="${item.id}" id="id" name="id"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div><!--features_items-->
    </div>
<script>
    // $('#btnAddToCart').click(function (e) {
    //     e.preventDefault();
    //     var id = $('#id').val();
    //     var quality = $('#quality').val();
    //     var data = {};
    //     data['productId'] = id;
    //     data['quality'] = quality;
    //     addToCart(data);
    // });

    function addToCart(data) {
        $.ajax({
            url: '${shoppingcartAPI}',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                swal("Thêm vào giỏ hàng thành công!");
            },
            error: function (error) {
                swal("Lỗi hệ thống!");
            }
        });
    }
</script>
</body>
</html>
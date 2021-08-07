<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Thanh toán</title>
</head>
<body>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="<c:url value="trang-chu"/>">Trang chủ</a></li>
                <li class="active">Thanh toán</li>
            </ol>
        </div><!--/breadcrums-->

        <div class="shopper-informations">
            <div class="row">
                <div class="col-sm-5 clearfix">
                    <div class="bill-to">
                        <p>Thông tin</p>
                        <div class="form-one">
                            <form>
                                <input type="text" placeholder="Tên người nhận">
                                <input type="text" placeholder="Số điện thoại">
                                <input type="text" placeholder="Địa chỉ">
                                <input type="text" placeholder="Tên người nhận thay (nếu có)">
                            </form>
                        </div>
                        <div class="form-two">
                            <textarea name="message"  placeholder="Ghi chú về đơn đặt hàng của bạn, Ghi chú đặc biệt khi giao hàng" rows="16"></textarea>
                            <label><input type="checkbox"> Giao hàng theo chị đỉa hoá đơn</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="review-payment">
            <h2>Đơn hàng của bạn</h2>
        </div>
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Sản phẩm</td>
                    <td class="description"></td>
                    <td class="price">Giá</td>
                    <td class="quantity">Số lượng</td>
                    <td class="total">Thành tiền</td>
                    <td></td>
                </tr>
                </thead>
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
                                    <input class="cart_quantity_input" type="text" id="quality" name="quality" value="${item.quality}" style="border: none" autocomplete="off" size="2" readonly>
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
                    <td colspan="4">&nbsp;</td>
                    <td colspan="2">
                        <table class="table table-condensed total-result">
                            <tr>
                                <td>Tổng tiền</td>
                                <td>${total}</td>
                            </tr>
                            <tr>
                                <td>Thuế giá trị gia tăng</td>
                                <td id="tax">$2</td>
                            </tr>
                            <tr class="shipping-cost">
                                <td>Tiền ship</td>
                                <td>Miễn phí</td>
                            </tr>
                            <tr>
                                <td>Tổng cộng</td>
                                <td><span>${total + 2}</span></td>
                            </tr>
                        </table>
                    </td>
                </tbody>
            </table>
        </div>
        <div class="payment-options">
			<span>
                <label><input type="checkbox"> Thanh toán trực tiếp</label>
            </span>
            <span>
				<label><input type="checkbox"> Chuyển khoản</label>
            </span>
            <span>
                <label><input type="checkbox"> Paypal</label>
            </span>
        </div>
    </div>
</section> <!--/#cart_items-->
</body>
</html>

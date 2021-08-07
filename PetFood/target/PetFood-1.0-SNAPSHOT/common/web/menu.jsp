<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-sm-3">
    <div class="left-sidebar">
        <c:if test="${not empty categories}">
            <h2>Loại sản phẩm</h2>
            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                <c:forEach var="item" items="${categories}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="<c:url value="/phanloai?action=lsp&id=${item.id}"/>">${item.nameCategory}</a>
                            </h4>
                        </div>
                    </div>
                </c:forEach>
            </div><!--/category-products-->
        </c:if>
        <c:if test="${not empty producers}">
            <div class="brands_products"><!--brands_products-->
                <h2>Nhà sản xuất</h2>
                <div class="brands-name">
                    <ul class="nav nav-pills nav-stacked">
                        <c:forEach var="item" items="${producers}">
                            <li><a href="<c:url value="/phanloai?action=nsx&id=${item.id}"/>">${item.nameProducer}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div><!--/brands_products-->
        </c:if>
    </div>
</div>
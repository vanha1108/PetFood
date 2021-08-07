<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<header id="header">

    <!--header_top-->
    <div class="header_top">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href="#"><i class="fa fa-phone"></i> +3 39 615 916</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> nguyenvanha11899@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="<c:url value="/trang-chu"/>"><i class="ace-icon fa fa-home home-icon"></i>Trang chủ</a></li>
                            <c:if test="${not empty USERMODEL}">
                                <li><a href="#"><i class="fa fa-tachometer"></i>Xin chào ${USERMODEL.userName}</a></li>
                                <c:if test="${USERMODEL.roleId == 1}">
                                    <li><a href="<c:url value="/admin-home"/>"><i class="fa fa-tachometer"></i>Trang quản trị</a></li>
                                </c:if>
                                <li><a href="<c:url value="/thoat?action=thoat"/>"><i class="fa fa-power-off"></i>Thoát</a></li>
                            </c:if>
                            <c:if test="${empty USERMODEL}">
                                <li><a href="<c:url value="/dang-nhap?action=dangnhap"/>"><i class="fa fa-sign-in"></i>Đăng nhập</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/header_top-->

    <div class="header-bottom">
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="<c:url value="/trang-chu"/>" class="active">Trang chủ</a></li>
                            <li class="dropdown"><a href="#">Loại sản phẩm<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <c:forEach var="item" items="${categories}">
                                        <li><a href="<c:url value="/phanloai?action=lsp&id=${item.id}"/>">${item.nameCategory}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li class="dropdown"><a href="#">Nhà sản xuất<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <c:forEach var="item" items="${producers}">
                                        <li><a href="<c:url value="/phanloai?action=nsx&id=${item.id}"/>">${item.nameProducer}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li><a href="<c:url value="/shoppingcart?action=giohang"/>">Giỏ hàng</a></li>
                            <li><a href="#">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <input type="text" placeholder="Search"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<section id="slider"><!--slider-->
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#slider-carousel" data-slide-to="1"></li>
                        <li data-target="#slider-carousel" data-slide-to="2"></li>
                    </ol>

                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-sm-6">
                                <h1><span>PET FOOD</span> ONLINE</h1>
                                <h2>Nguyễn Văn Hà</h2>
                                <p>Trang web chuyên cung cấp các loại thức ăn cho vật nuôi, đảm bảo chất lượng và uy tín. </p>
                            </div>
                            <div class="col-sm-6">
                                <img src="<c:url value="/template/web/images/image1.jfif"/>" class="girl img-responsive" alt="" />
                                <img src="<c:url value="/template/web/images/image2.jfif"/>"  class="pricing" alt="" />
                            </div>
                        </div>
                        <div class="item">
                            <div class="col-sm-6">
                                <h1><span>PET FOOD</span> ONLINE</h1>
                                <h2>Nguyễn Văn Hà</h2>
                                <p>Trang web chuyên cung cấp các loại thức ăn cho vật nuôi, đảm bảo chất lượng và uy tín. </p>
                            </div>
                            <div class="col-sm-6">
                                <img src="<c:url value="/template/web/images/image3.jfif"/>" class="girl img-responsive" alt="" />
                                <img src="<c:url value="/template/web/images/image4.jfif"/>"  class="pricing" alt="" />
                            </div>
                        </div>

                        <div class="item">
                            <div class="col-sm-6">
                                <h1><span>PET FOOD</span> ONLINE</h1>
                                <h2>Nguyễn Văn Hà</h2>
                                <p>Trang web chuyên cung cấp các loại thức ăn cho vật nuôi, đảm bảo chất lượng và uy tín. </p>
                            </div>
                            <div class="col-sm-6">
                                <img src="<c:url value="/template/web/images/image5.jfif"/>" class="girl img-responsive" alt="" />
                                <img src="<c:url value="/template/web/images/image6.jfif"/>"  class="pricing" alt="" />
                            </div>
                        </div>

                    </div>

                    <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                        <i class="fa fa-angle-right"></i>
                    </a>
                </div>

            </div>
        </div>
    </div>
</section><!--/slider-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><dec:title default="Trang chủ" /></title>

    <link href="<c:url value="/template/web/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/prettyPhoto.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/price-range.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/animate.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/responsive.css"/>" rel="stylesheet">

    <script src="<c:url value="/template/web/js/html5shiv.js"/>"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/fontawesome.min.css" rel="stylesheet">

    <script type='text/javascript'
            src='<c:url value="/template/admin/js/jquery-2.2.3.min.js" />'></script>
    <script
            src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

    <%@include file="/common/web/header.jsp"%>

    <section>
        <div class="container">
            <div class="row">
                <%@include file="/common/web/menu.jsp"%>

                <dec:body/>

            </div>
        </div>
    </section>

    <%@include file="/common/web/footer.jsp"%>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"/></script>
    <script src="<c:url value="/template/web/js/jquery.js"/>"></script>

<%--    <script src="<c:url value="/template/web/js/bootstrap.min.js"/>"></script>--%>
    <script src="<c:url value="/template/web/js/jquery.scrollUp.min.js"/>"></script>
    <script src="<c:url value="/template/web/js/price-range.js"/>"></script>
    <script src="<c:url value="/template/web/js/jquery.prettyPhoto.js"/>"></script>
    <script src="<c:url value="/template/web/js/main.js"/>"></script>
</body>
</html>
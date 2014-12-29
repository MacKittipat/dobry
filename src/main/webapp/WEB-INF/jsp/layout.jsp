<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Dobry</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/materialize/css/materialize.min.css" />" media="screen,projection" />
        <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/css/style.css" />" />
    </head>
    <body>

        <div id="header" class="row z-depth-1">
            <div class="container">
                <div class="col l12">
                    <h1>
                        <a href="<spring:url value="/" />">
                            Dobry
                        </a>
                    </h1>
                </div>
            </div>
        </div>

        <div id="content" class="row">
            <div class="container">
                <jsp:include page="${pageContent}.jsp" />
            </div>
        </div>

        <script type="text/javascript" src="<spring:url value="/assets/js/jquery-2.1.3.js" />"></script>
        <script type="text/javascript" src="<spring:url value="/assets/materialize/js/materialize.min.js" />"></script>
    </body>
</html>

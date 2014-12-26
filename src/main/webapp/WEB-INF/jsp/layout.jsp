<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Dobry</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/materialize/css/materialize.min.css" />" media="screen,projection" />

</head>
<body>
<jsp:include page="${pageContent}.jsp" />
<script type="text/javascript" src="<spring:url value="/assets/js/jquery-2.1.3.js" />"></script>
<script type="text/javascript" src="<spring:url value="/assets/materialize/js/materialize.min.js" />"></script>
</body>
</html>

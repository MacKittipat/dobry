<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Dobry</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/materialize/css/materialize.min.css" />" />
</head>
<body>
<jsp:include page="${pageContent}.jsp" />
</body>
</html>

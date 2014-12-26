<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head></head>
<body>
Search for Github pull request
<form:form commandName="githubPullRequestForm" method="get">
    <div>
        <form:input path="id" placeholder="id" />
    </div>
    <div>
        <input type="submit" value="Search Pull Request"/>
    </div>
</form:form>
<c:if test="${pullRequestList != null}">
    <table>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>state</th>
        </tr>
    <c:forEach var="index" items="${pullRequestList}">
        <tr>
            <td>${index.id}</td>
            <td>${index.title}</td>
            <td>${index.state}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<c:if test="${pullRequest != null}">
    <table>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>state</th>
        </tr>
        <tr>
            <td>${pullRequest.id}</td>
            <td>${pullRequest.title}</td>
            <td>${pullRequest.state}</td>
        </tr>
    </table>
</c:if>
</body>
</html>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form commandName="gitHubPullRequestForm" method="get">
    <form:select path="repo" items="${repoMap}" />
    <input type="submit" value="Search"/>
</form:form>

<c:if test="${pullRequestList != null}">
    <table>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>status</th>
        </tr>
        <c:forEach var="pullRequest" items="${pullRequestList}">
            <tr>
                <td>${pullRequest.id}</td>
                <td>${pullRequest.title}</td>
                <td>${pullRequest.state}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
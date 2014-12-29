<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form commandName="gitHubPullRequestForm" method="get">
    <form:select path="repo" items="${repoMap}" />
    <input type="submit" value="Search"/>
</form:form>

<c:if test="${pullRequestModelList != null}">
    <table>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>status</th>
            <th>time</th>
        </tr>
        <c:forEach var="pullRequestModel" items="${pullRequestModelList}">
            <tr>
                <td>${pullRequestModel.pullRequest.id}</td>
                <td>${pullRequestModel.pullRequest.title}</td>
                <td>${pullRequestModel.pullRequest.state}</td>
                <td>${pullRequestModel.diffTime}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
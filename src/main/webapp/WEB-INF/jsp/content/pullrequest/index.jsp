<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form commandName="gitHubPullRequestForm" method="get" cssclass="row">
    <div class="input-field col l8">
        <form:select path="repo" items="${repoMap}" cssClass="blue-text" />
    </div>
    <div class="input-field col l4">
        <button class="btn waves-effect waves-light col l12" type="submit">
            View
            <i class="mdi-action-search right"></i>
        </button>
    </div>
</form:form>

<div class="col l12">
    <c:if test="${pullRequestModelList != null}">
        <div class="card white">
            <div class="card-content">
                <table class="striped blue-text">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="pullRequestModel" items="${pullRequestModelList}">
                        <tr>
                            <td>${pullRequestModel.pullRequest.id}</td>
                            <td>${pullRequestModel.pullRequest.title}</td>
                            <td>${pullRequestModel.pullRequest.state}</td>
                            <td>${pullRequestModel.diffTime}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>


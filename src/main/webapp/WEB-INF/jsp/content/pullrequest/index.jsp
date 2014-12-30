<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form:form commandName="gitHubPullRequestForm" method="get" cssclass="row">
    <div class="input-field col l6">
        <form:select path="repo" cssClass="blue-text">
            <option value="" disabled selected>Select repo</option>
            <form:options items="${repoMap}" />
        </form:select>
    </div>
    <div class="input-field col l2">
        <form:select path="state" cssClass="blue-text" items="${pullRequestStateMap}" />
    </div>
    <form:hidden path="page" value="1" />
    <div class="input-field col l4">
        <button class="btn waves-effect waves-light col l12" type="submit">
            View
            <i class="mdi-action-search right"></i>
        </button>
    </div>
</form:form>

<div class="col l12">
    <c:if test="${pullRequestPaginationModel.pullRequestModelList != null}">
        <div class="card white">
            <div class="card-content">
                <table class="striped blue-text">
                    <thead>
                    <tr>
                        <th>Number</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="pullRequestModel" items="${pullRequestPaginationModel.pullRequestModelList}">
                        <tr>
                            <td>
                                <a href="${gitHubPullRequestUrl}${pullRequestModel.pullRequest.number}">
                                    ${pullRequestModel.pullRequest.number}
                                </a>
                            </td>
                            <td>${pullRequestModel.pullRequest.title}</td>
                            <c:choose>
                                <c:when test="${pullRequestModel.pullRequest.state == 'open'}">
                                    <td class="green-text" style="text-transform: capitalize">${fn:toLowerCase(pullRequestModel.pullRequest.state)}</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="red-text accent-2" style="text-transform: capitalize">${fn:toLowerCase(pullRequestModel.pullRequest.state)}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${pullRequestModel.diffTime}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <c:if test="${pullRequestPaginationModel.previousPage != null || pullRequestPaginationModel.nextPage != null}">
                <div class="card-action">
                    <div class="row">
                        <div class="col l12 center">
                            <c:if test="${pullRequestPaginationModel.previousPage != null}">
                                <a class="btn waves-effect waves-light white-text"
                                   href="?repo=${gitHubPullRequestForm.repo}&state=${gitHubPullRequestForm.state}&page=${pullRequestPaginationModel.previousPage}">
                                    <i class="mdi-image-navigate-before left"></i>
                                    Prev
                                </a>
                            </c:if>
                            <c:if test="${pullRequestPaginationModel.nextPage != null}">
                                <a class="btn waves-effect waves-light white-text"
                                   href="?repo=${gitHubPullRequestForm.repo}&state=${gitHubPullRequestForm.state}&page=${pullRequestPaginationModel.nextPage}">
                                    Next
                                    <i class="mdi-image-navigate-next right"></i>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>
</div>


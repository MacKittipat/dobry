<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="col l12">Welcome to Dobry</h2>
<div class="col l12">
    <div class="card white">
        <div class="card-content blue-text center">
            <h2>
                Please Login
            </h2>
            <a class="btn-large waves-effect waves-light white-text col l12" href="<spring:url value="${gitHubLoginUrl}" />">
                <div>
                    Login
                    <i class="mdi-action-account-box right"></i>
                </div>
            </a>
        </div>
    </div>
</div>
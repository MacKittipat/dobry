<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <header>
    </header>
    <body>
        <form:form commandName="authForm" method="POST">
            <div>
                <div>
                    <form:input path="username" placeholder="Username"/>
                </div>
                <div>
                    <form:password path="password" placeholder="Password"/>
                </div>
                <div>
                    <input type="submit" value="Login" />
                </div>
            </div>
        </form:form>
    </body>
</html>

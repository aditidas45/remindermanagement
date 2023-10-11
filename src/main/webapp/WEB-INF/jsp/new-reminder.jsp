<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reminder Library</title>
</head>
<body>
    <div>
        <h2>New Reminder</h2>
        <div>
            <div>
                <form:form action="/add" modelAttribute="reminder" method="post">
                    <div>
                        <div>
                            <form:label path="subject">Subject</form:label>
                            <form:input type="text" id="subject" path="subject"/>
                            <form:errors path="subject" />
                        </div>
                        <div>
                            <form:label path="description">Description</form:label>
                            <form:input type="text" id="description" path="description"/>
                            <form:errors path="description" />
                        </div>
                    </div>
                    <div>
                        <div>
                            <input type="submit" value="Add Reminder">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    </body>
</html>
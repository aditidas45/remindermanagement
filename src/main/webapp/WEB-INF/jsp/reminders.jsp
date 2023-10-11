<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reminder Application</title>
</head>
<body>
    <div>
        <div>
            <h2>Welcome to the Reminder Application ${name}</h2>
            <h4>The current date and time is: <script> document.write(new Date().toLocaleDateString()); </script></h4>
            <ul class="nav navbar-nav">
            			<li><a href="/logout">Logout</a></li>
            		</ul>
            <hr/>
            <a href="/new-reminder">
                <button type="submit">set reminder</button>
            </a>
            <br/><br/>
            <div>
                <div>
                    <div>Reminder list</div>
                </div>
                <div>
                    <table>
                        <tr>
                            <th>Subject</th>
                            <th>Description</th>
                        </tr>
                        <c:forEach var="reminder" items="${reminders}">
                            <tr>
                                <td>${reminder.subject}</td>
                                <td>${reminder.description}</td>
                                <td>
                                    <a href="/${reminder.id}">Modify Reminder</a>
                                    <form action="/${reminder.id}/delete" method="post">
                                        <input type="submit" value="Delete Reminder" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>

	<h2>How to iterate list on JSP in Spring MVC</h2>

    <div align="left">
        <table border="1" cellpadding="5">
            <caption><h2>List of Customers</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
            </tr>
            <c:forEach var="user" items="${listCustomers}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.surname}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>


</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>City List</title>
</head>
<body>
<a href="index.ftlh">Back to main page</a>
<br/>
<h1>City list</h1>
<c:if test="${!empty cityList}">
  <table>
    <tr>
      <th width="50">ID</th>
      <th width="350">Name</th>
      <th width="60">Edit</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${cityList}" var="city">
      <tr>
        <td>${city.id}</td>
        <td>${city.name}</td>
        <td><a  href="c:url value="/update/${city.id}">">Edit</a></td>
        <td><a  href="c:url value="/remove/${city.id}">">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<h1>Add a City</h1>

<c:url var="addAction" value="/cities/add"/>
<form:form action="${addAction}" commandName="city">
  <table>
    <c:if test="${!empty city.name}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="ID"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" size="10" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="name">
          <spring:message text="Name"/>
        </form:label>
      </td>
      <td>
        <form:input path="name"/>
      </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty city.name}">
                <input type="submit" value="<spring:message text="Edit city"/>"/>
            </c:if>
            <c:if test="${empty city.name}">
                <input type="submit" value="<spring:message text="Add city"/>"/>
            </c:if>
        </td>
    </tr>
  </table>
</form:form>
</body>
</html>
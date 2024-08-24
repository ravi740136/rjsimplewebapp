<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h2>Welcome to the Home Page</h2>
    <p>Session ID: <%= session.getId() %></p>
    <%
java.util.Enumeration<String> attributeNames = session.getAttributeNames();
while (attributeNames.hasMoreElements()) {
    String attributeName = attributeNames.nextElement();
    out.println(attributeName + ": " + session.getAttribute(attributeName) + "<br>");
}
%>
    
    
    <p>Hello, <%= session.getAttribute("username") %>!</p>
    <a href="logout">Logout</a>
</body>
</html>

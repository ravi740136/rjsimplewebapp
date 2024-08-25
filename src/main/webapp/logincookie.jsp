<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>

    <!-- Display error message if login failed -->
    <%
        String error = request.getParameter("error");
        if ("invalid_credentials".equals(error)) {
    %>
        <p style="color: red;">Invalid username or password. Please try again.</p>
    <%
        }
    %>

    <!-- Login form -->
    <form action="logincookie" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>

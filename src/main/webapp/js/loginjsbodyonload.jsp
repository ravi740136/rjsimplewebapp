<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page onload</title>
    <script type="text/javascript">
        // Onload event handler to check default values
        function setdefaults() {
            var usernameField = document.getElementById("username");
            var passwordField = document.getElementById("password");

            // Check if the username field is empty
        /*    if (usernameField.value === "") {
                alert("Please enter your username.");
            }

            // Check if the password field is empty
            if (passwordField.value === "") {
                alert("Please enter your password.");
            }*/

            // Optionally, set default values
            if (usernameField.value === "") {
                usernameField.value = "defaultUser";
            }

            if (passwordField.value === "") {
                passwordField.value = "defaultPassword";
            }
        };
    </script>
</head>
<body onload="setdefaults()">
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

    <!-- Login form following two urls submitted to same action url 
    <form action="loginjsbodyonload.jsp" method="post" >-->
    <form method="post" >
    <!-- this is submitted to localhost:8080 
     <form action="/" method="post" >-->
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>

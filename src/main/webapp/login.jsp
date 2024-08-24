<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error-message {
            color: red;
            
        }
    </style>
    <script>
        function validateForm() {
            // Get form elements
            var username = document.getElementById('username').value;
            var password = document.getElementById('password').value;
            var errorMessage = document.getElementById('error-message');
            
            // Clear previous client-side error messages
            errorMessage.innerHTML = '';
            errorMessage.style.display = 'none';

            // Initialize the message
            var message = '';
            if (username.trim() === '') {
                message += 'Username is required.<br/>';
            }
            if (password.trim() === '') {
                message += 'Password is required.<br/>';
            }
            
            // Show client-side error message if any
            if (message) {
                errorMessage.innerHTML = message;
                errorMessage.style.display = 'block';
                return false; // Prevent form submission
            } else {
                return true; // Allow form submission
            }
        }

       /* function clearServerSideErrors() {
            var serverErrorMessage = document.getElementById('server-error-message');
            if (serverErrorMessage) {
                serverErrorMessage.innerHTML = ''; // Clear server-side message
            }
        }

        window.onload = function() {
            clearServerSideErrors();
        };*/
    </script>
</head>
<body>
    <h2>Login</h2>
    
    <!-- Display server-side validation message in red -->
    <!-- c:if test="${not empty servererrorMessage}">-->
    <!--      <p id="server-error-message" class="error-message">${servererrorMessage}</p> -->
    <!--  /c:if-->
 <p id="error-message" class="error-message"></p>
 <c:if test="${not empty servererrorMessage}">
 <p id="server-error" class="error-message">
    <%
   
    out.println(request.getAttribute("servererrorMessage") + "<br>");

%>
</p>
    </c:if>
    
    
    <!-- Error message container for client-side validation -->
    <p id="error-message" class="error-message"></p>
    
  <form action="login" method="post" onsubmit="return validateForm()">
     <!--  <form action="login" method="post">  -->
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${param.username}" /><br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" /><br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>

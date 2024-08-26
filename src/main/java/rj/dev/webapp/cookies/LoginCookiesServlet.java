package rj.dev.webapp.cookies;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/logincookie")
public class LoginCookiesServlet extends HttpServlet {

    // A simple in-memory store for users and their passwords
    private static final Map<String, String> users = new HashMap<>();

    static {
        // Adding some users for testing
        users.put("admin", "Admin@123");
        users.put("user", "User@123");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username exists and the password matches
        if (users.containsKey(username) && users.get(username).equals(password)) {
            String role = "user"; // Default role

            // Logic to determine user role (e.g., admin)
            if ("admin".equals(username)) {
                role = "admin";
            }

            // Create a cookie to store the username and role
            Cookie userCookie = new Cookie("username", username);
            Cookie roleCookie = new Cookie("userRole", role);

            // Set cookie attributes
            userCookie.setMaxAge(60 * 60 * 24); // 1 day
            roleCookie.setMaxAge(60 * 60 * 24); // 1 day

            // Add cookies to the response
            response.addCookie(userCookie);
            response.addCookie(roleCookie);

            // Generate the home page content dynamically
            response.setContentType("text/html");
            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<h1>Welcome, ").append(username).append("</h1>");
            html.append("<p>Your role: ").append(role).append("</p>");
            html.append("<a href='logoutcookie'>Logout</a>");
            html.append("</body></html>");

            response.getWriter().write(html.toString());
        } else {
            // Authentication failed, redirect back to the login page with an error message
            response.sendRedirect("logincookie.jsp?error=invalid_credentials");
        }
    }
}


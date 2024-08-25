package rj.dev.webapp.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/homecookie")
public class HomeCookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = null;
        String userRole = null;

        // Retrieve cookies from the request
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("userRole".equals(cookie.getName())) {
                    userRole = cookie.getValue();
                }
            }
        }

        // Generate HTML response based on user information
        response.setContentType("text/html");
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h1>Welcome, ").append(username).append("</h1>");
        html.append("<p>Your role: ").append(userRole).append("</p>");
        html.append("<a href='logout'>Logout</a>");
        html.append("</body></html>");

        response.getWriter().write(html.toString());
    }
}


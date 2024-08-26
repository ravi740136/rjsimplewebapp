package rj.dev.webapp.cookies;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logoutcookie")
public class LogoutCookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate cookies by setting max age to 0
        Cookie userCookie = new Cookie("username", null);
        Cookie roleCookie = new Cookie("userRole", null);
        userCookie.setMaxAge(0);
        roleCookie.setMaxAge(0);

        response.addCookie(userCookie);
        response.addCookie(roleCookie);

        // Redirect to login page
        response.sendRedirect("logincookie.jsp");
    }
}


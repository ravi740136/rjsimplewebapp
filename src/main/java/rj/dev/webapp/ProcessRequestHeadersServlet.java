package rj.dev.webapp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class ProcessRequestHeadersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the custom request headers
        String authToken = request.getHeader("Auth-Token");
        String userRole = request.getHeader("User-Role");

        // Process the headers and send a response
        if ("user1-token-456".equals(authToken) && "User".equals(userRole)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Request processed successfully with user headers.");
        } else if ("admin-token-456".equals(authToken) && "Admin".equals(userRole)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Request processed successfully with admin headers.");
        }
        
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid headers.");
        }
    }
}


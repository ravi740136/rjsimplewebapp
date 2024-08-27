package rj.dev.webapp.ajax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/checkUsername")
public class CheckUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        // For simplicity, let's assume the username "admin" is already taken.
        String message;
        try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if ("admin".equalsIgnoreCase(username)) {
            message = "Username "+ username +" is taken!";
        } else {
            message = "Username "+ username +" is available!";
        }

        // Write the response back to the AJAX request
        resp.setContentType("text/plain");
        resp.getWriter().write(message);
    }
}


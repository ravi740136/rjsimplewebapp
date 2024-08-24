package rj.dev.webapp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        StringBuilder message= new StringBuilder();
        if (username == null || username.trim().equals("")) {
        	message.append ("Enter username <br>");
          
        }
        if (password == null || password.trim().equals("")) {
        	message.append("Enter password <br>");
          
        }
        if (message.length()>0) {
        	req.setAttribute("servererrorMessage", message.toString());
          	 req.getRequestDispatcher("login.jsp").forward(req, resp);
          	 return;
        }
        // Basic authentication check (for demonstration purposes)
        HttpSession session = req.getSession();
        if ("admin".equals(username) && "Admin@123".equals(password)) {
            
            System.out.println("Session ID (LoginServlet): " + session.getId());

            session.setAttribute("username", username);
            resp.sendRedirect("home.jsp");
        } else {
        	System.out.println("setting server error");
        	req.setAttribute("servererrorMessage", "Invalid username or password.");
       	 req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        // Basic authentication check (for demonstration purposes)
        if ("admin".equals(username) && "Admin@123".equals(password)) {
           
            System.out.println("Session ID (LoginServlet): " + session.getId());

            session.setAttribute("username", username);
            resp.sendRedirect("home.jsp");
        } else {
        	System.out.println("setting server error");
        	req.setAttribute("servererrorMessage", "Invalid username or password.");
        	 req.getRequestDispatcher("login.jsp").forward(req, resp);
            
           // req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

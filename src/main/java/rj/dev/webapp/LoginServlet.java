package rj.dev.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private Map<String, String> users = new HashMap();

	@Override
	public void init() throws ServletException {
		// Initialize with some users and their credentials
		users.put("admin", "Admin@123");
		users.put("user1", "User1@123");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		StringBuilder message = new StringBuilder();
		if (username == null || username.trim().equals("")) {
			message.append("Enter username <br>");

		}
		if (password == null || password.trim().equals("")) {
			message.append("Enter password <br>");

		}

		// server side validation for blank values
		if (message.length() > 0) {
			req.setAttribute("servererrorMessage", message.toString());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}

		// Basic authentication check
		HttpSession session = req.getSession();
		if (isValidUser(username, password)) {

			System.out.println("Session ID (LoginServlet): " + session.getId());

			// generating token based on user and setting the header
			String userToken = generateUserToken(username);
			resp.setHeader("Auth-Token", userToken);
String role ="";

			// setting header user-role
			if (username.equals("admin"))
				role = "Admin";
				
			else {
				role="User";
			}
			resp.setHeader("User-Role", role);
			

            // Generate HTML response
            String htmlContent = generateHomePageHtml(role, userToken);
            PrintWriter out = resp.getWriter();
            out.println(htmlContent);
			
			//session.setAttribute("username", username);
			//resp.sendRedirect("home.jsp");
		} else {

			req.setAttribute("servererrorMessage", "Invalid username or password.");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	private String generateHomePageHtml(String userRole, String token) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<title>Home</title>");
        html.append("</head>");
        html.append("<body>");
        html.append("<h1>Welcome to the Home Page</h1>");
        html.append("<p>Your Role: ").append(userRole).append("</p>");
        html.append("<p>Your Auth Token: ").append(token).append("</p>");
        html.append("<a href='logout'>Logout</a>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }

	private String generateUserToken(String username) {
		// Implement your logic to generate a token for a regular user
		return username + "-token-456";
	}

	private boolean isValidUser(String username, String password) {
		// Validate the user by checking the hashmap
		return users.containsKey(username) && password.equals(users.get(username));
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

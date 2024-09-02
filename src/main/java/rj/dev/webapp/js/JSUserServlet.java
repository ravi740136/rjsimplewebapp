package rj.dev.webapp.js;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@WebServlet("/js/json/jsonuser")
public class JSUserServlet extends HttpServlet {
    
    // Simulate a database by storing users in a static list
    private static List<User> userList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // Read JSON data from request
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        // Parse JSON data
        JSONObject jsonData = new JSONObject(jsonBuffer.toString());
         String name = jsonData.getString("name");
        int age = jsonData.getInt("age");
        String email = jsonData.getString("email");

        // Store the user in the simulated "database"
        User user = new User(name, age, email);
        userList.add(user);

        // Create a response JSON object
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "success");
        jsonResponse.put("message", "User data saved successfully");
        jsonResponse.put("totalUsers", userList.size());

        // Respond with the newly added user details
        JSONObject savedUser = new JSONObject();
        savedUser.put("name", user.getName());
        savedUser.put("age", user.getAge());
        savedUser.put("email", user.getEmail());
        
        jsonResponse.put("savedUser", savedUser);

        // Write the JSON response
        resp.getWriter().write(jsonResponse.toString());
    }
}


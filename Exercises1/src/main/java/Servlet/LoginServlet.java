package Servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> accounts = new HashMap<String, String>();

	
	@Override
	public void init() throws ServletException {
		accounts.put("user1", "password123");
		accounts.put("user2", "password123");
	}

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
	    	response.sendRedirect("index");
	    } else {
	    	request.setAttribute("message", "Usernanme/Password is invalid");
	    	request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	}

}

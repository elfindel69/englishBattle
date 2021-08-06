package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/deconnection"}, loadOnStartup = 3)
public class DeconnectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		  throws ServletException, IOException {
	    
	    req.getSession().invalidate();
	    resp.sendRedirect("index");
	}
}

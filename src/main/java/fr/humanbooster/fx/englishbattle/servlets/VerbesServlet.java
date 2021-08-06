package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/verbes"}, loadOnStartup = 1)
public class VerbesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final VerbeService verbesService = new VerbeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		List<Verbe> verbes = verbesService.recupererVerbes();
		
		
		req.setAttribute("verbes", verbes);
		
		try {
			req.getRequestDispatcher("WEB-INF/verbes.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

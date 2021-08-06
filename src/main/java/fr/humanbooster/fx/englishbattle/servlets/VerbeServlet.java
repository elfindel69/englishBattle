package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;

import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/verbe"}, loadOnStartup = 1)
public class VerbeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final VerbeService verbesService = new VerbeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Long idVerbe = Long.parseLong(req.getParameter("id"));
		Verbe verbe = verbesService.recupererVerbe(idVerbe);
		req.getSession().setAttribute("idVerbe", idVerbe);
		
		req.setAttribute("verbe", verbe);
		
		try {
			req.getRequestDispatcher("WEB-INF/verbe.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Long id = (Long)(req.getSession().getAttribute("idVerbe"));
		Verbe verbe = verbesService.recupererVerbe(id);
		
		System.out.println("verbe à modifier: "+verbe);
		String baseVerbale = req.getParameter("baseVerbale");
		String preterit = req.getParameter("preterit");
		String pp = req.getParameter("pp");
		String traduction = req.getParameter("traduction");
		
		verbe = verbesService.modifierVerbe(id, baseVerbale, preterit, pp, traduction);
		System.out.println("verbe modifié: "+verbe);
		req.setAttribute("verbe", verbe);
		
		try {
			req.getRequestDispatcher("WEB-INF/verbeModif.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

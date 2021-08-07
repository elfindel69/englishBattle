package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/","/index"}, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JoueurService joueursService = new JoueurServiceImpl();
	private VerbeService verbesService = new VerbeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		List<Joueur> joueurs = joueursService.recupererJoueursDuHallOfFame();
		String erreur = req.getParameter("erreur");
		req.setAttribute("erreur", erreur);
		req.setAttribute("joueurs", joueurs);
		req.setAttribute("nbVerbes", verbesService.recupererNbVerbes());
		
		try {
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

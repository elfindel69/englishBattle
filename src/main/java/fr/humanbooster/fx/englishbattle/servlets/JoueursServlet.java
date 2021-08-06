package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Ville;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/joueurs"}, loadOnStartup = 3)
public class JoueursServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final JoueurService joueursService = new JoueurServiceImpl();
	private static final VilleService villesService = new VilleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Long triListe = 0L;
		if(req.getParameter("triListe") != null) {
			triListe = Long.parseLong(req.getParameter("triListe"));
		}
		
		List<Joueur> joueurs = null;
		if(triListe == 0) {
			joueurs = joueursService.recupererJoueurs();
		}
		
		if(triListe == 1) {
			joueurs = joueursService.recupererJoueurs();
			joueurs.sort(Comparator.comparing((Joueur j)->j.getVille().getNom()));
		}
		
		if(triListe == 2) {
			joueurs = joueursService.recupererJoueursDuHallOfFame();
		}
		
		req.setAttribute("joueurs", joueurs);
		
		List<Ville> villes = villesService.recupererVilles();
		req.setAttribute("villes", villes);
		try {
			req.getRequestDispatcher("WEB-INF/joueurs.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		 final Long idVille =  Long.parseLong(req.getParameter("idVille"));
		
		
		String  prenom = req.getParameter("prenom");
		
		List<Joueur> joueurs = null;
		if(idVille == 0) {
			joueurs = joueursService.recupererJoueurs();
		}else {
			joueurs = joueursService.recupererJoueurs();
			joueurs = joueurs.stream().filter((Joueur j)->j.getVille().getId().equals(idVille)).collect(Collectors.toList());
		}
		
		if(prenom != null && !prenom.isEmpty()) {
			joueurs = joueursService.recupererJoueurs();
			joueurs = joueurs.stream().filter((Joueur j)->j.getPrenom().equals(prenom)).collect(Collectors.toList());
		}
		
		req.setAttribute("joueurs", joueurs);
		
		List<Ville> villes = villesService.recupererVilles();
		req.setAttribute("villes", villes);
		try {
			req.getRequestDispatcher("WEB-INF/joueurs.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

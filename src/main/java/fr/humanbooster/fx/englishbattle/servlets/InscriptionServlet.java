package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.business.Ville;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.NiveauServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/inscription"}, loadOnStartup = 1)
public class InscriptionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NiveauService niveauxService = new NiveauServiceImpl();
	private VilleService villesService = new VilleServiceImpl();
	private JoueurService joueursService = new JoueurServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("méthode GET invoquée à: "+LocalDateTime.now());
		List<Niveau>  niveaux= null;
		niveaux = niveauxService.recupererNiveaux();
		
		if(niveaux != null) {
			req.setAttribute("niveaux", niveaux);
		}
		
		List<Ville> villes = null;
		villes = villesService.recupererVilles();
		
		if(villes != null) {
			req.setAttribute("villes", villes);
		}
		
		try {
			req.getRequestDispatcher("WEB-INF/inscription.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Joueur joueur = new Joueur();
		String prenom = req.getParameter("prenom");
		joueur.setPrenom(prenom);
		
		String nom = req.getParameter("nom");
		joueur.setNom(nom);
		
		String email = req.getParameter("email");
		joueur.setEmail(email);
		
		String password = req.getParameter("password");
		joueur.setMotDePasse(password);
		
		Long villeID = Long.parseLong(req.getParameter("ville"));
		
		
		Long niveauID = Long.parseLong(req.getParameter("niveau"));
		
		joueur = joueursService.ajouterJoueur(email, nom, prenom, password, niveauID, villeID);
		System.out.println(joueur);
		
		try {
			req.setAttribute("joueur", joueur);
			req.getRequestDispatcher("WEB-INF/merciInscription.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

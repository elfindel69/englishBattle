package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(urlPatterns = { "/inscriptionNew"}, loadOnStartup = 1)
public class InscriptionNewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NiveauService niveauxService = new NiveauServiceImpl();
	private VilleService villesService = new VilleServiceImpl();
	private JoueurService joueursService = new JoueurServiceImpl();
	
	private String cheminDeBase = System.getProperty("catalina.base");
	private String separateur = System.getProperty("file.separator");
	private String nomApplication = "englishbattleval";
	
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
			req.getRequestDispatcher("WEB-INF/inscriptionNew.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Collection<Part> parts = null;
		try {
			parts = req.getParts();
		} catch (IOException | ServletException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String prenom = null;
		String nom = null;
		String email = null;
		String password = null;
		Long niveauID = 0L;
		Long villeID = 0L;
        for (Part part : parts) {
            switch (part.getName()) {
            case "prenom":
            	prenom = req.getParameter("prenom");
                break;
            case "nom":
            	nom = req.getParameter("nom");
                break;
            case "email":
            	email = req.getParameter("email");
                break;
            case "password":
            	password = req.getParameter("password");
                break;
            case "ville":
            	villeID = Long.parseLong(req.getParameter("ville"));
                break;
            case "niveau":
            	niveauID = Long.parseLong(req.getParameter("niveau"));
                break;
            }
         }
        
        if(villeID != 0 && villeID != null) {
			if(niveauID != 0 && niveauID != null) {
				Joueur joueur = joueursService.ajouterJoueur(email, nom, prenom, password, niveauID, villeID);
				System.out.println(joueur);
				
				String image = cheminDeBase + separateur + "wtpwebapps" + separateur + nomApplication + separateur
						+ "images" + separateur + joueur.getId() + ".jpg";

				for (Part part : parts) {
					switch (part.getName()) {
					case "file":
						try {
							part.write(image);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					}
				}
				
				try {
					req.setAttribute("joueur", joueur);
					req.getRequestDispatcher("WEB-INF/merciInscription.jsp").forward(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				return;
			}
		}
        
        req.setAttribute("prenom", prenom);
		req.setAttribute("nom", nom);
		req.setAttribute("email", email);
		req.setAttribute("idVille", villeID);
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
		req.setAttribute("erreur","erreur de saisie:(");
		req.setAttribute("idNiveau", niveauID);
		try {
			req.getRequestDispatcher("WEB-INF/inscription.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}

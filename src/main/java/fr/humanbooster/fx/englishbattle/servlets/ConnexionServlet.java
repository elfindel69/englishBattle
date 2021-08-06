package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.PartieService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.PartieServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/connexion"}, loadOnStartup = 0)
public class ConnexionServlet extends HttpServlet {
	private static JoueurService joueursService = new JoueurServiceImpl();
	private static PartieService partiesService = new PartieServiceImpl();
	private static VerbeService verbesService = new VerbeServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		  throws ServletException, IOException {

		        if (verbesService.recupererNbVerbes()==0)
		        {
		            req.setAttribute("erreur", "il n'est pas possible de jouer, la base de verbes irr&eacute;guliers est vide");
		            resp.sendRedirect("index");
		        }
		        
		        // On récupère les données saisies sur le formulaire de connexion
		        String email = req.getParameter("email");
		        String motDePasse = req.getParameter("password");
		        
		        // On essaie de récupérer un joueur avec ces données
		        Joueur joueur = joueursService.recupererJoueurParEmailEtMotDePasse(email, motDePasse);
		        if (joueur != null) {
		            // Le joueur a saisi le bon email + mdp
		            joueur.setEstEnLigne(true);
		            System.out.println("Connexion de " + joueur.getPrenom());
		            joueursService.mettreAJourJoueur(joueur.getId(), joueur.getEmail(), joueur.getNom(), joueur.getPrenom(), joueur.getMotDePasse(), joueur.getVille(), joueur.getNiveau() , true);
		            // Ajout d'une partie et mise en session de cette partie
		            Partie partie = partiesService.ajouterPartie(joueur);
		           
		            // On passe le relai à la servlet QuestionServlet
		           
		        	req.getSession().setAttribute("partie", partie);
		            req.getRequestDispatcher("jeu").forward(req, resp);
		        } else {
		            req.setAttribute("erreur", "email et/ou mot de passe incorrect");
		            resp.sendRedirect("index");
		        }
		
		
		
		
	}

}

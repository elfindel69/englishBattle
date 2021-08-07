package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Question;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.exceptions.AucunVerbeException;
import fr.humanbooster.fx.englishbattle.service.QuestionService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/jeu"}, loadOnStartup = 2)
public class QuestionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VerbeService verbesService = new VerbeServiceImpl();

	private static QuestionService questionsService = new QuestionServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		  throws ServletException, IOException {
		doQuestion(req, resp);

	}
	private void doQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Partie partie = (Partie)req.getSession().getAttribute("partie");
		
	    Joueur joueur = partie.getJoueur();
        if (verbesService.recupererNbVerbes()==0)
        {
        	req.getSession().invalidate();
            req.setAttribute("erreur", "il n'est pas possible de jouer, la base de verbes irr&eacute;guliers est vide");
            req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
        }
        
       Niveau niveau = joueur.getNiveau();
       Question question = (Question) req.getSession().getAttribute("question");
        if(question == null) {
        	 Verbe verbe = null;
	            try {
	    			verbe = verbesService.recupererVerbeAleatoire(partie);
	    		} catch (AucunVerbeException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    	    	req.getSession().invalidate();
	    			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
	    		}
	            question = questionsService.ajouterQuestion(partie, verbe);
	        	req.getSession().setAttribute("question", question);
        }
        Verbe verbe = question.getVerbe();
        if(verbe != null) {
        	req.setAttribute("verbe", verbe);
        	req.setAttribute("joueur", joueur);
        	req.setAttribute("niveau", niveau);
        	
        	long sec = question.getNbSecondesRestantes();
        	String preterit = "";
        	String pp = "";
        	double rand = Math.random();
        	req.getSession().setAttribute("rand",rand);
        	if(niveau.getNom().equals("débutant") || niveau.getNom().equals("intermédiaire")) {
    			preterit = verbe.getPreterit();
    			pp = verbe.getParticipePasse();
        	}
        	
        	req.setAttribute("sec", sec);
        	req.setAttribute("preterit", preterit);
        	req.setAttribute("pp", pp);
        	System.out.println("question: "+question.getVerbe().getBaseVerbale());
        	req.getSession().setAttribute("question", question);
        	req.getSession().setAttribute("partie", partie);
        	req.getRequestDispatcher("WEB-INF/question.jsp").forward(req, resp);
        }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		  throws ServletException, IOException {
		doQuestion(req, resp);
	}
}

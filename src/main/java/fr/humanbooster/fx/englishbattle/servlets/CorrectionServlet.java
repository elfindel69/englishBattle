package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Question;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.exceptions.AucunVerbeException;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.QuestionService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/correction"}, loadOnStartup = 2)
public class CorrectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VerbeService verbesService = new VerbeServiceImpl();
	private static QuestionService questionsService = new QuestionServiceImpl();
	private static JoueurService joueursService = new JoueurServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		  throws ServletException, IOException {

        if (verbesService.recupererNbVerbes()==0)
        {
        	System.out.println("erreur");
    		
    	   
    	   
            req.setAttribute("erreur", "il n'est pas possible de jouer, la base de verbes irr&eacute;guliers est vide");
            req.getSession().invalidate();
    		resp.sendRedirect("index");
        }
        
        // On récupère les données saisies sur le formulaire de connexion
       Question question = (Question)req.getSession().getAttribute("question");
       System.out.println("test");
       
       String preterit = req.getParameter("preterit");
       String pp = req.getParameter("pp");
       System.out.println("prétérit: "+preterit);
       System.out.println("participe passé: "+pp);
       System.out.println("question id: " +question.getId());
       System.out.println("question verbe: "+question.getVerbe().getBaseVerbale());
       question =  questionsService.mettreAJourQuestion(question.getId(), preterit, pp);
       boolean res = questionsService.verifierReponse(question);
       
       if(res) {
    	   Partie partie = (Partie)req.getSession().getAttribute("partie");
    	   Joueur joueur = partie.getJoueur();
    	   System.out.println("réponse OK");
    	   Verbe verbe = null;
           try {
   				verbe = verbesService.recupererVerbeAleatoire(partie);
   			} catch (AucunVerbeException e) {
   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   			
	   	    	joueursService.definirJoueurEnLigne(joueur, false);
	   	    	req.getSession().invalidate();
	   			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
   			}
	       question = questionsService.ajouterQuestion(partie, verbe);
	       List<Question> questions = partie.getQuestions();
	       questions.add(question);
	       partie.setQuestions(questions);
	       req.getSession().setAttribute("partie", partie);
	       req.getSession().setAttribute("question", question);
		   req.getRequestDispatcher("jeu").forward(req, resp);
       }else {
    	   System.out.println("réponse KO");
    	   Partie partie = (Partie)req.getSession().getAttribute("partie");
    	   Joueur joueur = partie.getJoueur();
    	   joueursService.definirJoueurEnLigne(joueur, false);
    	   req.getSession().invalidate();
    	   resp.sendRedirect("index");
       }		       
	}

}

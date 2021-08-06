package fr.humanbooster.fx.englishbattle.servlets;

import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.NiveauServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet(urlPatterns = { "/init"}, loadOnStartup = 1)
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerbeService verbes = new VerbeServiceImpl();
	private VilleService villes = new VilleServiceImpl();
	private NiveauService niveaux = new NiveauServiceImpl();
	
	@Override
	public void init() {
		int nb = verbes.recupererNbVerbes();
		if(nb == 0) {
			boolean doImport = verbes.importerVerbes();
			if(doImport) {
				nb = verbes.recupererNbVerbes();
				System.out.println("nombre de verbes: "+nb);
			}
		}
		if(villes.recupererVilles().isEmpty()) {
			villes.ajouterVille("Lyon");
			villes.ajouterVille("Villeurbanne");
			villes.ajouterVille("Marseille");
			villes.ajouterVille("Paris");
		}
		if(niveaux.recupererNiveaux().isEmpty()) {
			villes.ajouterVille("Débutant");
			villes.ajouterVille("Intermédiaire");
			villes.ajouterVille("Expert");
		}
	}
	

}

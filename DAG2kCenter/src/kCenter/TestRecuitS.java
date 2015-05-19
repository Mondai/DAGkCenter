package kCenter;

import java.io.IOException;

public class TestRecuitS {
	
	public static ParticuleKCenter particule ;
	public static RecuitSimuleKCenter recuit ;
	public static Graphe graphe ;
	
	public static void main(String[] args) throws IOException {
		
		String lien = "data/pmed1.txt";
		int nbIter = 100 ;
		int nbRepl = 3  ;
		graphe = Traducteur.traduireOrlibPMED(lien) ;

		particule = new ParticuleKCenter(graphe, nbRepl) ;
		recuit = new RecuitSimuleKCenter(new FonctionLineaire (10,1,nbIter), nbIter) ;
		
		recuit.lancer(particule);
		
	}
}

package kCenter;

import java.io.IOException;

public class TestRecuitQ {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Paramètres kCenter
		launcher.nomGraphe="data/pmed1.txt";
		
		// Paramètres du recuit
		// launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setnbMaxIteration(10) ;
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT(1);
		launcher.setNbReplique(4);

		// Paramètres graphiques
		System.out.println("launcher créé !");
		
		// Lancement
		launcher.lancer();
		
		

	}

}

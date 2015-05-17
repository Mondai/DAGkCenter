package kCenter;

import java.io.IOException;

public class TestRecuitQ {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Param�tres kCenter
		launcher.nomGraphe="data/pmed1.txt";
		
		// Param�tres du recuit
		// launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setnbMaxIteration(10) ;
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT(1);
		launcher.setNbReplique(4);

		// Param�tres graphiques
		System.out.println("launcher cr�� !");
		
		// Lancement
		launcher.lancer();
		
		

	}

}

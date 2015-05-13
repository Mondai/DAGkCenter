package kCenter;

import java.io.IOException;

public class TestFinal {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Paramètres
		launcher.setNomGraphe("data/pmed1.txt");
		// Paramètres du recuit
		launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT( 1);
		launcher.setNbReplique(5);

		// Paramètres graphiques
		System.out.println("launcher créé !");
		
		// Lancement
		launcher.lancer();
		
		

	}

}

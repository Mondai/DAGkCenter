package kCenter;

import java.io.IOException;

public class TestFinal {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Param�tres
		launcher.setNomGraphe("data/pmed1.txt");
		// Param�tres du recuit
		launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT( 1);
		launcher.setNbReplique(5);

		// Param�tres graphiques
		System.out.println("launcher cr�� !");
		
		// Lancement
		launcher.lancer();
		
		

	}

}

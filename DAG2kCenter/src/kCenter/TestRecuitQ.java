package kCenter;

import java.io.IOException;

public class TestRecuitQ {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Param�tres kCenter
		launcher.nomGraphe="data/pmed1.txt";
		launcher.graphe=Traducteur.traduireOrlibPMED(launcher.nomGraphe);
		
		// Param�tres du recuit
		launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT(1);
		launcher.setNbReplique(10);

		// Param�tres graphiques
		
		
		// Lancement
		launcher.lancer();
		
		

	}

}

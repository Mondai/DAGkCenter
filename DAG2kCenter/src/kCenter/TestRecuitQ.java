package kCenter;

import java.io.IOException;

public class TestRecuitQ {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher = new LanceurKCenter();

		// Paramètres kCenter
		launcher.nomGraphe="data/pmed1.txt";
		launcher.graphe=Traducteur.traduireOrlibPMED(launcher.nomGraphe);
		
		// Paramètres du recuit
		launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setG0(0.75);
		launcher.setNbPalier(3);
		launcher.setT(1);
		launcher.setNbReplique(10);

		// Paramètres graphiques
		
		
		// Lancement
		launcher.lancer();
		
		

	}

}

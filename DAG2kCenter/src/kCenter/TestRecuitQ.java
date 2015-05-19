package kCenter;

import java.io.IOException;

public class TestRecuitQ {

	public static void main(String[] args) throws IOException {

		LanceurKCenter launcher ;
		
		for (int i = 1 ; i < 6 ; i++) {
		launcher = new LanceurKCenter();

		// Paramètres kCenter
		launcher.nomGraphe="data/pmed"+i+".txt";
		launcher.graphe=Traducteur.traduireOrlibPMED(launcher.nomGraphe);
		
		// Paramètres du recuit
		launcher.setnbMaxIteration(4 * launcher.graphe.k * launcher.graphe.n);
		launcher.setG0(0.75);
		launcher.setNbPalier(5);
		launcher.setT(1);
		launcher.setNbReplique(10);

		// Paramètres graphiques
		
		
		// Lancement
		System.out.println("Recuit sur "+launcher.nomGraphe);
		launcher.lancer();
		}
		

	}

}

package kCenter;

import java.io.IOException;


public class LanceurKCenter {

	String nomGraphe ;
	double G0;
	int nbPalier;
	int nbMaxIteration;
	double T;
	int nbReplique ;
	ParticuleKCenter particule ;

	public LanceurKCenter() {
	}

	// Une fois l'initialisation faite on lance

	public void lancer() throws IOException {
		long startTime = System.nanoTime();	
		RecuitQuantiqueKCenter kcentre = new RecuitQuantiqueKCenter( new FonctionLineaire(G0, 0, nbMaxIteration), T, nbMaxIteration, nbPalier ) ;
		this.particule = new ParticuleKCenter (Traducteur.traduireOrlibPMED(nomGraphe),nbReplique) ;
		kcentre.lancer(particule );
		long endTime = System.nanoTime();

		for (EtatKCenter etat : particule.etats) {
			System.out.println("Energie de l'état : " + etat.valeurEp);
		}
		System.out.println("duree = " + (endTime - startTime) / 1000000000
				+ " s");
	}

	
	public void setNbReplique(int nbReplique) {
		this.nbReplique=nbReplique;
	}

	public void setG0(double g0) {
		G0 = g0;
	}

	public void setNbPalier(int nbPalier) {
		this.nbPalier = nbPalier;
	}

	

	public void setNbMaxIteration(int nbMaxIteration) {
		this.nbMaxIteration = nbMaxIteration;
	}

	
	public void setT(double t) {
		T = t;
	}


	



	

	public void setnbMaxIteration(int maxSteps) {
		this.nbMaxIteration = maxSteps;
	}
	


}
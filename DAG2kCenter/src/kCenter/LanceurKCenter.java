package kCenter;

import java.io.IOException;

import solver.parametres.FonctionLineaire;

public class LanceurKCenter {

	// Paramètres généraux
	MutationKCenter mutation;

	// Paramètres kCenter
	int nbNoeuds;
	int nbCentres;
	String nomGraphe;
	Graphe graphe;

	// Paramètres du recuit
	int k;
	double G0;
	int nbPalier;
	int nbMaxIteration;
	double T;
	int nbReplique ;

	public LanceurKCenter() {
	}

	// Une fois l'initialisation faite on lance

	public void lancer() throws IOException {
		System.out.println("debut creation particule");
		ParticuleKCenter particule = new ParticuleKCenter (graphe, nbReplique) ;
		System.out.println("debut creation etat alea");
		particule.initEtats();
		System.out.println("debut creation temperature");
		FonctionLineaire Tparam = new FonctionLineaire(G0, 0, nbMaxIteration);
		
		System.out.println("debut creation du recuit ");
		RecuitQuantiqueKCenter kcentre = new RecuitQuantiqueKCenter(Tparam, T, nbMaxIteration, nbPalier ) ;
	
		long startTime = System.nanoTime();
		System.out.println("Début de la resolution du recuit");
		kcentre.lancer(particule);
		long endTime = System.nanoTime();

		// affichage du resultat
		for (EtatKCenter etat : particule.etats) {
			System.out.println("Energie de l'état : " + etat.valeurEp);
		}
		// System.out.println("Nombre de conflits : "+recuit.getMeilleureEnergie());
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

	
	public void setNomGraphe(int numero) throws IOException {
		setNomGraphe("data/pmed"+numero+".txt") ;
	}

	
	public void setNomGraphe(String nomGraphe) throws IOException {
		this.nomGraphe = nomGraphe;
		this.graphe = Traducteur.traduireOrlibPMED(nomGraphe);
		this.nbNoeuds=this.graphe.n;
		this.nbCentres=this.graphe.k;
		
	}


	

	public void setnbMaxIteration(int maxSteps) {
		this.nbMaxIteration = maxSteps;
	}
	


}

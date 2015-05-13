package kCenter;

import java.util.ArrayList;

import solver.parametres.Fonction;

public class RecuitQuantiqueKCenter {

	public Fonction Gamma;
	public double meilleureEnergie = Double.MAX_VALUE;
	public double temperature;
	public int nbMaxIteration;
	public int nbParPalier;
	public int mutationsTentees = 0 ;
	public int mutationsAcceptees = 0 ;
	
	
	public RecuitQuantiqueKCenter(Fonction gamma, 
			double temperature, int nbMaxIteration, int nbParPalier) {
		Gamma = gamma;
		this.temperature = temperature;
		this.nbMaxIteration = nbMaxIteration;
		this.nbParPalier = nbParPalier;
		
	}


	public void lancer(ParticuleKCenter particule) {
		
		
		int c = 0;
		mutationsTentees = 0;
		mutationsAcceptees = 0;

		double Jr = 0;
		double Ep = 0;
		double Ec = 0;
		double EpActuelle = 0;
		double EcActuelle = 0;
		double deltaE = 0;
		double deltaEp = 0;
		double deltaEc = 0;

		int nombreRepliques = particule.nombreReplique;
		
		System.out.println("cacul des etats ramdom");
		for (int i = 0; i < nombreRepliques; i++) {
			double energie = particule.etats[i].calculerEp(particule.graphe);
			if (energie < this.meilleureEnergie) {
				this.meilleureEnergie = energie;
			}
		}

		EtatKCenter etat;
		EtatKCenter next;

		double proba = 1;
		double probaAcceptation = 0;

		// tableau des indices des etats a parcourir dans un certain ordre
		ArrayList<Integer> indiceEtats = new ArrayList<Integer>();
		for (int i = 0; i < nombreRepliques; i++) {
			indiceEtats.add(i);
		}

		while (Gamma.modifierT()) {
			
			System.out.println("boucle numero "+c) ; c++ ;
			
			// Collections.shuffle(indiceEtats, particulee.gen); // melanger
			// l'ordre de parcours des indices
			Jr = -this.temperature/ 2
					* Math.log(Math.tanh(this.Gamma.t / nombreRepliques
							/ this.temperature)); // calcul de Jr pour ce
			// nombreEtapeParnombreEtapeParPalier
			
			for (Integer p : indiceEtats) {
				System.out.println("sous boucle numero "+p) ;
				
				etat = particule.etats[p];
				if (p == nombreRepliques - 1) {
					next = particule.etats[0];
				} else {
					next = particule.etats[p + 1];
				}

				for (int j = 0; j < this.nbParPalier; j++) {
					System.out.println("sous sous boucle numero "+j) ;
					particule.getMutation(particule.k, nombreRepliques, etat);
					mutationsTentees++;

					Ep = etat.valeurEp;
					Ec = particule.calculerEc(etat, next);
					
					particule.etats[p].effectuerMutation(particule.mutation);
					
					etat.calculerEp(particule.graphe);
					EpActuelle = etat.valeurEp;
					deltaEp = EpActuelle - Ep;
					EcActuelle = particule.calculerEc(etat, next);
					deltaEc = EcActuelle - Ec;
					deltaE = deltaEp / nombreRepliques - deltaEc * Jr;

					if (deltaEp < 0) {
						mutationsAcceptees++;
						if (EpActuelle < this.meilleureEnergie) { 
							this.meilleureEnergie = EpActuelle;
							if (this.meilleureEnergie == 0) { 
								return;
							}
						}
					} else if (deltaE < 0) {
						mutationsAcceptees++;
					} else {
						proba = Expo.expf(-deltaE / this.temperature);
						probaAcceptation = Math.random();
						if (proba >= probaAcceptation) {
							mutationsAcceptees++;
						} else {
							etat.annulerMutation(particule.mutation); 
							etat.valeurEp = Ep ;
						}
					}
				}
			}
		}
	}
}



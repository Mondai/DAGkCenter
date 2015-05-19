package kCenter;

import java.util.ArrayList;

public class RecuitQuantiqueDebug {

	public Fonction Gamma;
	public double meilleureEnergie = Double.MAX_VALUE;
	public double temperature;
	public int nbMaxIteration;
	public int nbParPalier;
	public int mutationsTentees = 0;
	public int mutationsAcceptees = 0;
	ParticuleKCenter particule ;

	public RecuitQuantiqueDebug(Fonction gamma, double temperature,
			int nbMaxIteration, int nbParPalier, ParticuleKCenter particule) {
		Gamma = gamma;
		this.temperature = temperature;
		this.nbMaxIteration = nbMaxIteration;
		this.nbParPalier = nbParPalier;
		this.particule=particule ;
	}

	public void lancer() {

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

		System.out.println("cacul des energies des etats ramdom");
		for (int i = 0; i < nombreRepliques; i++) {
			double energie = particule.etats[i].calculerEp(particule.graphe);
			System.out.println(particule.etats[i].valeurEp );
			if (energie < this.meilleureEnergie) {
				this.meilleureEnergie = energie;
			}
		}

		EtatKCenter etat;
		EtatKCenter next;

		double proba = 1;
		double probaAcceptation = 0;

		// tableau des indices des etats a parcourir dans un certain ordre
		/*ArrayList<Integer> indiceEtats = new ArrayList<Integer>();
		for (int i = 0; i < nombreRepliques; i++) {
			indiceEtats.add(i);
		}*/

		while (Gamma.modifierT()) {

			System.out.println("boucle temperature " + c);
			c++;

			// Collections.shuffle(indiceEtats, particulee.gen); // melanger
			// l'ordre de parcours des indices
			Jr = -this.temperature
					/ 2
					* Math.log(Math.tanh(this.Gamma.t / nombreRepliques
							/ this.temperature)); // calcul de Jr pour ce
			// nombreEtapeParnombreEtapeParPalier

			for (int p = 0 ; p < particule.nombreReplique ; p++) {
				System.out.println("     boulce etat " + p);

				etat = particule.etats[p];
				if (p == nombreRepliques - 1) {
					next = particule.etats[0];
				} else {
					next = particule.etats[p + 1];
				}

				for (int j = 0; j < this.nbParPalier; j++) {
					System.out.println("            boucle palier " + j);
				
					particule.getMutation(particule.k, particule.n, etat);
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
					
					System.out.println("              "+Ep+" "+EpActuelle+" "+deltaEp+"   "+Ec+" "+EcActuelle+" "+deltaEc+"   "+deltaE);

					if (deltaEp < 0) {
						mutationsAcceptees++;
						System.out.println("                  deltaEp < 0 ");
						if (EpActuelle < this.meilleureEnergie) {
							this.meilleureEnergie = EpActuelle;
						}
					} else if (deltaE < 0) {
						mutationsAcceptees++;
						System.out.println("                  deltaE < 0 ");
					} else {
						proba = Expo.expf(-deltaE / this.temperature);
						probaAcceptation = Math.random();
						if (proba >= probaAcceptation) {
							mutationsAcceptees++;
							System.out
									.println("                  deltaE > 0  acecepée");
						} else {
							etat.annulerMutation(particule.mutation);
							etat.valeurEp = Ep;
							System.out
									.println("                  deltaE > 0  refusée");
						}
					}
				}
			}
		}
	}
}

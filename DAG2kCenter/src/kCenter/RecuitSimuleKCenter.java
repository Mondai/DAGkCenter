package kCenter;

public class RecuitSimuleKCenter {

	public Fonction temperature;
	public double meilleureEnergie = Double.MAX_VALUE;
	
	public int nbMaxIteration;

	public int mutationsTentees = 0;
	public int mutationsAcceptees = 0;

	public RecuitSimuleKCenter(Fonction temperature,
			int nbMaxIteration) {
		this.temperature = temperature;
		this.nbMaxIteration = nbMaxIteration;
	

	}

	public void lancer(ParticuleKCenter particule) {

		
		mutationsTentees = 0;
		mutationsAcceptees = 0;

		double Ep ;
		double EpActuelle ;
		double deltaE ;

		int nombreReplique = particule.nombreReplique;

		System.out.println("cacul des energies des etats ramdom");
		for (int i = 0; i < nombreReplique; i++) {
			double energie = particule.etats[i].calculerEp(particule.graphe);
			System.out.println(particule.etats[i].valeurEp);
			if (energie < this.meilleureEnergie) {
				this.meilleureEnergie = energie;
			}
		}

		// tableau des indices des etats a parcourir dans un certain ordre
		/*
		 * ArrayList<Integer> indiceetats = new ArrayList<Integer>(); for (int i
		 * = 0; i < nombreReplique; i++) { indiceetats.add(i); }
		 */

		while (temperature.modifierT()) {



			for (EtatKCenter replique : particule.etats) {

				particule.getMutation(particule.k, particule.n, replique);
				mutationsTentees++;

				Ep = replique.valeurEp;
				System.out.println ("ep "+replique.valeurEp);
				replique.effectuerMutation(particule.mutation);
				replique.calculerEp(particule.graphe);
				EpActuelle = replique.valeurEp;
				System.out.println ("ep actuelle "+replique.valeurEp);
				deltaE = EpActuelle - Ep;


				if (Expo.expf(-deltaE / this.temperature.t) > Math.random()) {

					mutationsAcceptees++;
					System.out.println("                  mut  acecepée");
				} else {
					replique.annulerMutation(particule.mutation);
					replique.valeurEp = Ep;
					System.out.println("                  mut  refusée");
				}
			}
		}
		
		System.out.println(+mutationsAcceptees+"/"+mutationsTentees) ;
	}
}

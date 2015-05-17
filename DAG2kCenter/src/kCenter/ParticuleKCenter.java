package kCenter;

import java.util.Random;

public class ParticuleKCenter {

	Graphe graphe;
	int nombreReplique;
	EtatKCenter[] etats;
	MutationKCenter mutation;
	int k;
	int n;
	Random random;

	public ParticuleKCenter(Graphe graphe, int nbReplique) {
		this.graphe = graphe;
		this.nombreReplique = nbReplique;
		this.k = graphe.k ;
		this.n = graphe.n ;
		this.etats = new EtatKCenter[nombreReplique];
		for (int j = 0; j < nombreReplique; j++) {
			etats[j] = new EtatKCenter(graphe.k); }
		this.mutation = new MutationKCenter();
		this.random= new Random() ;
		initEtats();
	}

	public void initEtats() {
		for (int j = 0; j < nombreReplique; j++) {
				while (etats[j].positionCentres.size() < this.k ) {
					etats[j].positionCentres.add(random.nextInt(n));
			}
		}
	}

	public void getMutation(int k, int n, EtatKCenter etat) {
		this.mutation.avant = random.nextInt(k);
		int mut = random.nextInt(n);
		while (etat.positionCentres.contains(mut)) {
			mut = random.nextInt(n);
		}
		this.mutation.apres = mut;

	}

	public double calculerEc(EtatKCenter etatKCenter,
			EtatKCenter etatKCenterNext) {
		double ec = 0;
		for (int centre : etatKCenter.positionCentres) {
			double distanceMinAuCentre = Double.POSITIVE_INFINITY;
			for (int centreNext : etatKCenterNext.positionCentres) {
				if (this.graphe.distances[centre][centreNext] < distanceMinAuCentre ) {
					distanceMinAuCentre = this.graphe.distances[centre][centreNext];
				}
			}
			if (ec < distanceMinAuCentre) {
				ec = distanceMinAuCentre;
			}
		}

		return ec;
	}

}

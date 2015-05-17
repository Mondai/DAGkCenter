package kCenter;

import java.util.TreeSet;

public class EtatKCenter {

	TreeSet<Integer> positionCentres;
	int k;
	double valeurEp;

	public EtatKCenter( int k) {
		this.k = k;
		this.positionCentres= new TreeSet<Integer>();
		this.valeurEp = 0 ; 
	}
	
	
	

	public double calculerEp(Graphe graphe) {
		double ep ;
		ep = 0 ;
		double distanceAuCentre;

		for (int i = 0; i < graphe.n; i++) {
			distanceAuCentre = Double.MAX_VALUE;
			for (int centre : positionCentres) {
				if (graphe.distances[centre][i] < distanceAuCentre) {
					distanceAuCentre = graphe.distances[centre][i];
				}
			}
			if (ep < distanceAuCentre) {
				ep = distanceAuCentre;
			}
		}
		valeurEp = ep;
		return ep;

	}

	public void effectuerMutation(MutationKCenter mutation) {
		positionCentres.remove(mutation.avant);
		positionCentres.add(mutation.apres);
	}

	public void annulerMutation(MutationKCenter mutation) {
		positionCentres.remove(mutation.apres);
		positionCentres.add(mutation.avant);
	}

}


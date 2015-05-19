package kCenter;

import java.io.IOException;
import java.util.Random;



public class TestDebug {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Random random = new Random() ;
		
		Graphe graphe = Traducteur.traduireOrlibPMED("data/pmed1.txt");


		AlgoDijkstra.computeAllPaths(graphe.data, graphe.distances);
		
		/*
		ParticuleKCenter particule = new ParticuleKCenter(graphe, 4) ; 
		particule.initEtats();
		for (EtatKCenter etat : particule.etats) { 
			//for (double d : etat.positionCentres) 
			System.out.println(etat.calculerEp(graphe));			
		}
		*/
		
		EtatKCenter etat = new EtatKCenter ( graphe.k) ;
		
		while (etat.positionCentres.size() < etat.k  ) {
			etat.positionCentres.add(random.nextInt(graphe.n));
	}
		
		System.out.println("Ep non itit "+etat.valeurEp  );
		
		
		MutationKCenter mutation = new MutationKCenter(etat.positionCentres.first(), 0) ;
		
		etat.calculerEp(graphe) ;
		System.out.println("Ep "+etat.valeurEp );
		System.out.println(etat.positionCentres); 
		etat.effectuerMutation(mutation);
		etat.calculerEp(graphe) ;
		System.out.println("Ep "+etat.valeurEp );
		System.out.println(etat.positionCentres); 
		etat.annulerMutation(mutation);
		etat.calculerEp(graphe) ;
		System.out.println("Ep "+etat.valeurEp );
		System.out.println(etat.positionCentres); 
		
		
		/*for (int i = 0 ; i < graphe.n ; i ++ ) {
			for (int j =0 ; j < graphe.n ; j ++ ) {
				System.out.print(graphe.distances[i][j]+":");
			}
			System.out.println("");
		}*/

	}
}
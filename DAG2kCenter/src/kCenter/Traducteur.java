package kCenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Traducteur {

	public static Graphe traduireOrlibPMEDnumero(int n) throws IOException{
		Graphe graphe = traduireOrlibPMED("data/pmed"+n+".txt") ;
		return graphe;
	}
	
	public static Graphe traduireOrlibPMED(String lien) throws IOException {

		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(lien));
		String line = null;
		String[] temp = null;

		line = reader.readLine();
		temp = line.split("\\s");
		int n = Integer.parseInt(temp[1]) ;
		int k = Integer.parseInt(temp[3]) ;
		Graphe graphe = new Graphe(n,k);
		for (int i = 0; i<n ; i ++) {
			graphe.data[i]= new Vertex (i) ;
		}
		
		System.out.println("Vertex[] créé !");
		
		while ( (line = reader.readLine()) != null) {

			temp = line.split("\\s");
			int v1 = (Integer.parseInt(temp[1])) -1 ;
			int v2 = (Integer.parseInt(temp[2])) -1 ;
			Double d = Double.parseDouble(temp[3]);
			graphe.data[v1].adjacencies.add(new Edge(graphe.data[v2],d)) ;
			graphe.data[v2].adjacencies.add(new Edge(graphe.data[v1],d)) ;

		}
		
		System.out.println("Edges créés !");
		
		reader.close() ;
		return graphe ;
	}

}
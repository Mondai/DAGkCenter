
package kCenter;

public class Graphe {
	
	Vertex[] data;
	double[][] distances ;
	int k ;
	int n;
	
	public Graphe (int n, int k){
		this.data = new Vertex[n] ;
		this.distances = new double[n][n];
		this.k=k;
		this.n=n;
	}
	
	
}

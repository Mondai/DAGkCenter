package others;

import java.util.Random;

public class TestBidon {
	static Random random = new Random(); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c = 0 ;
		while (c < 100) {
				c++;
				
			System.out.print(random.nextInt(100)+" ");
		}
		
		
	}

}

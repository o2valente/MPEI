package Testes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MinHash{

	private int n;
	private int[] minHashValues; 
	private int[] recorde;
	private String shingle;	
	private int[] hash;
	
	public int[] func() {
		for(int i = 0; i <n; i++) {
			hash = HashFunction.func(shingle, minHashValues, recorde );
			if(minHashValues[i] > hash)
				minHashValues[i]=hash;
		}
		
		return hash; // ????
	
	}
	
	public int funcs() {
		for(int i = 0; i< elementos.length; i++){
			Elemento e = elementos [i];
			int iguais = 0;
			for(int j=0; j < numeroDeHashFunctions; j++){
			if(e.assinatura[j] == alvo.assinatura[j]) iguais++;
			}
			distânciaJaccard = (double) iguais / numeroDeHashFunctions;

			if(distânciaJaccard > 0.6){
			// São semelhantes, indicar ao utilizador
			// e.string() para buscar string original
			}
			}
	}
	

	
}
	



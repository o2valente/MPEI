package Projeto;
import java.util.*;

public class MinHash {
	private int[] recorde;
	List<String> minhash = new ArrayList<>();
	private final int shingLength = 5;
	private int numHash;
	
	public MinHash(int numHash) {
		this.numHash = numHash;
		recorde = new int[numHash];
		for(int i = 0; i < numHash; i++) {	
			recorde[i] = -1;
		}
	}
	
	public void add(String texto) {
		//String[] shingles = new String[texto.length() - shingLength];
		for(int i = 0;i < texto.length(); i++) {
			String shingle = "";
			
			for(int j = 0; j < shingLength; j++) {
				shingle += texto.charAt(i + j);
				
				for(int k = 0; k < numHash; k++) {
					int hash = HashFunction.func(shingle);
					if(recorde[])
				}
			}
			//shingles[i] = shingle;	
		}
	}
	
	
	
}

package mpei;
import java.util.*;

public class MinHash {
	private int[] record;
	List<String> minhash = new ArrayList<>();
	private final int shingLength = 5;
	private int numHash;
	private List<Elemento> elem;
	
	public MinHash(int numHash) {
		this.numHash = numHash;
		this.elem = new ArrayList<>();
		record = new int[numHash];
		for(int i = 0; i < numHash; i++) {	
			record[i] = -1;
		}
	}
	
	public void add(String texto) {

		for(int i = 0;i < texto.length(); i++) {
			String shingle = "";
			
			for(int j = 0; j < shingLength; j++) {
				shingle += texto.charAt(i + j);
				
				elem.add(GetMinHash(shingle));
				
			}	
		}
	}
	
	
	private Elemento GetMinHash(String shingle) {
		for(int k = 0; k < numHash; k++) {
			int hash = HashFunction.func(shingle);
			if(record[k] == -1) record[k] = hash;
			else if(hash < record[k])record[k] = hash;
		}
		return new Elemento(shingle,record);
	}
	
	public boolean DistanciaJaccard(String alvo, double threshold) {
		Elemento elemAlvo = GetMinHash(alvo);
		for(Elemento e : elem.toArray(new Elemento[0])){
			int isEqual = 0;
			for(int k = 0; k < numHash; k++) {
				if(e.getRecord(k) == elemAlvo.getRecord(k)) {
					isEqual++;
				}
			}
			double distJaccard = (double) (isEqual/numHash); 
			if(distJaccard > threshold) {
				//System.out.printf("A %s é semelhante a uma String existente: %s \n", alvo,e.getString());
				return true;
			}
		}
		return false;
	}
	
}

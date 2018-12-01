package mpei;

public class HashFunction { // (ax+b) % p -> a e b aleatorios, x inteiro (hashcode da string), p numero primo "grande"
	
	public static int[] func(String s, int[] a, int[] b) {
		int[] hash = new int[a.length];
		for(int i = 0; i < hash.length; i++) {
			int p = 104729; 											// primo grande
			hash[i] = (((a[i] * (s.hashCode()) + b[i]) % p) % s.length());
			//hash[i] = ((a[i] * (s.hashCode()) + b[i]) % p);
			System.out.println(hash[i]);
		}			
		return hash;
	}
}

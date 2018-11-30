package mpei;

public class BloomFilter {  // counting bloom filter
	private int[] filter;
	private int k;			// number of hash functions to be used 
	private int size;       // number of elements in the filter
	private int capacity;
	private int[] values_a;
	private int[] values_b;
	
	 
	public BloomFilter(int k, int capacity) {
		this.capacity = capacity;
		this.filter = new int[capacity];
		this.k = k;
		this.size = 0;
		this.values_a = new int[k];
		this.values_b = new int[k];
	}
	
	public void generate_values() {
		for(int i = 0; i < k; i++) {
			values_a[i] = (int) (Math.random() * 40) + 1;
			values_b[i] = (int) (Math.random() * 40) + 1;
		}
	}
	
	
	public void insert(String s) {
		int[] temp = HashFunction.func(s, values_a, values_b);;
		for (int i = 0; i < temp.length; i++) {
			if(validIndex(temp[i])) {
				if(size < capacity) {
					filter[temp[i]] += 1;	
					size++;
					System.out.println("Adicionou");
				}else {
					System.out.println("Filtro cheio!");
				}
			}else {
				System.out.println("Index invalido!");
			}
		}
	}

	private boolean validIndex(int index) {
		if(index >= 0 && index <= filter.length)
			return true;
		return false;
	}
	
	public boolean isMember(String s) { 						// isto nao ta a funcionar bem -- pensar melhor nisso
		int[] temp = HashFunction.func(s, values_a, values_b);
		for (int i = 0; i < temp.length; i++) {
			if(validIndex(temp[i])) {
				if(filter[temp[i]] >= 1){
					return true;
				}
			}
		}
		return false;
	}
	
	public void remove(String s) {
		int[] temp = HashFunction.func(s, values_a, values_b);
		for (int i = 0; i < values_a.length; i++) {
			if(validIndex(temp[i])) {
				if(filter[temp[i]] >= 1) {
					filter[temp[i]] -= 1;
					size--;
					System.out.println("Removido com sucesso!");
				}
			}
		}
	}

	public int getSize() {
		return size;
	}	
	
	public int getK() { return k; }
	
	public int getCapacity() { return capacity; }
	
	public String toString() {   // nao ta a escrever
		return "BloomFilter";
	}
	
}

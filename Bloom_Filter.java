package MPEI;					// Counting Bloom Filter
public class Bloom_Filter {
	
	private int[] bloom_filter;
	private int numHash;
	
	public Bloom_Filter(int size, int numHash){		// Inicializar o Counting Bloom Filter;
		bloom_filter = new int[size];
		this.numHash = numHash;
	}
	
	// Adicionar um elemento á String
	
	public void addElement(String value) {

		for(int i = 0; i < numHash; i++) {
			value += i;
			int hash = Math.abs(value.hashCode());
			hash = Math.abs(hash % bloom_filter.length);
			bloom_filter[hash]++;
		}
	}
	
	/*Checks if a string is a member
	*There are false positives
	*There are no false negatives
	That means the output of isMember(String,int) is either false or maybe
	*/
	public boolean contains(String elem) {
		
		boolean member = true;
		String str = elem;
		
		for(int i = 1; i <= numHash; i++) {
			str += i;
			int hash = Math.abs(str.hashCode());
			hash = Math.abs(hash % set.length);
			if(!set[hash]) {
				member = false;
				break;
			}
		}
		return member;
	}
}
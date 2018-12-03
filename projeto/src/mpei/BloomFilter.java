package mpei;

public class BloomFilter {  // counting bloom filter
	private int[] filter;
	private int k;			// number of hash functions to be used ((log2 * n)/m) -> valor otimo para k
	private int size;       // number of elements in the filter
	private int capacity;
	
	 
	public BloomFilter(int k, int capacity) {
		this.capacity = capacity;
		this.filter = new int[capacity];
		this.k = k;
		this.size = 0;
	}
	
	
	public void insert(String s) {
		int index = s.hashCode();
		filter[index] += 1;	
		size++;
	}
	
	public boolean isMember(String s) {
		return filter[s.hashCode()] >= 1;
	}
	
	public void remove(String s) {
		int index = s.hashCode();
		if(filter[index] >= 1) {
			filter[index] -= 1;
			size--;
		}	
	}


	public int getSize() {
		return size;
	}	
	
	public int getK() { return k; }
	
	public int getCapacity() { return capacity; }
	
}

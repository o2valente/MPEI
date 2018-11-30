package mpei;

public class TestBloomFilter {

	public static void main(String[] args) {
		BloomFilter bloom = new BloomFilter(1, 50);
		bloom.insert("Valente");
		
		System.out.println(bloom.isMember("Valente")); //true
		System.out.println(bloom.isMember("menino")); // false
		
		bloom.remove("Valente");
		System.out.println(bloom.isMember("Valente")); //false
		
		System.out.println(bloom.toString());

	}

}

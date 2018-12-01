package mpei;

public class TestBloomFilter {

	public static void main(String[] args) {
		BloomFilter bloom = new BloomFilter(1, 30);
		
		
		bloom.insert("valente");
		bloom.insert("valente");
		bloom.insert("valente");
		
		System.out.println(bloom.toString());
		
		System.out.println(bloom.isMember("valente")); //true
		System.out.println(bloom.isMember("menino")); // false
		
		bloom.remove("valente");
		System.out.println(bloom.isMember("valente")); //false
		
		System.out.println(bloom.toString());

	}

}

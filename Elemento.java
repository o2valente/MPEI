package mpei;

public class Elemento {
	private int[] record;
	private String string;
	
	public Elemento(String string,int[]record) {
		this.string = string;
		this.record = record;
	}

	public int getRecord(int index) {
		return record[index];
	}

	public String getString() {
		return string;
	}
}

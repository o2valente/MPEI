package Projeto;
import java.util.*;

public class Elemento {
	private List<int[]> recorde = new ArrayList<>();
	private List<String> string = new ArrayList<>();
	
	public Elemento(String string,int[]recorde) {
		this.string.add(string);
		this.recorde.add(recorde);
	}
}

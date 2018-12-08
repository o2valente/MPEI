package mpei;

import java.io.*;

public class CreateFile {

	public static void main(String[] args) throws IOException {
		File f = new File("src/mpei/phones.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		int tele, price, store = 0;
		for(int i = 0; i < 1000; i++) {
			
			store = (int) (Math.random() * 5);
			tele = (int) (Math.random() * 21);
			price = (int) (Math.random() * 600) + 200;	
			
			PHONES[] p = PHONES.values();
			STORES[] stores = STORES.values();
			
			bw.write(stores[store] + "\t" + p[tele] + "\t" + price + "\n");
			//bw.newLine();
		}
		
		
		bw.close();
		
	}

}

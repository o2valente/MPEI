package mpei;

import java.io.*;

public class CreateFile {

	public static void main(String[] args) throws IOException {
		File f = new File("src/mpei/phones.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		int tele, price = 0;
		for(int i = 0; i < 1000; i++) {
			
			tele = (int) (Math.random() * 19) + 1;
			price = (int) (Math.random() * 600) + 200;	
			
			PHONES[] p = PHONES.values();
			
			bw.write(p[tele] + "\t" + price + "\n");
			bw.newLine();
		}
		
		
		bw.close();
		
	}

}

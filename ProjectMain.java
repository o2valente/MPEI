package mpei;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProjectMain {
	
	static BloomFilter lojadaesquina = new BloomFilter(3, 1000);	//(k, capacity)
	static BloomFilter phoneHouse = new BloomFilter(3, 1000); 
	static BloomFilter cheapSales = new BloomFilter(3, 1000);
	static BloomFilter tele4u = new BloomFilter(3, 1000);
	static BloomFilter compraaqui = new BloomFilter(3, 1000);
	
	static EstocasticCounter esquinaCounter = new EstocasticCounter();
	static EstocasticCounter houseCounter = new EstocasticCounter();
	static EstocasticCounter cheapCounter = new EstocasticCounter();
	static EstocasticCounter teleCounter = new EstocasticCounter();
	static EstocasticCounter compraCounter = new EstocasticCounter();

	public static void main(String[] args)  throws IOException {
		int escolha = 0;	
		
		fillFilters();
		System.out.println(lojadaesquina.toString());
		
		//######################################################
		
		Scanner menu = new Scanner(System.in);
			
		do{
			System.out.println("|-----------------------------------------------|");
			System.out.println("|              Main do Projeto                  |");
			System.out.println("|-----------------------------------------------|");
			System.out.println("| 1 - Ver ficheiro                              |");
			System.out.println("| 2 - Ver telemoveis                            |");
			System.out.println("| 3 - Ver lojas                                 |");
			System.out.println("| 4 - Stock de cada loja                        |");
			System.out.println("| 5 - Sair do programa                          |");
			System.out.println("|_______________________________________________|");
			
			System.out.print("Escolha: ");		
			
			try {
				escolha = menu.nextInt();
			}catch(Exception e) {
				System.err.println("Insira um numero!");
			}
			
			switch(escolha) {
			case 1:	{
				readFile("phones.txt");
				escolha = 0;
				break;
			}
			
			case 2:	{
				printPhones();
				escolha = 0;
				break;
			}
			case 3:{
				printStores();
				escolha = 0;
				break;
			}
			case 4:{
				System.out.println("\n--------------------Stock-------------------");
				System.out.println("Loja da Esquina: " + esquinaCounter.getCounter() + " telemoveis disponiveis");
				System.out.println("PhoneHouse     : " + houseCounter.getCounter() + " telemoveis disponiveis");
				System.out.println("Cheap Sales    : " + cheapCounter.getCounter() + " telemoveis disponiveis");
				System.out.println("Tele4u         : " + teleCounter.getCounter() + " telemoveis disponiveis");
				System.out.println("Compra Aqui    : " + compraCounter.getCounter() + " telemoveis disponiveis");
				escolha = 0;
				break;
			}

			case 5: System.exit(0);
			default: 
				escolha = 0;
			}
			System.out.println("\n\n");
		}while(true);			
		

	}
	
	public static void readFile(String fileName) {
		Path path = Paths.get("src/mpei/" + fileName);
		try {
			List<String> lines = Files.readAllLines(path);
			lines.stream().forEach(line -> System.out.println(line));
			
		}catch(IOException e) {
			System.out.println("\nNao foi possivel ler ficheiro!");
			System.out.println(e);
		}
		
	}
	
	public static void printPhones() {
		for(PHONES p : PHONES.values()) {
			System.out.println(p);
		}
	}
	
	public static void printStores() {
		for(STORES s : STORES.values()) {
			System.out.println(s);
		}
	}
	
	public static void fillFilters() throws IOException {
		Path path = Paths.get("src/mpei/phones.txt");
		List<String> lines = Files.readAllLines(path);
		for(String line : lines) {
			String[] split = line.split("\t");
			if(split[0].equals("LOJADAESQUINA")) {
				lojadaesquina.insert(split[1] + "\t" + split[2]);
				esquinaCounter.increment();
			}
			else if(split[0].equals("PHONEHOUSE")) {
				phoneHouse.insert(split[1] + "\t" + split[2]);
				houseCounter.increment();
			}
			else if(split[0].equals("COMPRAAQUI")) {
				compraaqui.insert(split[1] + "\t" + split[2]);
				compraCounter.increment();
			}
			else if(split[0].equals("CHEAPSALES")) {
				cheapSales.insert(split[1] + "\t" + split[2]);
				cheapCounter.increment();
			}
			else if(split[0].equals("TELE4U")) {
				tele4u.insert(split[1] + "\t" + split[2]);
				teleCounter.increment();
			}
			else {
				System.err.println("Something went wrong while filling the BloomFilters!");
			}
		}
	}

}

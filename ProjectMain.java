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
	
	static MinHash minhash = new MinHash(350);

	public static void main(String[] args)  throws IOException {
		int escolha = 0;	
		
		fillFilters();
		
		//######################################################
		
		Scanner menu = new Scanner(System.in);
			
		do{
			System.out.println("\n\n");
			System.out.println("|-----------------------------------------------|");
			System.out.println("|              Main do Projeto                  |");
			System.out.println("|-----------------------------------------------|");
			System.out.println("| 1 - Ver ficheiro                              |");
			System.out.println("| 2 - Ver telemoveis                            |");
			System.out.println("| 3 - Ver stock de cada loja                    |"); 
			System.out.println("| 4 - Procurar em todas as lojas                |");
			System.out.println("| 5 - Procurar em uma loja                      |");
			System.out.println("| 6 - Sair do programa                          |");
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
				printStock();
				escolha = 0;
				break;
			}
			case 4:{
				allStores(pickPhone());
				escolha = 0;
				break;
			}
			case 5:{				
				pickStore();
				escolha = 0;
				break;
			}

			case 6: System.exit(0);
			default: 
				escolha = 0;
			}
			
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
	
	public static void fillFilters() throws IOException {
		Path path = Paths.get("src/mpei/phones.txt");
		List<String> lines = Files.readAllLines(path);
		for(String line : lines) {
			String[] split = line.split("\t");
			
			minhash.add(split[1] + "\t" + split[2]); 				// adiciona todos à minhash
			
			if(split[0].equals("LOJADAESQUINA")) {					// adiciona ao bloomFilter de cada loja
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
	
	public static void allStores(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		esquinaPhones(phone, allPhones);
		housePhones(phone, allPhones);
		compraPhones(phone, allPhones);
		cheapPhones(phone, allPhones);
		telePhones(phone, allPhones);
	}
	
	public static void esquinaStore(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		esquinaPhones(phone, allPhones);
	}
	
	public static void houseStore(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		housePhones(phone, allPhones);
	}
	
	public static void compraStore(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		compraPhones(phone, allPhones);
	}
	
	public static void cheapStore(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		cheapPhones(phone, allPhones);
	}
	
	public static void teleStore(String phone) {
		List<String> allPhones = minhash.isMember(phone);
		telePhones(phone, allPhones);
	}
	
	private static void esquinaPhones(String s, List<String> list) {
		System.out.println("\n  Loja da Esquina");
		System.out.println("--------------------");
		list.stream().filter(x -> lojadaesquina.isMember(x)).forEach(x -> System.out.println(x + "€"));
	}
	
	private static void housePhones(String s, List<String> list) {
		System.out.println("\n     PhoneHouse");
		System.out.println("--------------------");
		list.stream().filter(x -> phoneHouse.isMember(x)).forEach(x -> System.out.println(x + "€"));
	}
	
	private static void compraPhones(String s, List<String> list) {
		System.out.println("\n    Compra Aqui");
		System.out.println("--------------------");
		list.stream().filter(x -> compraaqui.isMember(x)).forEach(x -> System.out.println(x + "€"));
	}
	
	private static void cheapPhones(String s, List<String> list) {
		System.out.println("\n    Cheap Sales");
		System.out.println("--------------------");
		list.stream().filter(x -> cheapSales.isMember(x)).forEach(x -> System.out.println(x + "€"));
	}
	
	private static void telePhones(String s, List<String> list) {
		System.out.println("\n      Tele4U");
		System.out.println("--------------------");
		list.stream().filter(x -> tele4u.isMember(x)).forEach(x -> System.out.println(x + "€"));
	}
	
	private static void pickStore() {
		Scanner store = new Scanner(System.in);
		int o = 0;
		
		System.out.println("\n|--------------------------------|");
		System.out.println("| Em que loja pretende procurar? |");
		System.out.println("|--------------------------------|");
		System.out.println("| 1 - Loja da Esquina            |");
		System.out.println("| 2 - PhoneHouse                 |");
		System.out.println("| 3 - Compra Aqui                |");
		System.out.println("| 4 - Cheap Sales                |");
		System.out.println("| 5 - Tele4U                     |");
		System.out.println("|________________________________|");
		
		System.out.println("Loja: ");
		
		try {
			o = store.nextInt();
		}catch(Exception e) {
			System.err.println("Insira um numero!");
		}
		
		switch(o) {
		case 1:	{
			esquinaStore(pickPhone());
			o = 0;
			break;
		}
		case 2:	{
			houseStore(pickPhone());
			o = 0;
			break;
		}
		case 3:	{
			compraStore(pickPhone());
			o = 0;
			break;
		}
		case 4:	{
			cheapStore(pickPhone());
			o = 0;
			break;
		}
		case 5:	{
			teleStore(pickPhone());
			o = 0;
			break;
		}
		default: 
			o = 0;
		}
		
	}
	
	private static String pickPhone() {
		Scanner sc = new Scanner(System.in); 
		System.out.println("\nTelemovel que pretende procurar: ");
		String tele = sc.nextLine();
		
		return tele.toUpperCase();
	}
	
	private static void printStock() {
		System.out.println("\n--------------------Stock-------------------");
		System.out.println("Loja da Esquina : " + esquinaCounter.getCounter() + " telemoveis disponiveis");
		System.out.println("PhoneHouse      : " + houseCounter.getCounter() + " telemoveis disponiveis");
		System.out.println("Cheap Sales     : " + cheapCounter.getCounter() + " telemoveis disponiveis");
		System.out.println("Tele4u          : " + teleCounter.getCounter() + " telemoveis disponiveis");
		System.out.println("Compra Aqui     : " + compraCounter.getCounter() + " telemoveis disponiveis");
	}

}

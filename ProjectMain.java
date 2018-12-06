import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class ProjectMain {

	public static void main(String[] args)  throws IOException {
		int escolha = 0;
		
		Scanner menu = new Scanner(System.in);
			
		do{
			System.out.println("|-----------------------------------------------|");
			System.out.println("|              Main do Projeto                  |");
			System.out.println("|-----------------------------------------------|");
			System.out.println("| 1 - Ver Ficheiro                              |");
			System.out.println("| 2 - Ver Telemóveis                            |");
			System.out.println("| 3 - Ver Lojas                                 |");
			System.out.println("| 4 - Sair do programa                          |");
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

			
			case 4: System.exit(0);
			default: 
				escolha = 0;
			}
			
		}while(true);			
		

	}
	
	public static void readFile(String fileName) {
		Path path = Paths.get("src/" + fileName);
		try {
			List<String> readAllLines = Files.readAllLines(path);
			for(String line : readAllLines) {
				System.out.println(line);
			}
		}catch(IOException e) {
			System.out.println("Não foi possível ler ficheiro!");
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

}

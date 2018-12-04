package mpei;

import java.util.*;

public class Teste_BF {
	
	static Bloom_Filter bf = new Bloom_Filter(1000,3);
	
	
	public static void main(String[] args) {	
		int escolha;
		
		Scanner menu = new Scanner(System.in);
			
		do{
			System.out.println("|-----------------------------------------------|");
			System.out.println("|              Teste BloomFilter                |");
			System.out.println("|-----------------------------------------------|");
			System.out.println("| 1 - Adicionar valores.                        |");
			System.out.println("| 2 - Verificar se valor pertence.              |");
			System.out.println("| 3 - Contar quantas vezes aparece o valor.     |");
			System.out.println("| 4 - Testar BloomFilter com strings aleatorias |");
			System.out.println("| 5 - Sair do programa                          |");
			System.out.println("|_______________________________________________|");
			
			escolha = menu.nextInt();
			
			switch(escolha) {
			
			case 1:	{
				addValores();
				escolha = 0;
				break;
			}
			case 2:	{
				verificar();
				escolha = 0;
				break;
			}
			case 3: {
				contar();
				escolha = 0;
				break;
			}
			case 4: {
				testBloom();
				escolha = 0;
				break;
			}
			case 5: System.exit(0);
			default: 
				escolha = 0;
			}
			
		}while(true);			
		
	}
	
	public static void addValores() {
		Scanner introduz = new Scanner(System.in);
		String valor;
		
		System.out.println("Valor: ");
		valor = introduz.next();
		bf.addElement(valor);
		introduz.close();
	}

	public static void verificar() {
		Scanner verScan = new Scanner(System.in);
		String verificar;
		
		System.out.println("Verificar: ");
		verificar = verScan.next();
		
		if(bf.contains(verificar) == false) {
			
			System.out.println("Nao contem");
		}
		else {
			System.out.println("Contem");
		}
		
		verScan.close();
	}
	
	public static void contar() {
		Scanner count = new Scanner(System.in);
		String contar;
		System.out.println("Valor: ");
		
		contar = count.next();
		bf.counterBloom_Filter(contar);
		
		count.close();
	}
	
	private static String randomString(int size) {
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
		String s = "";
		
		for(int i = 0; i < size; i++) {
			int rand = (int) Math.random() + alphabet.length() - 1;
			char c = alphabet.charAt(rand);
			s += "" + c;
		}
		
		return s;
	}
	
	public static void testBloom() {
		System.out.println("Testar BloomFilter");
		
		for(int i = 0; i < 100; i++) {
			String temp = randomString(50);
			bf.addElement(temp);
		}
		
		int falsePositives = 0;
		for(int i = 0; i < 100000; i++) {
			String rand = randomString(40);
			if(bf.contains(rand)) {
				falsePositives++;
			}
		}
		
		System.out.println("Falsos positivos em 100000 strings aleatorias: " + falsePositives);
	}
}

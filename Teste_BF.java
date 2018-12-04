package Projeto;

import java.util.*;

public class Teste_BloomFilter {
	
	static List<String> valores = new ArrayList<>();
	static Bloom_Filter bf = new Bloom_Filter(1000,3);
	
	
	public static void main(String[] args) {
		int escolha = 0;
		
		while(escolha == 0) {
			escolha = menu();
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
			case 4: System.exit(0);
			default: escolha = 0;
			}
		}
		
		
	}
	public static int menu() {
		Scanner menu = new Scanner(System.in);
		
		int escolha;
		System.out.println("-----------Teste------------");
		System.out.println("1- Adicionar valores.");
		System.out.println("2- Verificar se valor pertence.");
		System.out.println("3- Contar quantas vezes aparece o valor.");			System.out.println("4 - Sair do programa");
		escolha = menu.nextInt();
		return escolha;
	}
	
	public static void addValores() {
		Scanner introduz = new Scanner(System.in);
		String valor;
		
		System.out.println("Valor: ");
		valor = introduz.next();
		bf.addElement(valor);
	}

	public static void verificar() {
		Scanner verScan = new Scanner(System.in);
		String verificar;
		
		System.out.println("Verificar: ");
		verificar = verScan.next();
		
		if(bf.contains(verificar) == false) {
			
			System.out.println("Não contém");
		}
		else {
			System.out.println("Contém");
		}
	}
	
	public static void contar() {
		Scanner count = new Scanner(System.in);
		String contar;
		System.out.println("Valor: ");
		
		contar = count.next();
		bf.counterBloom_Filter(contar);
	}

}

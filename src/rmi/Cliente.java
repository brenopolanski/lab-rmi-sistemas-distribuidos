package rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		String host = "127.0.0.1";
		
		if (args.length == 1) {
			host = args[0];
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		System.out.println("Deseja fazer algum c�lculo (S/N)?");
	   	String resultado = new Scanner(System.in).next();
	   	
	   	do {
			System.out.print("Digite o primeiro valor inteiro para o c�lculo: ");
			int num1 = new Scanner(System.in).nextInt();
			
			System.out.print("Digite o segundo valor inteiro para o c�lculo: ");
			int num2 = new Scanner(System.in).nextInt();
			
			System.out.println("Qual o tipo da opera��o?");
			System.out.println("Soma(+) | Subtra��o(-) | Multiplica��o(*) | Divis�o(/) | Pot�ncia(^)");
		   	String operador = new Scanner(System.in).nextLine();
			
			String nomeRemoto = "//" + host + "/Servidor";
			
			ServidorRemoto servidor = (ServidorRemoto) Naming.lookup(nomeRemoto);
			
			//escreve mensagem no servidor, chamando m�todo dele
			servidor.escreveMsg("Hello, fellows!!!!");
			
			//recebe a data de hoje do servidor, executando m�todo l� nele
			Date dataDeHoje = servidor.dataDeHoje();
			//System.out.println("A data/hora do servidor �: " + dataDeHoje.toString());
			
			System.out.println("");
			System.out.println(num1 + " " + operador + " " + num2 + " = " +
					servidor.calcular(num1, num2, operador));			
			System.out.println("");
			
			System.out.println("Deseja fazer outro c�lculo (S/N)?");
			resultado = new Scanner(System.in).next();
	   	} while (!resultado.equals("n") && !resultado.equals("N"));
	}
}
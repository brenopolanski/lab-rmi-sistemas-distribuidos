package rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Servidor extends UnicastRemoteObject implements ServidorRemoto {
	
	public Servidor() throws RemoteException {}
	
	public void escreveMsg (String msg)throws RemoteException {
		System.out.println(msg);
	}
	
	public Date dataDeHoje() throws RemoteException {
		return new Date();
	}
	
	public int calcular(int num1, int num2, String operador) throws RemoteException {
		int resultado = 0;
		
		switch (operador) {
		case "+":
			resultado = num1 + num2;
			break;
		case "-":
			resultado = num1 - num2;
			break;
		case "*":
			resultado = num1 * num2;
			break;
		case "/":
			resultado = num1 / num2;
			break;
		case "^":
			resultado = (int) Math.pow(num1, num2);
			break;			
		default:
			System.out.println("Este não é um operador válido!");
			break;
		}
		
		return resultado;
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException{	
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {      
			  //Fazer o registro para a porta desejado
			  java.rmi.registry.LocateRegistry.createRegistry(1099);
			  System.out.println("RMI registry ready.");
			} catch (Exception e) {
			  System.out.println("Exception starting RMI registry:");
			  e.printStackTrace();
			}
				
		Servidor servidor = new Servidor();
		
		Naming.rebind("Servidor", servidor);
	}
}
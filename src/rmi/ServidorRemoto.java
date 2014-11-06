package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ServidorRemoto extends Remote {
	public void escreveMsg (String msg) throws RemoteException;
		
	public Date dataDeHoje() throws RemoteException;
	
	public int calcular(int num1, int num2, String operacao) throws RemoteException;
}
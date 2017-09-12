package com.br.ufc.lista1.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;

public class Server{
	protected static List<User> usuariosConectador = new ArrayList<User>();
	protected static Long cont = 0l; 
	public static void main (String args[]) {
		try{
			ServerSocket listenSocket = new ServerSocket(Constants.PORTA);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				User u = inputUser(clientSocket);
				OuvirMensagens mensagens = new OuvirMensagens(u);
				//Connection c = new Connection(clientSocket,u);
			}
		} catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
	}
	private static User inputUser(Socket socketClient) {
		ObjectOutputStream saida;
		ObjectInputStream entrada;
		Socket clientSocket;
		
    	try {
    		clientSocket = socketClient;
			saida = new ObjectOutputStream(clientSocket.getOutputStream());		 
	    	entrada = new ObjectInputStream(clientSocket.getInputStream());
	    	
	    	User novo = (User) entrada.readObject();
	    	saida.writeObject(usuariosConectador);
	    	novo.setId(cont++);
	    	novo.setSocket(clientSocket);
	    	usuariosConectador.add(novo);
	    	return novo;
    	} catch (Exception e) {
    		System.out.println("IO:"+e.getMessage());
		}
    	return new User("ERRO");
	}
		
}
	

class OuvirMensagens extends Thread {
	DataInputStream inputStream;
	DataOutputStream outputStream;
	
	DataOutputStream novoOutputStream; //saida para o outro socket
	DataInputStream novoInoutStream; //entrada para o outro socket
	User meuUser;
	public OuvirMensagens(User user) {
		meuUser = user;
		try {
		inputStream = new DataInputStream(user.getSocket().getInputStream());		
		outputStream = new DataOutputStream(user.getSocket().getOutputStream());
		} catch (IOException e) {
			System.err.println("IO"+ e.getMessage());
		}
		this.start();		 
	}
	@Override
	public void run() {
		while(true) {
			try {
				String msg = null;
				msg = inputStream.readUTF();
				User user = null;
				if(msg.split(" ")[0].equals("INICIO")) {
					try {
						user = Server.usuariosConectador.get(Integer.parseInt(msg.split(" ")[1]));
						if(user != null) {
							novoOutputStream = new DataOutputStream(user.getSocket().getOutputStream());
							novoOutputStream.writeUTF("INICIO2 "+meuUser.getId()); //sckt cliente 
						}
					} catch(Exception e) {
						System.err.println("Usuário não existe");
					}
				} else {
					msg = inputStream.readUTF();					
					if(msg.split(" ")[0].equals("INICIO2")){
						try {
							user = Server.usuariosConectador.get(Integer.parseInt(msg.split(" ")[1]));
							if(user != null) {
								novoOutputStream = new DataOutputStream(user.getSocket().getOutputStream());
							}
						} catch(Exception e) {
							System.err.println("Usuário não existe");
						}
					}
				}
				while(true) {
					msg = inputStream.readUTF();
					if(msg.split(" ")[0].equals("CALC")) {
						Calculadora calc = new Calculadora();
						String resultado = calc.calc(msg);
						outputStream.writeUTF(resultado);
					}else
						novoOutputStream.writeUTF(user.getNome()+ ": "+msg);					
				}
			} catch (IOException e) {
				System.err.println("IO: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}

class Calculadora {
	String calc(String msg) {
		Double numA = Double.parseDouble(msg.split(" ")[1]);
		String operacao = msg.split(" ")[2];
		Double numB = Double.parseDouble(msg.split(" ")[3]);
		String resultado = null;
		switch(operacao) {
			case "+" : resultado = String.valueOf(numA + numB);break;
			case "-" : resultado = String.valueOf(numA - numB);break; 
			case "*" : resultado = String.valueOf(numA * numB);break;
			case "/" : resultado = String.valueOf(numA / numB);break;
			default :  resultado = "";
		}
		return resultado;

	}
}
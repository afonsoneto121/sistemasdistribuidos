package com.br.ufc.lista1.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cliente {	
	private static ObjectOutputStream saida;
	private static ObjectInputStream entrada;
	
	private static DataInputStream inText;
	private static DataOutputStream outText;
	private Socket s = null;
	
	public Cliente() {
		try {
			s = new Socket(Constants.IP, Constants.PORTA);
			saida = new ObjectOutputStream(s.getOutputStream());
		
			entrada = new ObjectInputStream(s.getInputStream());
		
			inText = new DataInputStream(s.getInputStream());
			outText = new DataOutputStream(s.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void execulta() {	
		try{				    	
	    	String nome = JOptionPane.showInputDialog(null,"Digite seu nome ");
	    	
	    	saida.writeObject(new User(nome));
	    	List<User> listClients = searchUsers(nome);
	    		    	
	    	int numberUser = showUsers(listClients);
	    	
	    	if(numberUser != -1) {
		    	outText.writeUTF("INICIO "+numberUser); //Iniciou a conversa
		    	System.out.println("Mandou");
	    	} else {	    		
	    		outText.writeUTF("ERRO"); 
	    		String txt = inText.readUTF();
		    	outText.writeUTF(txt);
	    		
	    	}
	    } catch (Exception e) { 
	    	System.out.println("IO:"+e.getMessage());
		} 
		
  	}

	private static int showUsers(List<User> listClients) {
		String texto = "";
		List<Long> listId = new ArrayList<Long>();
		for (int i = 0; i < listClients.size(); i++) {
			texto += listClients.get(i).getId() +":"+listClients.get(i).getNome()+"\n";
			listId.add(listClients.get(i).getId());
		}
		if(listClients.size() == 0) {
			JOptionPane.showMessageDialog(null, "Esperando novos usuários");
			return -1;
		}else {
			try{
				int in = -2;		
				while(!(listId.contains( (long)in))) {
					in = Integer.parseInt(JOptionPane.showInputDialog(null,"Escolha o id de usuário ou -1 para sair \n "+texto));
					if(in == -1) break;
				}
				return in;
			} catch (NumberFormatException e) {
				System.err.println("Formato inválido");
			}
		}
		return -1;
	}
	public boolean enviar(String texto) {
		try {
			outText.writeUTF(texto);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO: enviar " + e.getMessage());
		}
		return false;

	}
	
	public String receber() {
		try {
			return inText.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO: receber " + e.getMessage());
		}
		return null;
	}
	private static List<User> searchUsers(String nome) throws Exception {
    	List<User> listClients = (List<User>) entrada.readObject();
    	return listClients;
	}
	public void close() {
		if(s!=null) { 
			try { 
				s.close(); 
			} catch (IOException e) { 
				System.out.println("close:"+e.getMessage()); 
			} 
		}

	}
	
}	
	


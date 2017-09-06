package com.br.ufc.lista1.calculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class CalculadoraClient {
	private static Double numA;
	private static Double numB;
	private static String operacao;
	public static boolean input() {
		try {
			String input = JOptionPane.showInputDialog(null, "Digite a operação (ex: a + b)");
			numA = Double.parseDouble(input.split(" ")[0]);
			operacao = input.split(" ")[1];
			numB = Double.parseDouble(input.split(" ")[2]);
			return true;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Entrada mal formatada");
			return false;
		}
		
		
	}
	
	public static void main(String[] args) {
		while(!input());
		
		Socket s = null;
	    try{
		   	s = new Socket(Constants.IP, Constants.PORTA);
		   	DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream( s.getOutputStream());
			out.writeUTF(numA+" "+operacao+" "+numB);        	// UTF is a string encoding see Sn 4.3
			String data = in.readUTF();
			if(data.equals(""))
				JOptionPane.showMessageDialog(null, "Operador Invalido");
			else
				JOptionPane.showMessageDialog(null, data);


	    } catch (UnknownHostException e) {
	    	System.out.println("Sock:"+e.getMessage()); 
	    } catch (EOFException e){
	    	System.out.println("EOF:"+e.getMessage());
	    } catch (IOException e){ 
	    	System.out.println("IO:"+e.getMessage());
		} finally { 
			if(s!=null) { 
				try { 
					s.close();
				} catch (IOException e) { 
					System.out.println("close:"+e.getMessage()); 
				} 
			}
		}
	}
}

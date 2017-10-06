package com.br.ufc.lista1.calculadora;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class ClienteUDP {
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
		
		DatagramSocket datagramSocket = null;
		
	    try{
	    	datagramSocket = new DatagramSocket();
	    	InetAddress host = InetAddress.getByName(Constants.IP);
	    	
			byte [] msg = (numA+" "+operacao+" "+numB).getBytes();
			DatagramPacket reqPacket = new DatagramPacket(msg, msg.length,host,Constants.PORTA);
			datagramSocket.send(reqPacket);
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			datagramSocket.setSoTimeout(1000);
			boolean a = true;
			while(a) {
				try {
					datagramSocket.setSoTimeout(3000);
					datagramSocket.receive(reply);
					a = false;
				} catch(SocketTimeoutException e) {					
					e.getStackTrace();
					System.out.println("tenta enviar");
					datagramSocket.send(reqPacket);
				}
			}
			String res = new String(reply.getData());
			if(res.equals(""))
				JOptionPane.showMessageDialog(null, "Operador Invalido");
			else
				JOptionPane.showMessageDialog(null, res.trim());

	    } catch (UnknownHostException e) {
	    	System.out.println("Sock:"+e.getMessage()); 
	    } catch (EOFException e){
	    	System.out.println("EOF:"+e.getMessage());
	    } catch (IOException e){ 
	    	System.out.println("IO:"+e.getMessage());
		} finally { 
			if(datagramSocket != null) { 
				datagramSocket.close();
			}
		}
	}

}



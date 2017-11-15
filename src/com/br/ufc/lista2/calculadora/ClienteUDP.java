package com.br.ufc.lista2.calculadora;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class ClienteUDP {
	private DatagramSocket datagramSocket = null;
	private InetAddress host = null;
	
	public ClienteUDP() {				
	    try{
	    	datagramSocket = new DatagramSocket();
	    	host = InetAddress.getByName(Constants.IP);    	
			
	    } catch (UnknownHostException e) {
	    	System.out.println("Sock:"+e.getMessage()); 
	    } catch (IOException e){ 
	    	System.out.println("IO:"+e.getMessage());
		}

	}
	
	public void sendRequest(String mensagem) {	
		byte [] msg = mensagem.getBytes();
		DatagramPacket reqPacket = new DatagramPacket(msg, msg.length,host,Constants.PORTA);
		try {
			datagramSocket.send(reqPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getResponse() {
		byte[] buffer = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	

		try {
			datagramSocket.setSoTimeout(3000);
			datagramSocket.receive(reply);
			return new String(reply.getData());
		} catch(SocketTimeoutException e) {					
			e.getStackTrace();
			System.out.println("tenta enviar");
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return "FAIL";
	}
	public void close () {
		if(datagramSocket != null) { 
			datagramSocket.close();
		}
	}	
}



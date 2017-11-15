package com.br.ufc.lista2.calculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerUDP {
	
	DatagramSocket datagramSocket;
	DatagramPacket request;
	public ServerUDP() {
		try {
			datagramSocket = new DatagramSocket(Constants.PORTA);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) throws Exception {
		ServerUDP server = new ServerUDP();
		String resultado;
		while (true) {
			String input = server.getRequest();
			
			Despachante despachante = new Despachante();
			resultado  = despachante.invoke(input);
			
			server.sendResponse(resultado);
		}
			
	}

	public String getRequest() {
		byte[] msg = new byte[1000];
		request = new DatagramPacket(msg, msg.length);
		try {
			datagramSocket.receive(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input = new String(msg);		
		return input;
	}
	public void sendResponse(String mensagem) {
		byte[] msgSend = new byte[1000];
		msgSend = mensagem.getBytes();
		DatagramPacket reply = new DatagramPacket(msgSend, 
			   	msgSend.length, request.getAddress(), request.getPort());
		try {
			datagramSocket.send(reply);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

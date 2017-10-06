package com.br.ufc.lista1.calculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUDP {
	private static String resultado;
	private static Double numA;
	private static Double numB;
	private static String operacao;
	public static void main(String argv[]) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket(Constants.PORTA);
		int cont=0;
		while (true) {
			byte[] msg = new byte[1000];
			DatagramPacket request = new DatagramPacket(msg, msg.length);
			datagramSocket.receive(request);
			String input = new String(msg);
			
			numA = Double.parseDouble(input.split(" ")[0]);
			operacao = input.split(" ")[1];
			numB = Double.parseDouble(input.split(" ")[2]);
			
			switch(operacao) {
				case "+" : resultado = String.valueOf(numA + numB);break;
				case "-" : resultado = String.valueOf(numA - numB);break; 
				case "*" : resultado = String.valueOf(numA * numB);break;
				case "/" : resultado = String.valueOf(numA / numB);break;
				default :  resultado = "";

			}
			byte[] msgSend = new byte[1000];
			msgSend = resultado.getBytes();
			DatagramPacket reply = new DatagramPacket(msgSend, 
				   	msgSend.length, request.getAddress(), request.getPort());
	 		
	 		if(cont%2==0) {
	 			datagramSocket.send(reply);
	 			System.out.println("enviou");
	 		}
	 		else 
	 			System.out.println("não enviou");
			System.out.println(resultado);
			cont++;
		}
	}

}

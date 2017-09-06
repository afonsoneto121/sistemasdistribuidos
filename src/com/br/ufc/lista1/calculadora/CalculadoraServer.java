package com.br.ufc.lista1.calculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServer {
	private static String resultado;
	private static Double numA;
	private static Double numB;
	private static String operacao;
	public static void main(String argv[]) throws Exception {
		String input;
		
		ServerSocket novoSocket = new ServerSocket(Constants.PORTA);

		while (true) {
			Socket connectionSocket = novoSocket.accept();
			
			DataInputStream in;
			DataOutputStream out;
			
			in = new DataInputStream( connectionSocket.getInputStream());
			out =new DataOutputStream( connectionSocket.getOutputStream());	
			input = in.readUTF();
			
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
			out.writeUTF(resultado);
			System.out.println(resultado);
		}
	}
}

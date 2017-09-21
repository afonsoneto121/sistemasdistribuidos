package com.br.ufc.lista1.testedecarga;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServerSingleThread {
	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			DataInputStream in;
			DataOutputStream out;
			
			in = new DataInputStream( connectionSocket.getInputStream());
			out =new DataOutputStream( connectionSocket.getOutputStream());
			

			clientSentence = in.readUTF();
			Thread.sleep(500);
			
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			out.writeUTF(capitalizedSentence);
		}
	}
}
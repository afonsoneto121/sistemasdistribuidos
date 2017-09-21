package com.br.ufc.lista1.testedecarga;

public class TesteDeCarga {
	public static void main(String[] args) {
		
		final int PORTA_SERVER_MULTITHREAD = 7896;
		final int PORTA_SERVER_SINGLETHREAD = 6789;
		testeServerThread(PORTA_SERVER_MULTITHREAD); // Teste com servidor multithread
		testeServerThread(PORTA_SERVER_SINGLETHREAD); // Teste com servidor singlethread
		
	}
	
	public static void testeServerThread(int porta){
		long timeInicial = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			TCPClient cliente = new TCPClient();			
			cliente.execultar(new String[]{"localhost","teste"},porta);
		}
		long timeFim = System.currentTimeMillis();
		System.out.println("Saiu: "+ (timeFim-timeInicial));
	}	
	
}

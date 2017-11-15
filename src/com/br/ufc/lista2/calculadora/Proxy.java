package com.br.ufc.lista2.calculadora;

import javax.swing.JOptionPane;

public class Proxy {
	ClienteUDP clienteUDP;

	public Proxy() {
		clienteUDP = new ClienteUDP();
	}
	public Double add(Double op1, Double op2) {
		clienteUDP.sendRequest("ADD"+";"+op1+";"+op2);
		String request = null;
		for (int i = 0; i < 4; i++) {
			request = clienteUDP.getResponse();
			if(request.equals("FAIL")) 
				clienteUDP.sendRequest("ADD"+";"+op1+";"+op2);			
			else 
				break;
		}
		return Double.parseDouble(request.trim());
	}
	public Double sub(Double op1, Double op2) {
		clienteUDP.sendRequest("SUB"+";"+op1+";"+op2);
		String request = null;
		for (int i = 0; i < 4; i++) {
			request = clienteUDP.getResponse();
			if(request.equals("FAIL")) 
				clienteUDP.sendRequest("SUB"+";"+op1+";"+op2);			
			else 
				break;
		}
		return Double.parseDouble(request.trim());
	}
	public Double mult(Double op1, Double op2) {
		clienteUDP.sendRequest("MULT"+";"+op1+";"+op2);
		String request = null;
		for (int i = 0; i < 4; i++) {
			request = clienteUDP.getResponse();
			if(request.equals("FAIL")) 
				clienteUDP.sendRequest("MULT"+";"+op1+";"+op2);			
			else 
				break;
		}
		return Double.parseDouble(request.trim());
	}
	public Double div(Double op1, Double op2) {
		clienteUDP.sendRequest("DIV"+";"+op1+";"+op2);
		String request = null;
		for (int i = 0; i < 4; i++) {
			request = clienteUDP.getResponse();
			if(request.equals("FAIL")) 
				clienteUDP.sendRequest("DIV"+";"+op1+";"+op2);			
			else 
				break;
		}
		return Double.parseDouble(request.trim());
	}
}

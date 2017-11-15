package com.br.ufc.lista2.calculadora;

import javax.swing.JOptionPane;

public class ClienteView {	
	public static void main(String[] args) {
		Proxy proxy = new Proxy();		
		Double numA;
		Double numB;
		String operacao;
		Double result = 0.0;
		try {
			String input = JOptionPane.showInputDialog(null, "Digite a operação (ex: a + b)");
			numA = Double.parseDouble(input.split(" ")[0]);
			operacao = input.split(" ")[1];
			numB = Double.parseDouble(input.split(" ")[2]);
			 
			switch (operacao) {
				case "+": result = proxy.add(numA, numB); break;
				case "-": result = proxy.sub(numA, numB); break;
				case "*": result = proxy.mult(numA, numB); break;
				case "/": result = proxy.div(numA, numB); break;
				default:  JOptionPane.showMessageDialog(null, "Operação invalida"); break;
			}
			JOptionPane.showMessageDialog(null, result);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Entrada mal formatada");
		}
		
		
	}
}

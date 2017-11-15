package com.br.ufc.lista2.calculadora;

public class Despachante {
	public String invoke(String input) {
		Esqueleto esqueleto = new Esqueleto();
		String operacao = input.split(";")[0];
		Double numA = Double.parseDouble(input.split(";")[1]);
		Double numB = Double.parseDouble(input.split(";")[2]);
		String resultado;
		String concat = numA +"," + numB;
		switch(operacao) {
			case "ADD" : resultado = esqueleto.add(concat);break;
			case "SUB" : resultado = esqueleto.add(concat);break; 
			case "MULT" : resultado = esqueleto.add(concat);break;
			case "DIV" : resultado = esqueleto.add(concat);break;
			default :  resultado = "";
		}

		return resultado;
	}
}

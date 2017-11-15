package com.br.ufc.lista2.calculadora;

public class Esqueleto {
	Calc calc = Calc.getInstance();
	public String add(String msg) {
		Double valA = Double.parseDouble(msg.split(",")[0]);
		Double valB = Double.parseDouble(msg.split(",")[1]);
		return String.valueOf(calc.add(valA,valB));
	}
	public String sub(String msg) {
		Double valA = Double.parseDouble(msg.split(",")[0]);
		Double valB = Double.parseDouble(msg.split(",")[1]);
		return String.valueOf(calc.sub(valA,valB));
	}
	public String mult(String msg) {
		Double valA = Double.parseDouble(msg.split(",")[0]);
		Double valB = Double.parseDouble(msg.split(",")[1]);
		return String.valueOf(calc.mult(valA,valB));
	}
	public String div(String msg) {
		Double valA = Double.parseDouble(msg.split(",")[0]);
		Double valB = Double.parseDouble(msg.split(",")[1]);
		return String.valueOf(calc.div(valA,valB));
	}
	
}

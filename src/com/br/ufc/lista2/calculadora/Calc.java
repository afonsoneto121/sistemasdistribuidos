package com.br.ufc.lista2.calculadora;

public class Calc {
	private static Calc calc = null;
	private Calc( ) {}
	public static Calc getInstance() {
		if(calc == null) {
			calc = new Calc();
			return calc;
		}
		return calc;
	}
	public Double add(Double op1, Double op2) {
		return op1 + op2;
	}
	public Double sub(Double op1, Double op2) {
		return op1 - op2;
	}
	public Double mult(Double op1, Double op2) {
		return op1 * op2;
	}
	public Double div(Double op1, Double op2) {
		return op1 / op2;
	}
}

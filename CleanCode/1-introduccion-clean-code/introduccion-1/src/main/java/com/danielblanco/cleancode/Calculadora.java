package com.danielblanco.cleancode;

public class Calculadora {
	
	public Double calcular(Double a, Double b) {
		Double d = Math.sqrt(a*a - b*b); 
		
		return b*d/2;
	}
}

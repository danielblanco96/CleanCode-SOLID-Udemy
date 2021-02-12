package com.danielblanco.cleancode;

public class CalculadoraTrianguloRectangulo {
	
	public Double calcularArea(Double hipotenusa, Double cateto) {
		Double cateto_2 = Math.sqrt(hipotenusa*hipotenusa - cateto*cateto);
		
		return cateto * cateto_2 / 2;
	}
}

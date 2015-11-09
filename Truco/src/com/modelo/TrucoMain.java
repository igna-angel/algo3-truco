package com.modelo;

public class TrucoMain {
	
	public static void main(String[] args){
		Mazo nuevoMazo = new Mazo();
		nuevoMazo.crear();
		nuevoMazo.imprimir();
				
		nuevoMazo.imprimir(nuevoMazo.mezclar());
	}

}

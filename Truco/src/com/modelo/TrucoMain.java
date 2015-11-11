package com.modelo;

public class TrucoMain {
	
	public static void main(String[] args){
		Mazo.getInstanciaMazo();
		Mazo.getInstanciaMazo().crear();
		Mazo.getInstanciaMazo().imprimir();
		Mazo.getInstanciaMazo().imprimir(Mazo.getInstanciaMazo().mezclar());
//		Mazo nuevoMazo = new Mazo();
//		nuevoMazo.crear();
//		nuevoMazo.imprimir();
//				
//		nuevoMazo.imprimir(nuevoMazo.mezclar());
	}

}

package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.modelo.Carta.Palo;

public class Mazo {
	
	public static final int CARTAS_EN_MAZO = 48;
	public static final int CANTIDAD_DE_PALOS = 4;
	
	private List<Carta> _cartas = null;

	public Mazo(){
		this._cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	public void crear(){
		int cartasPorPalo = CARTAS_EN_MAZO / CANTIDAD_DE_PALOS;
		int palo = Palo.Indefinido.getValorPalo();
		for(int i = 0; i < CANTIDAD_DE_PALOS; i++){
			palo++;
			for(int j = 1 ; j <= cartasPorPalo; j++){
				this.getCartas().add(new Carta(Palo.getTipoPalo(palo), j));
			}
		}
	}
	
	public void imprimir(){		
		this.getCartas().forEach(carta -> {
			System.out.println(carta.getPalo() + " " + carta.getNumero());
		});
		
		System.out.println("");
	}
	
	public void imprimir(int[] nuevoOrden){
		for(int i = 0; i < nuevoOrden.length; i++){
			System.out.println(this.getCartas().get(nuevoOrden[i]).getPalo() + " " + this.getCartas().get(nuevoOrden[i]).getNumero());
		}	
		
		System.out.println("");
	}
	
	public int[] mezclar(){
		int[] nuevoOrden = new int[Mazo.CARTAS_EN_MAZO];
		
		Arrays.fill(nuevoOrden, -1);
		
		int nuevaPosicion;
		
		for(int i = 0; i < Mazo.CARTAS_EN_MAZO; i++){
			do{
				nuevaPosicion = ThreadLocalRandom.current().nextInt(0, Mazo.CARTAS_EN_MAZO);
			}while(nuevoOrden[nuevaPosicion] != -1);
			
			nuevoOrden[nuevaPosicion] = i;			
		}
		
		return nuevoOrden;
	}
}

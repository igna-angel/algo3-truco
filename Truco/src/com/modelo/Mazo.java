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
	
	private static Mazo MazoInstancia = null;
	
	private Mazo() {
		 _cartas = new ArrayList<Carta>();
	}
	
	public static Mazo getInstanciaMazo() {
		if(MazoInstancia == null) {
			MazoInstancia = new Mazo();
		}
		return MazoInstancia;
	}
	
//	public Mazo(){
//		this._cartas = new ArrayList<Carta>();
//	}
//	
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
		int posicionInvalida = -1;
		
		Arrays.fill(nuevoOrden, posicionInvalida);
		
		int nuevaPosicion;
		
		for(int i = 0; i < Mazo.CARTAS_EN_MAZO; i++){
			do{
				nuevaPosicion = ThreadLocalRandom.current().nextInt(0, Mazo.CARTAS_EN_MAZO);
			}while(nuevoOrden[nuevaPosicion] != posicionInvalida);
			
			nuevoOrden[nuevaPosicion] = i;			
		}
		
		return nuevoOrden;
	}
	
	public Carta getCarta(int indice){
		return this.getCartas().get(indice);
	}

	public void repartirCarta(Jugador jugadorEnMesa) {
		List<Carta> cartasEnMano = jugadorEnMesa.getCartasEnMano();
		this.entregarCarta(cartasEnMano);
	}

	private void entregarCarta(List<Carta> cartasEnMano) {
		Carta cartaAEntregar = this._cartas.get(0);
		this._cartas.remove(0);
		cartasEnMano.add(cartaAEntregar);
	}

	public void agregarCartasAlMazoPorFinalizacionDeRonda(List<Carta> cartasEnMano) {
		_cartas.add(cartasEnMano.get(0));
		_cartas.add(cartasEnMano.get(1));
		_cartas.add(cartasEnMano.get(2));
	}
}

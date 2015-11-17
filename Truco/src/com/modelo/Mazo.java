package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.modelo.Carta.Palo;

public class Mazo {
	
	public static final int CARTAS_EN_MAZO = 40;
	public static final int CANTIDAD_DE_PALOS = 4;
	
	private List<Carta> _cartas = null;
	
	private int[] _mazoMezclado = null;
			
	public Mazo(){
		this._cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	public void crear(){
		int palo = Palo.Indefinido.getValorPalo();
		for(int i = 0; i < CANTIDAD_DE_PALOS; i++){
			palo++;
			for(int j = 1 ; j <= 7; j++){
				this.getCartas().add(new Carta(Palo.getTipoPalo(palo), j));
			}
			
			for(int k = 10; k <= 12; k++){
				this.getCartas().add(new Carta(Palo.getTipoPalo(palo), k));
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
	
	private int[] getMazoMezclado(){
		return this._mazoMezclado;
	}
	
	public void mezclar(){
		this._mazoMezclado = new int[Mazo.CARTAS_EN_MAZO];
		int posicionInvalida = -1;
		
		Arrays.fill(this._mazoMezclado, posicionInvalida);
		
		int nuevaPosicion;
		
		for(int i = 0; i < Mazo.CARTAS_EN_MAZO; i++){
			do{
				nuevaPosicion = ThreadLocalRandom.current().nextInt(0, Mazo.CARTAS_EN_MAZO);
			}while(this._mazoMezclado[nuevaPosicion] != posicionInvalida);
			
			this._mazoMezclado[nuevaPosicion] = i;			
		}
	}
	
	public Carta getCarta(int indice){
		return this.getCartas().get(indice);
	}
	
	
	public void repartir(CircularList<Jugador> jugadores, Jugador reparte, int cartasPorJugador){
		jugadores.moveCursorTo(jugadores.getIndexOf(reparte));
		
		int cartasARepartir = jugadores.getSize() * cartasPorJugador;
		
		for(int i = 0; i < cartasARepartir; i++){
			jugadores.advanceCursor();
			jugadores.getCurrent().recibirCarta(this.getCarta(this.getMazoMezclado()[i]));			
		}
	}

}

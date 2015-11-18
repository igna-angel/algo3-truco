package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.modelo.cartas.Carta;
import com.modelo.cartas.Carta.Palo;
import com.modelo.cartas.CartaAnchoBasto;
import com.modelo.cartas.CartaAnchoEspada;
import com.modelo.cartas.CartaAnchoFalso;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;

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
		this.crearNormales();
		this.crearEspeciales();
	}
	
	private void crearNormales(){
		int palo = Palo.Indefinido.getValorPalo();
		for(int i = 0; i < CANTIDAD_DE_PALOS; i++){
			palo++;
			for(int j = 4 ; j < 7; j++){
				this.getCartas().add(new CartaNormal(Palo.getTipoPalo(palo), j));
			}
			
			
			for(int k = 10; k <= 12; k++){
				this.getCartas().add(new CartaNormal(Palo.getTipoPalo(palo), k));
			}
		}
		
		this.getCartas().add(new CartaNormal(Palo.Basto, 7));
		this.getCartas().add(new CartaNormal(Palo.Copa, 7));
	}
	
	private void crearEspeciales(){		
		this.getCartas().add(new CartaAnchoFalso(Palo.Copa));
		this.getCartas().add(new CartaAnchoFalso(Palo.Oro));
		this.getCartas().add(new CartaAnchoBasto());
		this.getCartas().add(new CartaAnchoEspada());
		
		int palo = Palo.Indefinido.getValorPalo();
		palo++;
		
		while(palo < (Mazo.CANTIDAD_DE_PALOS+1)){
			this.getCartas().add(new CartaDos(Palo.getTipoPalo(palo)));
			this.getCartas().add(new CartaTres(Palo.getTipoPalo(palo)));
			palo++;
		}

		this.getCartas().add(new CartaSieteOro());
		this.getCartas().add(new CartaSieteEspada());
		
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

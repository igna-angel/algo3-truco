package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.exceptions.EmptyListException;
import com.exceptions.NoContieneCartaException;
import com.exceptions.NoSePuedenRecibirMasCartasException;
import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaInvalida;

public class Mano implements IRecibible{

	private List<Carta> _cartas = null;
	
	public Mano() {
		this._cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		if(this.getCartas().size() == 3) throw new NoSePuedenRecibirMasCartasException();
		carta.cartaEnMano();
		this.getCartas().add(carta);
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
	}
	
	public int getCartasEnMano(){
		int cartasEnMano = 0;
		for(Carta carta : this.getCartas()){
			if(carta.estaCartaEnMano()){
				cartasEnMano++;
			}
		}
		
		return cartasEnMano;
	}
	
	public boolean contiene(Carta carta){
		return this.getCartas().contains(carta);
	}

	public void quitarCarta(Carta carta) {
		this.getCartas().remove(carta);
	}

	public void devolverCartas() {
		this.getCartas().clear();
	}
	
	public void bajarCarta(Carta carta){
		if(!this.contiene(carta)) throw new NoContieneCartaException();
		if(!carta.estaCartaEnMano()) throw new NoContieneCartaException();
		carta.cartaJugada();
	}
	
	public Carta devolverCartaMasAlta() {
		Carta ganadora = new CartaInvalida();
		
		for(Carta cartaEnMano : this.getCartas()){
			ganadora = ganadora.ganador(cartaEnMano);
		}
		
		return ganadora;
	}

	private List<Carta> getGanadorasA(Carta carta){
		List<Carta> ganadoras = new ArrayList<Carta>();
		
		for(Carta cartaEnMano : this.getCartas()){
			if(cartaEnMano == cartaEnMano.ganador(carta)){
				ganadoras.add(cartaEnMano);
			}
		}
		
		return ganadoras;
	}
	
	private Carta getMasBajaEn(List<Carta> cartas){
		if(cartas.isEmpty()) throw new EmptyListException();
		
		Carta masBaja = cartas.get(0);
		
		for(Carta carta : cartas){
			masBaja = (masBaja == masBaja.ganador(carta))? carta : masBaja;
		}
		
		return masBaja;
	}
	
	public Carta getCartaGanadoraMinimaA(Carta carta){
		return this.getMasBajaEn(this.getGanadorasA(carta));		
	}
	
	public Carta getCartaMasBaja(){
		return this.getMasBajaEn(this.getCartas());
	}
	
	private Set<Carta> getCartasDelMismoPalo(){
		Set<Carta> mismoPalo = new HashSet<Carta>();
		
		for(int i = 0; i < this.getCartas().size() - 1; i++){
			if(this.getCartas().get(i).esMismoPaloQue(this.getCartas().get(i+1))){
				mismoPalo.add(this.getCartas().get(i));
				mismoPalo.add(this.getCartas().get(i+1));
			}
		}
		
		return mismoPalo;
	}
	
	public int getCantidadDeCartasDelMismoPalo(){
		return this.getCartasDelMismoPalo().size();
	}
	
	public boolean hayFlor(){
		return (this.getCantidadDeCartasDelMismoPalo() == 3);
	}
	
	protected int getMaximosPuntosEnvido(){
		int[] combinacionesEnvido = new int[6];
		combinacionesEnvido[0] = this.getCartas().get(0).getPuntosEnvido();
		combinacionesEnvido[1] = this.getCartas().get(1).getPuntosEnvido();
		combinacionesEnvido[2] = this.getCartas().get(2).getPuntosEnvido();
		combinacionesEnvido[3] = this.getCartas().get(0).getPuntosEnvidoCon(this.getCartas().get(1));
		combinacionesEnvido[4] = this.getCartas().get(0).getPuntosEnvidoCon(this.getCartas().get(2));
		combinacionesEnvido[5] = this.getCartas().get(1).getPuntosEnvidoCon(this.getCartas().get(2));
		
		Arrays.sort(combinacionesEnvido);
		return combinacionesEnvido[5];
	}
	
	public int getTantoEnMano() {		
		return this.getMaximosPuntosEnvido();
	}

	public int getPuntosFlorEnMano() {
		return (this.getCartas().get(0).getPuntosEnvido() +
				this.getCartas().get(1).getPuntosEnvido() +
				this.getCartas().get(2).getPuntosEnvido() +
				Carta.PUNTOS_BASE_MISMO_PALO);
	}
}

package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.modelo.cartas.Carta;

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
		this.getCartas().add(carta);
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
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
	
	public Carta devolverCartaMasAlta() {
		ComparadorCartas comparador = new ComparadorCartas();
		return comparador.compararCartas(this._cartas.toArray(new Carta[this._cartas.size()]));
	}

	public Carta buscarCartaQueGane(Stack<Carta> cartasYaJugadas) {
		// Busco las cartas que le gana a la más alta de las ya jugadas
		ComparadorCartas comparador = new ComparadorCartas();
		Carta masAltaJugada = comparador.compararCartas(cartasYaJugadas.toArray(new Carta[cartasYaJugadas.size()]));
		List<Carta> cartasQueGananALaMasAlta = comparador.getCartasMasAltasQueUnaDada(masAltaJugada, this._cartas);
		
		if (cartasQueGananALaMasAlta.size() == 0) {
			// No hay cartas que ganen, devuelvo la más baja
			return comparador.getCartaMasBaja(this._cartas.toArray(new Carta[this._cartas.size()]));
		}
		
		return comparador.getCartaMasBaja(cartasQueGananALaMasAlta.toArray(new Carta[this._cartas.size()]));
	}
	
	public int getTantoEnMano() {
		int tantoCarta1 = this._cartas.get(0).getNumero();
		int tantoCarta2 = this._cartas.get(1).getNumero();
		int tantoCarta3 = this._cartas.get(2).getNumero();
		int tantoParcial = 0;
		if (this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta2;
		}
		
		else if (this._cartas.get(0).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta3;
		}
		
		else if (this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta2 + tantoCarta3;
		}
		
		else if (this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo() && this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta2 + tantoCarta3;
		}
		
		else {
			tantoParcial = Collections.max(Arrays.asList(tantoCarta1, tantoCarta2, tantoCarta3));
		}
		
		return tantoParcial;
	}

	public boolean florEnMano() {
		return this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo() && this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo();
	}

}

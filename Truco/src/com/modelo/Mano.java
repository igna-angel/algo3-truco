package com.modelo;

import java.util.ArrayList;
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

}

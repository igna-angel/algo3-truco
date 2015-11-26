package com.modelo;

import com.modelo.cartas.Carta;

public class JugadorVirtual extends Jugador {
	private static final int MIN_TANTO_ENVIDO = 23;
	private static final int MIN_TANTO_REAL_ENVIDO = 28;
	private static final int MIN_TANTO_FALTA_ENVIDO = 31;
	
	public JugadorVirtual() {
		super();
	}
	
	public void elegirCartaABajarYBajarla (Vuelta vuelta) {
		Carta cartaAJugar = null;
		// Si abro la vuelta, bajo la carta mas alta
		if (vuelta.getCantidadDeCartasEnVuelta() == 0) {
			cartaAJugar = this._mano.devolverCartaMasAlta();
		}
		
		// Sino me fijo las cartas en mesa, si puedo ganar, juego a ganar con lo justo, sino tiro la mas baja
		else {
			cartaAJugar = this._mano.buscarCartaQueGane(vuelta.getCartas());
		}
		
		this.bajarCarta(vuelta, cartaAJugar);
	}
}

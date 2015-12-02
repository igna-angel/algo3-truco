package com.modelo;

import com.exceptions.EmptyListException;
import com.modelo.cartas.Carta;

public class JugadorVirtual extends Jugador {
	private static final int MIN_TANTO_ENVIDO = 23;
	private static final int MIN_TANTO_REAL_ENVIDO = 28;
	private static final int MIN_TANTO_FALTA_ENVIDO = 31;
	
	public JugadorVirtual() {
		super();
	}
	
	public void elegirCartaABajarYBajarla (Vuelta vuelta) {
		Carta cartaAJugar;
		
		try{
			cartaAJugar = this.getMano().getCartaGanadoraMinimaA(vuelta.getCartaGanadora());
		}catch (EmptyListException e){
			cartaAJugar = this.getMano().getCartaMasBaja();
		}
		
		this.bajarCarta(vuelta, cartaAJugar);
	}
	
	public void cantarTantoOFlorSiCorresponde (Ronda ronda) {
		int tantoEnMano = this._mano.getTantoEnMano();
		if (this._mano.florEnMano()) {
			ronda.seCantoFlor();
		}
		
		else if (tantoEnMano >= MIN_TANTO_FALTA_ENVIDO) {
			ronda.seCantoFaltaEnvido();
		}
		
		else if (tantoEnMano >= MIN_TANTO_REAL_ENVIDO) {
			ronda.seCantoRealEnvido();
		}
		
		else if (tantoEnMano >= MIN_TANTO_ENVIDO) {
			ronda.seCantoEnvido();
		}
	}

	@Override
	public void jugar(Vuelta vuelta) {
		// TODO Auto-generated method stub
	}
	
	// VER COMO HACER PARA ACEPTAR EL TANTO
}

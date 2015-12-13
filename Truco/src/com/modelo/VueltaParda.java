package com.modelo;

import java.util.List;

import com.acciones.Accion;

public class VueltaParda extends Vuelta{

	public VueltaParda(Ronda ronda, List<Accion> acciones, Jugador jugadorInicial) {
		super(ronda, acciones, jugadorInicial);
	}
	
	public Jugador getJugadorConCartaGanadora(){
		if(!this.getRonda().hayParda()){
			this.getRonda().definirFinDeRonda();
		}
		this.setJugadorConCartaGanadoraPardaParaVueltaAnterior();
		return this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
	}
	
	private void setJugadorConCartaGanadoraPardaParaVueltaAnterior() {
		Jugador jugadorGanadorParda = this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
		this.getRonda().asignarGanadorDeVueltaParda(jugadorGanadorParda);
	}
}

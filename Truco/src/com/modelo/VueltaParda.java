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
		Jugador jugadorGanadorParda = this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
		this.setJugadorConCartaGanadoraPardaParaVueltaAnterior(jugadorGanadorParda);
		return this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
	}
	
	public void setJugadorConCartaGanadoraPardaParaVueltaAnterior(Jugador jugador) {
		int posVueltaAnterior = this.getRonda().getCantidadDeVueltas() - 2;
		this.getRonda().getVueltas().get(posVueltaAnterior).asignarJugadorInicial(jugador);
	}
}

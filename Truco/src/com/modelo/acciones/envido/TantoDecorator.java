package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class TantoDecorator implements AccionTanto {

	protected AccionTanto accionADecorar;
	
	private Jugador _origen = null;
	private Jugador _destino = null;
	
	public TantoDecorator(AccionTanto accionDecorar, Jugador origen, Jugador destino){
		this.accionADecorar = accionDecorar;
		this.asignarOrigenYDestino(origen, destino);
	}
	
	@Override
	public int cantar() {
		return accionADecorar.cantar();
	}

	private void asignarOrigenYDestino(Jugador origen, Jugador destino){
		this._origen = origen;
		this._destino = destino;
	}
	
	public Jugador getOrigen(){
		return this._origen;
	}
	
	public Jugador getDestino(){
		return this._destino;
	}
}
package com.interfazgrafica;

import com.modelo.Jugador;
import com.modelo.Vuelta;
import com.modelo.acciones.Accion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAccionEventHandler implements EventHandler<ActionEvent> {

	private Accion accion;
	private Vuelta vuelta;
	private Jugador _origen;
	private Jugador _destino;
	
	public BotonAccionEventHandler (Accion accion, Vuelta vuelta, Jugador origen, Jugador destino){
		this.accion = accion;
		this.vuelta = vuelta;
		this._origen = origen;
		this._destino = destino;
	}
	
	public void handle (ActionEvent actionEvent){
		this.accion.setOrigenDestino(this._origen, this._destino);
		ImprimirTablero.getInstance().enviarAccionAJugador (accion, this._destino, this.vuelta);
	}
}


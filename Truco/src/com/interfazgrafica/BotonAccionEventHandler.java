package com.interfazgrafica;

import com.acciones.Accion;
import com.modelo.Vuelta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAccionEventHandler implements EventHandler<ActionEvent> {

	private Accion accion;
	private Vuelta vuelta;
	private ImprimirTablero tablero;
	
	public BotonAccionEventHandler (Accion accion, Vuelta vuelta, ImprimirTablero tablero){
		this.accion = accion;
		this.vuelta = vuelta;
		this.tablero = tablero;
	}
	
	public void handle (ActionEvent actionEvent){
		this.accion.setOrigenDestino(this.vuelta.getJugadorActual(), this.vuelta.getJugadorSiguienteAlActual());
		tablero.enviarAccionAJugador (accion, this.vuelta.getJugadorSiguienteAlActual(), this.vuelta);
		
	}
	
	
}


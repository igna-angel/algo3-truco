package com.interfazgrafica;

import com.acciones.Accion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAceptarEventHandler implements EventHandler<ActionEvent> {

	private Accion accionBase;
	
	public BotonAceptarEventHandler (Accion accionBase){		
		this.accionBase = accionBase;
	}
	
	public void handle (ActionEvent actionEvent){
		ImprimirTablero.getInstance().mostrarCartasJugador();
		ImprimirTablero.getInstance().aceptarAccion(accionBase);
	}
}


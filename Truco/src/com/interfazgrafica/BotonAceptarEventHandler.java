package com.interfazgrafica;

import com.modelo.acciones.Accion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAceptarEventHandler implements EventHandler<ActionEvent> {

	private Accion accionBase;
	
	public BotonAceptarEventHandler (Accion accionBase){		
		this.accionBase = accionBase;
	}
	
	public void handle (ActionEvent actionEvent){
		ImprimirTablero.getInstance().aceptarAccion(accionBase);
	}
}


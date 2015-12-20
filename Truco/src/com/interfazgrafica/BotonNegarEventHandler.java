package com.interfazgrafica;

import com.modelo.acciones.Accion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonNegarEventHandler implements EventHandler<ActionEvent> {

	private Accion accionBase;
	
	public BotonNegarEventHandler (Accion accionBase){		
		this.accionBase = accionBase;
	}
	
	public void handle (ActionEvent actionEvent){
		ImprimirTablero.getInstance().negarAccion(accionBase);
	}
}


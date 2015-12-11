package com.interfazgrafica;

import com.acciones.Accion;
import com.modelo.Vuelta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class BotonAceptarEventHandler implements EventHandler<ActionEvent> {

	private Accion accionBase;
	private Vuelta vuelta;
	private ImprimirTablero tablero;
	private VBox botonera;
	
	public BotonAceptarEventHandler (Accion accionBase, Vuelta vuelta, ImprimirTablero tablero, VBox botonera){
		
		this.accionBase = accionBase;
		this.vuelta = vuelta;
		this.tablero = tablero;
		this.botonera = botonera;
	}
	
	public void handle (ActionEvent actionEvent){
		this.accionBase.aceptar();
		this.botonera.getChildren().clear();
	}
	
	
}


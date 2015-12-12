package com.interfazgrafica;

import com.acciones.Accion;
import com.modelo.Vuelta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class BotonResponderTantoEventHandler implements EventHandler<ActionEvent>{

	private Accion accionBase;
	private VBox botoneraActual;
	private Vuelta vueltaActual;
	
	public BotonResponderTantoEventHandler(Accion accionOriginal, VBox botonera, Vuelta vuelta) {
		this.accionBase = accionOriginal;
		this.botoneraActual = botonera;
		this.vueltaActual = vuelta;
	}

	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().crearBotoneraDeRetruque(accionBase, botoneraActual, vueltaActual);
		ImprimirTablero.getInstance().imprimirPuntajeTanto(accionBase.getDestino().getTantoEnMano());
	}
}
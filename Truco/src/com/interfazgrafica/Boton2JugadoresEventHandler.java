package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;


public class Boton2JugadoresEventHandler implements EventHandler<ActionEvent>{

	private boolean conOSinFlor;
	
	public Boton2JugadoresEventHandler (boolean florHabilitada){
		this.conOSinFlor = florHabilitada;
	}

	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().generarPartido2Jugadores(conOSinFlor);
	}
	
}

package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Boton2JugadoresEventHandler implements EventHandler<ActionEvent>{

	private boolean conOSinFlor;
	private Stage stage;
	
	public Boton2JugadoresEventHandler (boolean florHabilitada,Stage stage){
		this.conOSinFlor = florHabilitada;
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().generarPartido2Jugadores(conOSinFlor);
		this.stage.close();
	}
	
}

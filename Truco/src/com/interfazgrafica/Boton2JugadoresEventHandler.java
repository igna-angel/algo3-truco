package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;


public class Boton2JugadoresEventHandler implements EventHandler<ActionEvent>{


	
	public Boton2JugadoresEventHandler (Stage stage){
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().generarPartido2Jugadores();
		
	}
	
}

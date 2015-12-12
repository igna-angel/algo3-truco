package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonFlorEventHandler implements EventHandler<ActionEvent>{
	
	private Stage stage;
	
	public BotonFlorEventHandler(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().determinarJuegoConOSinFlor(this.stage);
	}
}

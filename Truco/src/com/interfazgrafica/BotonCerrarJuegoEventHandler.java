package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonCerrarJuegoEventHandler implements EventHandler<ActionEvent>{
	
	private Stage stage;
	
	public BotonCerrarJuegoEventHandler(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent actionEvent){
		this.stage.close();
	}
}

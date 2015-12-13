package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonVueltaAlMenuEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent actionEvent){
		try {
			ImprimirTablero.getInstance().nuevaPresentacion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

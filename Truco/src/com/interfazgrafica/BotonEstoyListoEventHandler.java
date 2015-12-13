package com.interfazgrafica;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().ejecutarEstoyListo();
	}
}
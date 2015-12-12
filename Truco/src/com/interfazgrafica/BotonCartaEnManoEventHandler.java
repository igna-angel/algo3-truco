package com.interfazgrafica;

import com.modelo.cartas.Carta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonCartaEnManoEventHandler implements EventHandler<ActionEvent>{

	Carta carta;
	boolean yaClickeado;
	
	BotonCartaEnManoEventHandler(Carta carta){
		this.carta = carta;
		this.yaClickeado = false;
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		if (this.yaClickeado == false){
			ImprimirTablero.getInstance().bajarCarta(this.carta);		
			this.yaClickeado = true;
		}
	}
}

package com.interfazgrafica;

import com.modelo.Partido;
import com.modelo.cartas.Carta;
import com.interfazgrafica.BotonEstoyListoEventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
	
public class BotonCartaEnManoEventHandler implements EventHandler<ActionEvent>{
	
	Stage stage;
	Scene scene;
	HBox cartasJugadorEnMano;
	HBox cartasJugadorJugadas;
	VBox controles;
	Partido partido;
	Carta carta;
	BotonEstoyListoEventHandler botonEstoyListoEventHandler;
	boolean yaClickeado;
	
	
	BotonCartaEnManoEventHandler(HBox cartasJugadorEnMano, HBox cartasJugadorJugadas, Partido partido, Carta carta){

		this.cartasJugadorEnMano = cartasJugadorEnMano;
		this.cartasJugadorJugadas = cartasJugadorJugadas;
		this.partido = partido;
		this.carta = carta;
		this.yaClickeado = false;
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		if (this.yaClickeado == false){
			ImprimirTablero.getInstance().traspasarCartaDeManoAMesa(this.cartasJugadorEnMano, this.cartasJugadorJugadas, this.carta);
			
			this.yaClickeado = true;
		}
	}
}

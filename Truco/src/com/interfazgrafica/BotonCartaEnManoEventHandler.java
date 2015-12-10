package com.interfazgrafica;

import java.util.List;

import com.modelo.CircularList;
import com.modelo.Jugador;
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
	int i;
	BotonEstoyListoEventHandler botonEstoyListoEventHandler;
	
	
	BotonCartaEnManoEventHandler(Scene scene, Stage stage, VBox controles, HBox cartasJugadorEnMano, HBox cartasJugadorJugadas, Partido partido, int i){
		this.stage = stage;
		this.scene = scene;
		this.cartasJugadorEnMano = cartasJugadorEnMano;
		this.cartasJugadorJugadas = cartasJugadorJugadas;
		this.controles = controles;
		this.partido = partido;
		this.i = i;
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		
		cartasJugadorJugadas.getChildren().add(cartasJugadorEnMano.getChildren().get(i));
		cartasJugadorEnMano.getChildren().remove(i);
		this.partido.getJugadorActual().bajarCarta(this.partido.getRondaActual().getVueltaActual(), this.partido.getJugadorActual().getListaDeCartasEnMano().get(i));
		this.stage.setScene (this.scene);
		this.stage.show();
	}
}

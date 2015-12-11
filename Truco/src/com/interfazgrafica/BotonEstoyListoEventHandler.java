package com.interfazgrafica;

import com.modelo.Partido;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
//import javafx.scene.input.MouseEvent;;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
	private HBox cartasJugadorEnMano;
	private HBox cartasJugadorJugadas;
	private Partido partido;
	
	BotonEstoyListoEventHandler(Partido partido, HBox cartasJugadorEnMano, HBox cartasDeJugadorJugadas){				
		this.partido = partido;
		this.cartasJugadorEnMano = cartasJugadorEnMano;
		this.cartasJugadorJugadas = cartasDeJugadorJugadas;		
	}
	
	@Override
	public void handle(ActionEvent actionEvent){		
		ImprimirTablero.getInstance().crearBotonera(ImprimirTablero.getInstance().getBotonera());
		ImprimirTablero.getInstance().mostrarCartasJugador (partido.getJugadorActual(), this.cartasJugadorEnMano ,this.cartasJugadorJugadas);		
	}
}
package com.interfazgrafica;

import com.modelo.Partido;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
	private HBox cartasJugadorEnMano;
	private Partido partido;
	
	BotonEstoyListoEventHandler(Partido partido, HBox cartasJugadorEnMano){				
		this.partido = partido;
		this.cartasJugadorEnMano = cartasJugadorEnMano;

	}
	
	@Override
	public void handle(ActionEvent actionEvent){	
		int numeroDeJugador = partido.getOrdenJugadores().getIndexOf(partido.getJugadorActual());
		ImprimirTablero.getInstance().crearBotonera(ImprimirTablero.getInstance().getBotonera());
		HBox cartasJugadorJugadas = new HBox(ImprimirTablero.getInstance().getTablero().getChildren().get(numeroDeJugador));
		ImprimirTablero.getInstance().mostrarCartasJugador (partido.getJugadorActual(), this.cartasJugadorEnMano, cartasJugadorJugadas);		
	}
}
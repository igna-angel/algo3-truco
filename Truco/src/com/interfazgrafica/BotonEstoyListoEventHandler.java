package com.interfazgrafica;

import java.util.List;

import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.cartas.Carta;
import com.interfazgrafica.GeneradoresVisuales;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
		
	private Stage stage;
	private Scene scene;
	private List<Carta> listaDeCartasEnManoJugador1;
	private List<Carta> listaDeCartasEnManoJugador2;
	private HBox cartasJugador1EnMano;
	private HBox cartasJugador2EnMano;
	private GeneradoresVisuales generadores = new GeneradoresVisuales();
	
	BotonEstoyListoEventHandler(Scene scene, Stage stage, Partido partido, VBox botonera, Jugador jugadorActual1, Jugador jugadorActual2, HBox cartasJugador1EnMano, HBox cartasJugador2EnMano){
		
		this.stage = stage;
		
		this.cartasJugador1EnMano = cartasJugador1EnMano;
		this.cartasJugador2EnMano = cartasJugador2EnMano;
		this.listaDeCartasEnManoJugador1 = jugadorActual1.;
		this.listaDeCartasEnManoJugador2 = jugadorActual2.getListaDeCartasEnMano();
		
		this.scene = scene;
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		
		this.cartasJugador1EnMano.getChildren().clear();
		for (int i=0; i<listaDeCartasEnManoJugador1.size(); i++){
			Carta unaCarta = listaDeCartasEnManoJugador1.get(i);
			this.cartasJugador1EnMano.getChildren().add(generadores.generadorDeVisualDeCarta(unaCarta));
		}
		
		this.cartasJugador2EnMano.getChildren().clear();
		for (int i=0; i<listaDeCartasEnManoJugador2.size(); i++){
			Carta unaCarta = listaDeCartasEnManoJugador2.get(i);
			this.cartasJugador2EnMano.getChildren().add(generadores.generadorDeVisualDeCarta(unaCarta));
		}
		
		this.stage.setScene (scene);
		this.stage.show();
		
	}
}
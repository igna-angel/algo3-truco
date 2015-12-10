package com.interfazgrafica;

import java.util.List;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.cartas.Carta;
import com.sun.glass.events.MouseEvent;
import com.interfazgrafica.GeneradoresVisuales;
import com.interfazgrafica.BotonCartaEnManoEventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import javafx.scene.input.MouseEvent;;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
		
	private Stage stage;
	private Scene scene;
	private List<Carta> listaDeCartasEnManoJugador;
	private HBox cartasJugadorEnMano;
	private HBox cartasJugadorJugadas;
	private GeneradoresVisuales generadores = new GeneradoresVisuales();
	private Partido partido;
	private Jugador jugadorActual;
	private VBox controles;
	
	BotonEstoyListoEventHandler(Scene scene, Stage stage, Partido partido, VBox controles, HBox cartasJugadorEnMano, HBox cartasDeJugadorJugadas){
		
		this.stage = stage;
		
		this.scene = scene;
		
		this.partido = partido;
		
		this.jugadorActual = this.partido.getJugadorActual();
		
		this.cartasJugadorEnMano = cartasJugadorEnMano;
		
		this.cartasJugadorJugadas = cartasDeJugadorJugadas;

		this.listaDeCartasEnManoJugador = jugadorActual.getListaDeCartasEnMano();
		
		this.controles = controles;

	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		
		if (this.partido.getMaximoPuntaje() < 30){
			Label puedeCantar = new Label ("Jugando");
			Button envido = new Button ("ENVIDO");
			VBox posiblesCantos = new VBox (envido);
			this.controles.getChildren().clear();
			this.cartasJugadorEnMano.getChildren().clear();
			this.cartasJugadorJugadas.getChildren().clear();
			for(int i=0; i<= this.jugadorActual.getCartasEnMano(); i++){
				BotonCartaEnManoEventHandler botonCarta1EventHandler = new BotonCartaEnManoEventHandler(this.scene, this.stage, this.controles, this.cartasJugadorEnMano, this.cartasJugadorJugadas, this.partido, i);
				ImageView visualDeCartaEnMano = generadores.generadorDeVisualDeCarta(listaDeCartasEnManoJugador.get(i));
				Button botonCartaEnMano = new Button ();
				botonCartaEnMano.setGraphic(visualDeCartaEnMano);
				botonCartaEnMano.setOnAction(botonCarta1EventHandler);
				this.cartasJugadorEnMano.getChildren().add(botonCartaEnMano);
				
			}
			/*BotonCartaEnManoEventHandler botonCarta1EventHandler = new BotonCartaEnManoEventHandler(this.scene, this.stage, this.cartasJugadorEnMano, this.cartasJugadorJugadas, 0);
			BotonCartaEnManoEventHandler botonCarta2EventHandler = new BotonCartaEnManoEventHandler(this.scene, this.stage, this.cartasJugadorEnMano, this.cartasJugadorJugadas, 1 );
			BotonCartaEnManoEventHandler botonCarta3EventHandler = new BotonCartaEnManoEventHandler(this.scene, this.stage, this.cartasJugadorEnMano, this.cartasJugadorJugadas, 2 );

			ImageView visualDeCartaEnMano1 = generadores.generadorDeVisualDeCarta(listaDeCartasEnManoJugador.get(0));
			ImageView visualDeCartaEnMano2 = generadores.generadorDeVisualDeCarta(listaDeCartasEnManoJugador.get(1));
			ImageView visualDeCartaEnMano3 = generadores.generadorDeVisualDeCarta(listaDeCartasEnManoJugador.get(2));
			Button botonCartaEnMano1 = new Button ();
			Button botonCartaEnMano2 = new Button ();
			Button botonCartaEnMano3 = new Button ();
			botonCartaEnMano1.setGraphic(visualDeCartaEnMano1);
			botonCartaEnMano1.setOnAction(botonCarta1EventHandler);
			botonCartaEnMano2.setGraphic(visualDeCartaEnMano2);
			botonCartaEnMano2.setOnAction(botonCarta2EventHandler);
			botonCartaEnMano3.setGraphic(visualDeCartaEnMano3);
			botonCartaEnMano3.setOnAction(botonCarta3EventHandler);
			this.cartasJugadorEnMano.getChildren().add(botonCartaEnMano1);
			this.cartasJugadorEnMano.getChildren().add(botonCartaEnMano2);
			this.cartasJugadorEnMano.getChildren().add(botonCartaEnMano3);*/
		}
		
		else {
			Label tituloFinalJuego = new Label ("JUEGO TERMINDO \n \n PUNTAJE FINAL \n \n");
			tituloFinalJuego.setAlignment(Pos.CENTER);
			tituloFinalJuego.setPadding(new Insets (20));
			Label puntajesFinal = new Label ("JUGADOR1: " + partido.getPuntosPrimerEquipo() + "\n JUGADOR2: " + partido.getPuntosUltimoEquipo());
			puntajesFinal.setAlignment(Pos.CENTER);
			puntajesFinal.setPadding(new Insets (20));
			VBox finDelJuego = new VBox (tituloFinalJuego, puntajesFinal);
			finDelJuego.setAlignment(Pos.CENTER);
			this.scene = new Scene (finDelJuego, 600, 600); 
		}
		
		this.stage.setScene (scene);
		this.stage.show();
	}
}
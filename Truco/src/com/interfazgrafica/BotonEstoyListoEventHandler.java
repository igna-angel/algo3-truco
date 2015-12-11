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
	private VBox botonera;
	private GeneradoresVisuales generadores = new GeneradoresVisuales();
	private Partido partido;
	private Jugador jugadorActual;
	
	BotonEstoyListoEventHandler(Partido partido, HBox cartasJugadorEnMano, HBox cartasDeJugadorJugadas, VBox botonera){
				
		this.partido = partido;
		this.jugadorActual = this.partido.getJugadorActual();
		this.cartasJugadorEnMano = cartasJugadorEnMano;
		this.cartasJugadorJugadas = cartasDeJugadorJugadas;
		this.botonera = botonera;
		this.listaDeCartasEnManoJugador = jugadorActual.getListaDeCartasEnMano();
		
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		
		ImprimirTablero.getInstance().crearBotonera(this.botonera);
		ImprimirTablero.getInstance().mostrarCartasJugador (partido.getJugadorActual(), this.cartasJugadorEnMano ,this.cartasJugadorJugadas);
		/*if (this.partido.getMaximoPuntaje() < 30){*/
			/*Label puedeCantar = new Label ("Jugando");
			Button envido = new Button ("ENVIDO");
			VBox posiblesCantos = new VBox (envido);
			this.controles.getChildren().clear();*/
			/*this.cartasJugadorEnMano.getChildren().clear();
			this.cartasJugadorJugadas.getChildren().clear();
			for(int i=0; i< this.jugadorActual.getCartasEnMano(); i++){
				BotonCartaEnManoEventHandler botonCarta1EventHandler = new BotonCartaEnManoEventHandler(this.scene, this.stage, this.controles, this.cartasJugadorEnMano, this.cartasJugadorJugadas, this.partido, i);
				System.out.println(i);
				ImageView visualDeCartaEnMano = generadores.generadorDeVisualDeCarta(listaDeCartasEnManoJugador.get(i));
				Button botonCartaEnMano = new Button ();
				botonCartaEnMano.setGraphic(visualDeCartaEnMano);
				botonCartaEnMano.setOnAction(botonCarta1EventHandler);
				this.cartasJugadorEnMano.getChildren().add(botonCartaEnMano);
				
			}

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
		}*/
		
	}
}
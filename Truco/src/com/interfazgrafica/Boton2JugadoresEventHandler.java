package com.interfazgrafica;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Mazo;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.cartas.Carta;

import java.util.List;

import com.interfazgrafica.GeneradoresVisuales;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Boton2JugadoresEventHandler implements EventHandler<ActionEvent>{

	private GeneradoresVisuales generador = new GeneradoresVisuales ();
	private Partido partido;
	private Stage stage;
	private Scene scene;
	

	
	public Boton2JugadoresEventHandler (Stage stage){
		this.stage = stage;
		this.partido = new Partido();
		partido.agregarEquipo();
		partido.agregarEquipo();
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		partido.crearPartido();
		//partido.getMazo().mezclar();
		//partido.getOrdenJugadores().resetToFirst();
		//partido.getMazo().repartir(partido.getOrdenJugadores(), partido.getOrdenJugadores().getFirst(), 3);
		partido.nuevaRonda();
		partido.getRondaActual().nuevaVuelta();
		
		VBox puntajeJugador1 = new VBox (new Label ("EQUIPO 1"), new Label (Integer.toString(partido.getPuntosPrimerEquipo())));
		puntajeJugador1.setAlignment(Pos.TOP_CENTER);
		puntajeJugador1.setSpacing(10);
		VBox puntajeJugador2 = new VBox (new Label ("EQUIPO 2"), new Label (Integer.toString(partido.getPuntosUltimoEquipo())));
		puntajeJugador2.setAlignment(Pos.TOP_CENTER);
		puntajeJugador2.setSpacing(10);
		HBox ambosPuntajes = new HBox (puntajeJugador1, puntajeJugador2);
		ambosPuntajes.setSpacing(15);
		ambosPuntajes.setPadding(new Insets(20));
		
        HBox cartasJugador1Jugadas = generador.generarEspacioVacioVertical();
        HBox cartasJugador2Jugadas = generador.generarEspacioVacioVertical();
        
        HBox cartasJugador1EnMano = generador.generarCartasComienzoDeJugador();
        HBox cartasJugador2EnMano = generador.generarCartasComienzoDeJugador();
        cartasJugador1EnMano.setAlignment(Pos.TOP_CENTER);
		cartasJugador2EnMano.setAlignment(Pos.BOTTOM_CENTER);
		
		
		VBox campoDeJuego = new VBox (cartasJugador1EnMano, cartasJugador1Jugadas, cartasJugador2Jugadas, cartasJugador2EnMano);
		campoDeJuego.setPadding(new Insets (20));
		
		CircularList<Integer> turnoJugador = new CircularList<Integer>();
		turnoJugador.add(1);
		turnoJugador.add(2);
		turnoJugador.resetToFirst();
		Label turnoDe = new Label ("Turno de: JUGADOR ");
		
		
		
		//List <Carta> listaDeCartasEnManoJugador2 =
		
				
		HBox esElTurnoDe = new HBox (turnoDe, new Label (turnoJugador.getCurrent().toString())/*, new Label (Integer.toString(partido.getOrdenJugadores().getFirst().getCantidadCartas()))*/);
		esElTurnoDe.setPadding(new Insets(20));
		Button botonEstoyListo = new Button ("Estoy Listo");
		VBox botonera = new VBox (botonEstoyListo);
		botonera.setAlignment(Pos.TOP_CENTER);
		VBox controles = new VBox (esElTurnoDe, botonera);
		controles.setAlignment(Pos.TOP_CENTER);
		
		

		HBox pantalla = new HBox (ambosPuntajes, campoDeJuego, controles);
		this.scene = new Scene(pantalla, 700,600);
		
		Jugador jugadorActual = this.partido.getJugadorActual();
		HBox cartasJugadorEnMano = cartasJugador1EnMano;
		BotonEstoyListoEventHandler botonEstoyListoEventHandler = new BotonEstoyListoEventHandler (this.scene, this.stage, this.partido, botonera, jugadorActual, cartasJugadorEnMano);
		botonEstoyListo.setOnAction(botonEstoyListoEventHandler);
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		this.stage.setScene (scene);
		this.stage.show();
		
	}
	
}

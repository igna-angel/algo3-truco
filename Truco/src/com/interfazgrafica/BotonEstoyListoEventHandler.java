package com.interfazgrafica;

import java.util.List;

import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.cartas.Carta;
import com.interfazgrafica.GeneradoresVisuales;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
		
	private Stage stage;
	private Scene scene;
	private List<Carta> listaDeCartasEnManoJugador;
	private HBox cartasJugadorEnMano;
	private GeneradoresVisuales generadores = new GeneradoresVisuales();
	private Partido partido;
	
	BotonEstoyListoEventHandler(Scene scene, Stage stage, Partido partido, VBox botonera, Jugador jugadorActual, HBox cartasJugadorEnMano){
		
		this.stage = stage;
		
		this.cartasJugadorEnMano = cartasJugadorEnMano;

		this.listaDeCartasEnManoJugador = jugadorActual.getListaDeCartasEnMano();

		this.partido = partido;
		
		this.scene = scene;
		
		partido.getRondaActual().asignarPuntos(15, 1);
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		
		if (this.partido.getMaximoPuntaje() < 30){
			this.cartasJugadorEnMano.getChildren().clear();
			for (int i=0; i<listaDeCartasEnManoJugador.size(); i++){
				Carta unaCarta = listaDeCartasEnManoJugador.get(i);
				this.cartasJugadorEnMano.getChildren().add(generadores.generadorDeVisualDeCarta(unaCarta));
				partido.getRondaActual().asignarPuntos(15, 1);
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
		}
		
		this.stage.setScene (scene);
		this.stage.show();
		
	}
}
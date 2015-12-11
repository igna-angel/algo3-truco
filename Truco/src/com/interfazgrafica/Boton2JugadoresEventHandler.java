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


	
	public Boton2JugadoresEventHandler (Stage stage){
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		ImprimirTablero.getInstance().generarPartido2Jugadores();
		
	}
	
}

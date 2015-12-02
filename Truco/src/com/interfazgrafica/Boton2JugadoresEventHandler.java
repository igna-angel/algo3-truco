package com.interfazgrafica;

import com.modelo.Partido;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

	private Partido partido;
	private Stage stage;
	private Scene scene;
	
	public ImageView generarVisionCartaDorso (){
		Image dorsoAzul = new Image("file:C:\\Users\\Ska-Ska-Ska\\Desktop\\Cartas Españolas\\CartaDorsoAzul.png");
		ImageView cartaDorsoAzul = new ImageView (dorsoAzul);
        cartaDorsoAzul.setFitWidth(70);
        cartaDorsoAzul.setPreserveRatio(true);
        cartaDorsoAzul.setSmooth(true);
        cartaDorsoAzul.setCache(true);
        return cartaDorsoAzul;
	}
	
	public HBox generarCartasComienzoDeJugador (){
		ImageView cartaDorso1 = this.generarVisionCartaDorso();
        ImageView cartaDorso2 = this.generarVisionCartaDorso();
        ImageView cartaDorso3 = this.generarVisionCartaDorso();
		HBox cartasJugador = new HBox (cartaDorso1, cartaDorso2, cartaDorso3);
		cartasJugador.setSpacing(5);
		cartasJugador.setPadding(new Insets(15));
		cartasJugador.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		return cartasJugador;
	}
	
	public HBox generarEspacioVacioVertical (){
		
		Image imagenEspacioVacio = new Image("file:C:\\Users\\Ska-Ska-Ska\\Desktop\\Cartas Españolas\\espacioCarta.png");
		ImageView espacioVacioCarta = new ImageView (imagenEspacioVacio);
		espacioVacioCarta.setFitWidth(70);
		espacioVacioCarta.setPreserveRatio(true);
		espacioVacioCarta.setSmooth(true);
		espacioVacioCarta.setCache(true);
		HBox espacioVacio = new HBox (espacioVacioCarta);
		espacioVacio.setSpacing(5);
		espacioVacio.setPadding(new Insets(15));
		espacioVacio.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		return espacioVacio;
	}
	
	public Boton2JugadoresEventHandler (Stage stage){
		this.stage = stage;
		this.partido = new Partido();
		
		HBox tituloPuntajes = new HBox (new Label ("P1"), new Label ("P2"));
		HBox puntajeJugador1 = new HBox (2);
		//HBox puntajeJugador2 = new HBox (this.partido.getPuntosUltimoEquipo());
		//HBox ambosPuntajes = new HBox (puntajeJugador1, puntajeJugador2);
		//VBox puntajes = new VBox (tituloPuntajes, ambosPuntajes);
        HBox cartasJugador1EnMano = this.generarCartasComienzoDeJugador();
        HBox cartasJugador2EnMano = this.generarCartasComienzoDeJugador();
        HBox cartasJugador1Jugadas = this.generarEspacioVacioVertical();
        HBox cartasJugador2Jugadas = this.generarEspacioVacioVertical();
		cartasJugador1EnMano.setAlignment(Pos.TOP_CENTER);
		cartasJugador2EnMano.setAlignment(Pos.BOTTOM_CENTER);
		//puntajes.setAlignment(Pos.CENTER_LEFT);
		
		VBox campoDeJuego = new VBox (cartasJugador1EnMano, cartasJugador1Jugadas, cartasJugador2Jugadas, cartasJugador2EnMano);
		
		this.scene = new Scene(campoDeJuego, 700,600);
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		this.stage.setScene (scene);
		this.stage.show();
	}
	
}

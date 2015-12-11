package com.interfazgrafica;

import com.modelo.cartas.Carta;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

public class GeneradoresVisuales {
	
	private static GeneradoresVisuales instance = null;
	
	public static GeneradoresVisuales getInstance(){
		if (GeneradoresVisuales.instance == null){
			GeneradoresVisuales.instance = new GeneradoresVisuales();
		}
		return GeneradoresVisuales.instance;
	}
	
	public ImageView generarVisionCartaDorso (){
		Image dorsoAzul = new Image("https://raw.githubusercontent.com/igna-angel/algo3-truco/5e5aa5b00030f72bbe2f85ab055ca5482076829b/cartas%20espa%C3%B1olas/CartaDorsoAzul.png");
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
		
		Image imagenEspacioVacio = new Image("https://raw.githubusercontent.com/igna-angel/algo3-truco/5e5aa5b00030f72bbe2f85ab055ca5482076829b/cartas%20espa%C3%B1olas/EspacioCarta.png");
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
	
	// Pre: La imagen de la carta debe estar con extension ".png"
	// Post: Devuelve la visual aplicable a Boxes de la carta
	public ImageView generadorDeVisualDeCarta (Carta unaCarta){
		Image imagenDeUnaCarta = new Image ("https://raw.githubusercontent.com/igna-angel/algo3-truco/5e5aa5b00030f72bbe2f85ab055ca5482076829b/cartas%20espa%C3%B1olas/"+unaCarta.getPalo().toString()+"/"+unaCarta.getNumero()+".png");
		ImageView visualDeUnaCarta = new ImageView (imagenDeUnaCarta);
		visualDeUnaCarta.setFitWidth(70);
		visualDeUnaCarta.setPreserveRatio(true);
		visualDeUnaCarta.setSmooth(true);
		visualDeUnaCarta.setCache(true);
		return visualDeUnaCarta;
	}
}

package com.interfazgrafica;

import java.util.List;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.cartas.Carta;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

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
	
	public void generarCartasDadasVuelta (CircularList<Jugador> listaDeJugadores, List<HBox> contenedoresDeCartas){
		for (int i=0; i < contenedoresDeCartas.size(); i++){
			contenedoresDeCartas.get(i).getChildren().clear();
			for (int j=0; j < listaDeJugadores.getAt(i).getCantidadCartasEnMano(); j++ ){
				ImageView cartaDorso = this.generarVisionCartaDorso();
				contenedoresDeCartas.get(i).getChildren().add(cartaDorso);
			}
			contenedoresDeCartas.get(i).setSpacing(5);
			contenedoresDeCartas.get(i).setPadding(new Insets(15));
			contenedoresDeCartas.get(i).setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
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

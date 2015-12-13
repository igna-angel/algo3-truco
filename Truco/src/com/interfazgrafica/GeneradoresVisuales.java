package com.interfazgrafica;

import java.util.HashMap;
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
	
	private Image _imagenCartaDorso = null;
	private Image _imagenEspacioVacio = null;
	private HashMap<String, Image> _imagenesCartas = null;
	
	private static GeneradoresVisuales instance = null;
	
	private GeneradoresVisuales(){
		this._imagenesCartas = new HashMap<String, Image>();
	}
	
	public static GeneradoresVisuales getInstance(){
		if (GeneradoresVisuales.instance == null){
			GeneradoresVisuales.instance = new GeneradoresVisuales();
		}
		return GeneradoresVisuales.instance;
	}
	
	public ImageView generarVisionCartaDorso (){
		if(this._imagenCartaDorso == null){
			this._imagenCartaDorso = new Image("com/interfazgrafica/CartaDorsoAzul.png");
		}
		
		ImageView nuevo = new ImageView (this._imagenCartaDorso);
		nuevo.setFitWidth(70);
		nuevo.setPreserveRatio(true);
		nuevo.setSmooth(true);
		nuevo.setCache(true);
			
        return nuevo;
	}
	
	public void generarCartasDadasVuelta (CircularList<Jugador> listaDeJugadores, List<HBox> contenedoresDeCartas){
		for (int i=0; i < contenedoresDeCartas.size(); i++){
			contenedoresDeCartas.get(i).getChildren().clear();
			for (int j=0; j < listaDeJugadores.getAt(i).getCantidadCartasEnMano(); j++ ){
				contenedoresDeCartas.get(i).getChildren().add(this.generarVisionCartaDorso());
			}
			contenedoresDeCartas.get(i).setSpacing(5);
			contenedoresDeCartas.get(i).setPadding(new Insets(15));
			contenedoresDeCartas.get(i).setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}
	
	
	// Pre: La imagen de la carta debe estar con extension ".png"
	// Post: Devuelve la visual aplicable a Boxes de la carta
	public ImageView generadorDeVisualDeCarta (Carta unaCarta){
		if(!this._imagenesCartas.containsKey(unaCarta.getPalo().toString()+unaCarta.getNumero())){
			this._imagenesCartas.put(unaCarta.getPalo().toString()+unaCarta.getNumero(), new Image ("com/interfazgrafica/baraja/"+unaCarta.getPalo().toString()+"/"+unaCarta.getNumero()+".png"));
		}
		
		ImageView visualDeUnaCarta = new ImageView (this._imagenesCartas.get(unaCarta.getPalo().toString()+unaCarta.getNumero()));
		visualDeUnaCarta.setFitWidth(70);
		visualDeUnaCarta.setPreserveRatio(true);
		visualDeUnaCarta.setSmooth(true);
		visualDeUnaCarta.setCache(true);
		return visualDeUnaCarta;
	}
}

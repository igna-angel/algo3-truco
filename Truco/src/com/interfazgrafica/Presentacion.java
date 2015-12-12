
package com.interfazgrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;


public class Presentacion extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
		
		stage.setTitle ("FonTruco");
		
		Label presentacion = new Label ();
		presentacion.setText("Bienvenido a FonTruco! \n"
				+ "Por Favor Seleccione la cantidad de Jugadores:");
		presentacion.setFont(Font.font ("VerdanaItalic" , FontWeight.BOLD , 12));
		presentacion.setTextFill(Color.ANTIQUEWHITE);
		
        Image imagenTruco = new Image("https://raw.githubusercontent.com/igna-angel/algo3-truco/5e5aa5b00030f72bbe2f85ab055ca5482076829b/cartas%20espa%C3%B1olas/truco.png");
        ImageView imagenInicio = new ImageView (imagenTruco);
        imagenInicio.setFitWidth(120);
        imagenInicio.setPreserveRatio(true);
        imagenInicio.setSmooth(true);
        imagenInicio.setCache(true);
        
		presentacion.setTextAlignment(TextAlignment.CENTER);
		
		Button boton2Jugadores = new Button();
		boton2Jugadores.setText ("2 JUGADORES");
		
		Button boton4Jugadores = new Button();
		boton4Jugadores.setText ("4 JUGADORES");
		
		Button boton6Jugadores = new Button();
		boton6Jugadores.setText ("6 JUGADORES");
		
		VBox contenedorInicial = new VBox(presentacion, boton2Jugadores, boton4Jugadores, boton6Jugadores, imagenInicio);
		contenedorInicial.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		contenedorInicial.setAlignment(Pos.CENTER);
        contenedorInicial.setSpacing(10);
        contenedorInicial.setPadding(new Insets(15));
        		
        Scene scene = new Scene(contenedorInicial, 300, 340);

        stage.setScene(scene);

        stage.show();
        
        BotonFlorEventHandler botonCriterio2JugadoresEventHandler = new BotonFlorEventHandler(stage);
        boton2Jugadores.setOnAction(botonCriterio2JugadoresEventHandler);
        
        //Boton4JugadoresEventHandler boton4JugadoresEventHandler = new Boton4JugadoresEventHandler();
		
        //Boton6JugadoresEventHandler boton6JugadoresEventHandler = new Boton6JugadoresEventHandler();
    }

}
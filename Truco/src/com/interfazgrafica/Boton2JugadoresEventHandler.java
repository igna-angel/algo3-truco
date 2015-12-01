package com.interfazgrafica;

import com.modelo.Partido;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Boton2JugadoresEventHandler implements EventHandler<ActionEvent>{

	private Partido partido;
	private Stage stage;
	private Scene scene;
	
	public Boton2JugadoresEventHandler (Stage stage){
		this.stage = stage;
		this.partido = new Partido();
		Label nuevaLabel = new Label();
		nuevaLabel.setText("Estan por jugar 2 jugadores");
		HBox contenedorHorizontal = new HBox (nuevaLabel);
		contenedorHorizontal.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		contenedorHorizontal.setAlignment(Pos.CENTER);
		this.scene = new Scene(contenedorHorizontal,700,600);
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		this.stage.setScene (scene);
		this.stage.show();
	}
	
}

package com.interfazgrafica;

import com.modelo.Partido;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BotonEstoyListoEventHandler implements EventHandler<ActionEvent>{
		
	private Stage stage;
	private Scene scene;
	
	BotonEstoyListoEventHandler(Stage stage, Partido partido, HBox pantalla){
		
		this.stage = stage;
		
		
	}
	
	@Override
	public void handle(ActionEvent actionEvent){
		this.stage.setScene (scene);
		this.stage.show();
		
	}
}
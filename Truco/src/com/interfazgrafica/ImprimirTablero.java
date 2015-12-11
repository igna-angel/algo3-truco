package com.interfazgrafica;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.Vuelta;
import com.modelo.cartas.Carta;

import java.util.List;

import com.acciones.Accion;
import com.acciones.EstadoIndefinido;
import com.exceptions.NoContieneCartaException;
import com.interfazgrafica.GeneradoresVisuales;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImprimirTablero {
	
	private Scene scene;
	private Stage stage;
	private Partido partido;
	private static ImprimirTablero instance = null;
	VBox botonera;
	VBox controles;
	HBox pantalla;
	
	
	
	private ImprimirTablero(){
		
	}
	
	
	
	public void generarPartido2Jugadores(){
		
		this.stage = new Stage();
		this.partido = new Partido(false);
		partido.agregarEquipo();
		partido.agregarEquipo();
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		partido.crearPartido();
		partido.nuevaRonda();
		partido.getRondaActual().nuevaVuelta();
		ImprimirTablero.getInstance().imprimirTablero(partido);
		//this.scene = ImprimirTablero.getInstance().getScene();
		this.stage.setScene (scene);
		this.stage.show();
	}
	

	public static ImprimirTablero getInstance(){
		if (ImprimirTablero.instance == null){
			System.out.println("Instancia");
			ImprimirTablero.instance = new ImprimirTablero();
		}
		return ImprimirTablero.instance;
	}
	
	
	
	public Scene getScene (){
		return this.scene;
	}
	
	
	
	public VBox crearBotoneraDeRetruque (List<Accion> accionesPosibles, Pos posicion, Vuelta vuelta){
		VBox botonera = new VBox ();
		
		Button aceptar = new Button ("QUIERO");
		botonera.getChildren().add(aceptar);
		//BotonAceptarEventHandler botonAceptarEventHandler = BotonAceptarEventHandler ();
		
		//Button negar = new Button ("NO QUIERO");
		//botonera.getChildren().add(negar);
		//BotonNegarEventHandler botonNegarEventHandler 
		
		for (Accion accion : accionesPosibles)
		{
			Button unBoton = new Button();
			unBoton.setText(accion.getID());
			botonera.getChildren().add(unBoton);
			BotonAccionEventHandler botonRetruqueEventHandler = new BotonAccionEventHandler (accion, vuelta, this);
			
		}
		
		return botonera;
	}
	
	
	
	
	public void crearBotonera (VBox botonera){
		VBox botoneraAux = new VBox();
		for (Accion accion : partido.getRondaActual().getVueltaActual().getAccionesDeVuelta())
		{
			if (accion.getEstado() instanceof EstadoIndefinido){
				Button unBoton = new Button();
				unBoton.setText(accion.getID());
				botonera.getChildren().add(unBoton);
				//BotonAccionEventHandler botonAccionEventHandler = new BotonAccionEventHandler (accion, vuelta, this);
			
			}
			}
		botonera = botoneraAux;
		
	}
	
	
	
	
	public void imprimirTablero (Partido partido){
		
	GeneradoresVisuales generador = new GeneradoresVisuales();
	
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
    cartasJugador1Jugadas.setAlignment(Pos.CENTER);
    cartasJugador2Jugadas.setAlignment(Pos.CENTER);
    
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
	HBox esElTurnoDe = new HBox (turnoDe, new Label (turnoJugador.getCurrent().toString()));
	
	esElTurnoDe.setPadding(new Insets(20));
	Button botonEstoyListo = new Button ("Estoy Listo");
	VBox botonera = new VBox (botonEstoyListo);
	botonera.setAlignment(Pos.TOP_CENTER);
	VBox controles = new VBox (esElTurnoDe, botonera);
	controles.setAlignment(Pos.TOP_CENTER);
	System.out.println("Creada visual");
	
	HBox cartasJugadorEnMano = cartasJugador1EnMano;
	HBox cartasJugadorJugadas = cartasJugador1Jugadas;
	BotonEstoyListoEventHandler botonEstoyListoEventHandler = new BotonEstoyListoEventHandler (partido, cartasJugadorEnMano, cartasJugadorJugadas, botonera);
	botonEstoyListo.setOnAction(botonEstoyListoEventHandler);
	

	pantalla = new HBox (ambosPuntajes, campoDeJuego, controles);
	scene = new Scene(pantalla, 700,600);

	}
	
	private void botoneraRetrucar(List<Accion> accionesPosibles) {

		
	}

	public void enviarAccionAJugador(Accion accion, Jugador jugadorSiguienteAlActual, Vuelta vuelta) {
		
		this.botoneraRetrucar (accion.getAccionesPosibles());
	}
	
	
	public void mostrarCartasJugador (Jugador jugador, HBox cartasJugadorEnMano, HBox cartasJugadorJugadas){
		
		
		cartasJugadorEnMano.getChildren().clear();
		cartasJugadorJugadas.getChildren().clear();
		for(int i=0; i< jugador.getCantidadCartasEnMano(); i++){
			BotonCartaEnManoEventHandler botonCarta1EventHandler = new BotonCartaEnManoEventHandler(cartasJugadorEnMano, cartasJugadorJugadas, this.partido, jugador.getListaDeCartasEnMano().get(i));
			System.out.println(i);
			ImageView visualDeCartaEnMano = GeneradoresVisuales.getInstance().generadorDeVisualDeCarta(jugador.getListaDeCartasEnMano().get(i));
			Button botonCartaEnMano = new Button ();
			botonCartaEnMano.setGraphic(visualDeCartaEnMano);
			botonCartaEnMano.setOnAction(botonCarta1EventHandler);
			cartasJugadorEnMano.getChildren().add(botonCartaEnMano);
		}	
	}



	public void traspasarCartaDeManoAMesa(HBox cartasJugadorEnMano, HBox cartasJugadorJugadas, Carta carta) {
		
		int i = buscarPosicionDeCartaEnMano(carta);
		cartasJugadorJugadas.getChildren().add(cartasJugadorEnMano.getChildren().get(i));
		System.out.println("Cantidad de hijos: "+cartasJugadorEnMano.getChildren().size()+" Indice: "+i);
		//cartasJugadorEnMano.getChildren().remove(i);
		this.partido.getJugadorActual().bajarCarta(this.partido.getRondaActual().getVueltaActual(), carta);
	}


	public int buscarPosicionDeCartaEnMano (Carta carta){
			
		for (int i=0; i < partido.getJugadorActual().getCantidadCartasEnMano(); i++)
			if ((partido.getJugadorActual().getCartasEnMano().get(i))==carta){
			return i;	
			};
		throw new NoContieneCartaException();
	}

}

package com.interfazgrafica;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.Vuelta;
import com.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

import com.acciones.Accion;
import com.acciones.EstadoIndefinido;
import com.exceptions.NoContieneCartaException;
import com.exceptions.NoHayAccionesException;
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
	private VBox botonera;
	private VBox controles;
	private HBox pantalla;
	private HBox cartasJugador1EnMano;
	private HBox cartasJugador2EnMano;
	private List<HBox> listaDeCartasDeJugadoresEnMano;
	private CircularList<Integer> turnoJugador;
	private VBox tablero;
	
	private ImprimirTablero(){
		
	}
	
	public VBox getBotonera(){
		if(this.botonera == null){
			this.botonera = new VBox();
		}
		return this.botonera;
	}
	
	public VBox getTablero(){
		if(this.tablero == null){
			this.tablero = new VBox();
		}
		return this.tablero;		
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
	
	public void crearBotonera (VBox botonera){
		botonera.getChildren().clear();
		for (Accion accion : partido.getRondaActual().getVueltaActual().getAccionesDeVuelta())
		{
			if (accion.getEstado() instanceof EstadoIndefinido){
				Button unBoton = new Button();
				unBoton.setText(accion.getID());
				botonera.getChildren().add(unBoton);
				unBoton.setOnAction(new BotonAccionEventHandler(accion, partido.getRondaActual().getVueltaActual(), this.partido.getJugadorActual(), this.partido.getJugadorSiguienteAlActual()));
			
			}
		}
	}	
	
	public void crearBotoneraDeRetruque (Accion accionOriginal, VBox botonera, Vuelta vuelta){
		botonera.getChildren().clear();
		
		Label responde = new Label("Responde el Equipo: " + partido.getNumeroDeEquipo(partido.getEquipoDeJugador(accionOriginal.getDestino())));
		botonera.getChildren().add(responde);
		
		Button aceptar = new Button ("QUIERO");
		botonera.getChildren().add(aceptar);
		aceptar.setOnAction(new BotonAceptarEventHandler(accionOriginal));
		
		Button negar = new Button ("NO QUIERO");
		botonera.getChildren().add(negar);
		negar.setOnAction(new BotonNegarEventHandler(accionOriginal)); 
		
		try{
			for (Accion accion : accionOriginal.getAccionesPosibles())
			{
				Button unBoton = new Button();
				unBoton.setText(accion.getID());
				botonera.getChildren().add(unBoton);
				unBoton.setOnAction(new BotonAccionEventHandler(accion, vuelta, accionOriginal.getDestino(), accionOriginal.getOrigen()));			
			}
		}catch(NoHayAccionesException e){
			System.out.println("NO HAY MAS ACCIONES POSIBLES");
		}
	}	
	
	
	public void imprimirTablero (Partido partido){		
		GeneradoresVisuales generador = new GeneradoresVisuales();
		listaDeCartasDeJugadoresEnMano = new ArrayList<HBox>();
		for (int i=0 ; i < partido.getCantidadDeJugadoresTotales(); i++){
			listaDeCartasDeJugadoresEnMano.add(new HBox());
		}
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
	    this.tablero = new VBox (cartasJugador1Jugadas, cartasJugador2Jugadas);
	    
	    GeneradoresVisuales.getInstance().generarCartasDadasVuelta(partido.getOrdenJugadores(), listaDeCartasDeJugadoresEnMano);
	    this.asignarAlineaciones();
		
		
		VBox campoDeJuego = new VBox (listaDeCartasDeJugadoresEnMano.get(0), this.tablero, listaDeCartasDeJugadoresEnMano.get(1));
		campoDeJuego.setPadding(new Insets (20));
		
		turnoJugador = new CircularList<Integer>();
		turnoJugador.add(1);
		turnoJugador.add(2);
		turnoJugador.resetToFirst();
		
		this.generarBotonEstoyListo();

				
		pantalla = new HBox (ambosPuntajes, campoDeJuego, this.controles);
		scene = new Scene(pantalla, 700,600);

	}
	
	
	public void asignarAlineaciones (){
		
		for (int i=0 ; i < listaDeCartasDeJugadoresEnMano.size(); i++){
			if (i%2 == 0){
				this.listaDeCartasDeJugadoresEnMano.get(i).setAlignment(Pos.TOP_CENTER);
			}
			else{
				this.listaDeCartasDeJugadoresEnMano.get(i).setAlignment(Pos.BOTTOM_CENTER);
			}
		}
	}
	
	
	public void generarBotonEstoyListo(){
		Label turnoDe = new Label ("Turno de: JUGADOR ");				
		HBox esElTurnoDe = new HBox (turnoDe, new Label (turnoJugador.getCurrent().toString()));
		
		esElTurnoDe.setPadding(new Insets(20));
		Button botonEstoyListo = new Button ("Estoy Listo");
		this.botonera = new VBox (botonEstoyListo);
		this.getBotonera().setSpacing(10);
		this.getBotonera().setAlignment(Pos.TOP_CENTER);
		
		this.controles = new VBox (esElTurnoDe, this.getBotonera());
		this.controles.setAlignment(Pos.TOP_CENTER);
		
		BotonEstoyListoEventHandler botonEstoyListoEventHandler = new BotonEstoyListoEventHandler (partido, listaDeCartasDeJugadoresEnMano.get(0));
		botonEstoyListo.setOnAction(botonEstoyListoEventHandler);
	}
	
	
	public void transicionDeTurno(){
		
		this.generarBotonEstoyListo();
		GeneradoresVisuales.getInstance().generarCartasDadasVuelta(partido.getOrdenJugadores(), listaDeCartasDeJugadoresEnMano);
	}
	
	public void imprimirTurnoDeJugador (Jugador jugador, HBox cartasJugadorEnMano, HBox cartasJugadorJugadas){
		
		mostrarCartasJugador(jugador, cartasJugadorEnMano, cartasJugadorJugadas);
	}

	public void enviarAccionAJugador(Accion accion, Jugador jugadorSiguienteAlActual, Vuelta vuelta) {		
		this.crearBotoneraDeRetruque(accion, this.getBotonera(), vuelta);
	}
	
	
	public void mostrarCartasJugador (Jugador jugador, HBox cartasJugadorEnMano, HBox cartasJugadorJugadas){
		cartasJugadorEnMano.getChildren().clear();
		for(int i=0; i< jugador.getCantidadCartasEnMano(); i++){
			BotonCartaEnManoEventHandler botonCarta1EventHandler = new BotonCartaEnManoEventHandler(cartasJugadorEnMano, cartasJugadorJugadas, this.partido, jugador.getListaDeCartasEnMano().get(i));
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

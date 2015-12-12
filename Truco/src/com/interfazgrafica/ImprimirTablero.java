package com.interfazgrafica;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.Vuelta;
import com.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

import com.acciones.Accion;
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
	
	private static ImprimirTablero instance = null;
	
	private Scene scene;
	private Stage stage;
	private Partido partido;
	private VBox botonera;
	private VBox controles;
	private HBox pantalla;

	private List<HBox> listaDeCartasDeJugadoresEnMano;
	private List<HBox> listaDeCartasDeJugadoresJugadas;
	private VBox _tableroDeMesa;
	
	private VBox _puntajeEquipoUno;
	private VBox _puntajeEquipoDos;
	private HBox _tableroPuntajes;
	
	private Label _turnoDeLabel;
	private HBox _tableroTurnoDe;
	
	private VBox _tableroDeMano;
	
	private VBox _tableroDeManoYMesa;
		
	private ImprimirTablero(){
		
	}
	
	public VBox getBotonera(){
		if(this.botonera == null){
			this.botonera = new VBox();
		}
		return this.botonera;
	}
	
	public VBox getTablero(){
		if(this._tableroDeMesa == null){
			this._tableroDeMesa = new VBox();
		}
		return this._tableroDeMesa;		
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
				
		this.crearTableros();
		ImprimirTablero.getInstance().imprimirTablero();
		this.generarNuevaEscena();
	}
	

	public static ImprimirTablero getInstance(){
		if (ImprimirTablero.instance == null){
			ImprimirTablero.instance = new ImprimirTablero();
		}
		return ImprimirTablero.instance;
	}	
	
	public Scene getScene (){
		return this.scene;
	}
	
	public void crearBotoneraAcciones (){
		this.getBotonera().getChildren().clear();
		
		for (Accion accion : this.partido.getVueltaActual().getAccionesDeVuelta())
		{
			if (accion.getEstado().getID().equals(Accion.ESTADO_INDEFINIDO) && accion.puedePedirNuevaAccion(this.partido, this.partido.getJugadorActual())){
				Button unBoton = new Button();
				unBoton.setText(accion.getID());
				this.getBotonera().getChildren().add(unBoton);
				unBoton.setOnAction(new BotonAccionEventHandler(accion, this.partido.getVueltaActual(), this.partido.getJugadorActual(), this.partido.getJugadorSiguienteAlActual()));
			}
		}
	}	
	
	public void crearBotoneraDeRetruque (Accion accionOriginal, VBox botonera, Vuelta vuelta){
		botonera.getChildren().clear();
		
		Label responde = new Label( "Responde el Equipo: " + Integer.toString(partido.getNumeroDeEquipo(partido.getEquipoDeJugador(accionOriginal.getDestino())) + 1) );
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
				Accion nuevaAccion = accionOriginal.getNuevaAccion(accion, accionOriginal.getOrigen(), accionOriginal.getDestino(), this.partido);
				unBoton.setOnAction(new BotonAccionEventHandler(nuevaAccion, vuelta, accionOriginal.getDestino(), accionOriginal.getOrigen()));			
			}
		}catch(NoHayAccionesException e){
			System.out.println("NO HAY MAS ACCIONES POSIBLES");
		}
	}	
	
	private void generarPuntajesDeEquipos(){
		this._puntajeEquipoUno = new VBox (new Label ("EQUIPO 1"), new Label (Integer.toString(partido.getPuntosPrimerEquipo())));
		this._puntajeEquipoUno.setAlignment(Pos.TOP_CENTER);
		this._puntajeEquipoUno.setSpacing(10);

		this._puntajeEquipoDos = new VBox (new Label ("EQUIPO 2"), new Label (Integer.toString(partido.getPuntosUltimoEquipo())));
		this._puntajeEquipoDos.setAlignment(Pos.TOP_CENTER);
		this._puntajeEquipoDos.setSpacing(10);
		
		this._tableroPuntajes.getChildren().clear();
		this._tableroPuntajes.getChildren().add(_puntajeEquipoUno);
		this._tableroPuntajes.getChildren().add(_puntajeEquipoDos);
	}
	
	private void generarListaDeCartasEnManoDeJugadores(){
		this.listaDeCartasDeJugadoresEnMano = new ArrayList<HBox>();
		
		for (int i=0 ; i < partido.getCantidadDeJugadoresTotales(); i++){
			this.listaDeCartasDeJugadoresEnMano.add(new HBox());
		}
	}
	
	private void generarListaDeCartasJugadasDeJugadores(){
		this.listaDeCartasDeJugadoresJugadas = new ArrayList<HBox>();
		
		for (int i=0 ; i < partido.getCantidadDeJugadoresTotales(); i++){
			HBox nuevoEspacio = GeneradoresVisuales.getInstance().generarEspacioVacioVertical();
			nuevoEspacio.setAlignment(Pos.CENTER);
			this.listaDeCartasDeJugadoresJugadas.add(nuevoEspacio);
		}
	}
	
	private void agregarListaDeCartasJugadasATablero(){
		int size = this.listaDeCartasDeJugadoresJugadas.size();
		
		for(int i = 0; i < size; i++){
			this._tableroDeMesa.getChildren().add(this.listaDeCartasDeJugadoresJugadas.get(i));
		}
	}
	
	private void agregarListaDeCartasEnManoATableroDeMano(){
		int size = this.listaDeCartasDeJugadoresEnMano.size();
		
		for(int i = 0; i < size; i++){
			this._tableroDeMano.getChildren().add(this.listaDeCartasDeJugadoresEnMano.get(i));
		}
	}
	
	public void aceptarAccion(Accion accion){
		accion.aceptar();
		accion.reemplazarAccionOriginalEnVuelta(this.partido.getVueltaActual());
		accion.limpiarAccionesRelacionadasEnVuelta(this.partido.getVueltaActual());
		this.crearBotoneraAcciones();
	}
	
	public void negarAccion(Accion accion){
		accion.negar();
		accion.reemplazarAccionOriginalEnVuelta(this.partido.getVueltaActual());
		accion.limpiarAccionesRelacionadasEnVuelta(this.partido.getVueltaActual());
		this.crearBotoneraAcciones();
	}

	private void crearTableros(){
		this._tableroDeMesa = new VBox();
		
		this.botonera = new VBox();
		this.getBotonera().setSpacing(10);
		this.getBotonera().setAlignment(Pos.TOP_CENTER);
		
		this.controles = new VBox();
		this.controles.setAlignment(Pos.TOP_CENTER);
		
		this._tableroDeMano = new VBox ();
		this._tableroDeMano.setPadding(new Insets (20));
		
		this._tableroDeManoYMesa = new VBox();
		this._tableroDeManoYMesa.setPadding(new Insets (20));
		
		this._tableroPuntajes = new HBox();
		this._tableroPuntajes.setSpacing(15);
		this._tableroPuntajes.setPadding(new Insets(20));
		
		this._tableroTurnoDe = new HBox();
		this._tableroTurnoDe.setPadding(new Insets(20));
		
		this.pantalla = new HBox();
	}
	
	private void generarPantalla(){
		this.pantalla.getChildren().add(this._tableroPuntajes);	
		this.pantalla.getChildren().add(this._tableroDeManoYMesa);
		this.pantalla.getChildren().add(this.controles);
	}
	
	private void generarTableroDeManoYMesa(){
		this._tableroDeManoYMesa.getChildren().add(this._tableroDeMano.getChildren().get(0));
		this._tableroDeManoYMesa.getChildren().add(this._tableroDeMesa);
		this._tableroDeManoYMesa.getChildren().add(this._tableroDeMano.getChildren().get(0));
	}
	
	private void generarNuevaEscena(){
		this.scene = new Scene(pantalla, 700,600);
		this.stage.setScene (scene);
		this.stage.show();
	}

	public void imprimirTablero (){	
		this.generarListaDeCartasEnManoDeJugadores();

		this.generarListaDeCartasJugadasDeJugadores();
	    	    
	    this.agregarListaDeCartasJugadasATablero();
	    
	    GeneradoresVisuales.getInstance().generarCartasDadasVuelta(partido.getOrdenJugadores(), listaDeCartasDeJugadoresEnMano);
	    this.asignarAlineaciones();
	    
		this.agregarListaDeCartasEnManoATableroDeMano();
		
		this.generarTableroDeManoYMesa();
		
		this.generarBotonEstoyListo();
			
		this.generarPuntajesDeEquipos();
		
		this.generarPantalla();
	}	
	
	public void asignarAlineaciones (){		
		for (int i=0 ; i < listaDeCartasDeJugadoresEnMano.size(); i++){
			if (i%2 == 0){
				this.listaDeCartasDeJugadoresEnMano.get(i).setAlignment(Pos.BOTTOM_CENTER);
			}
			else{
				this.listaDeCartasDeJugadoresEnMano.get(i).setAlignment(Pos.TOP_CENTER);
			}
		}
	}
	
	private void generarLabelTurnoDe(int numeroJugadorActual){
		this._turnoDeLabel = new Label ("Turno de: JUGADOR ");	
		
		this._tableroTurnoDe.getChildren().clear();
		this._tableroTurnoDe.getChildren().add(this._turnoDeLabel);
		this._tableroTurnoDe.getChildren().add(new Label (Integer.toString(numeroJugadorActual + 1)));
	}
	
	public void ejecutarEstoyListo(){
		ImprimirTablero.getInstance().crearBotoneraAcciones();
		
		int numeroDeJugadorActual = partido.getNumeroJugadorActual();			
		ImprimirTablero.getInstance().mostrarCartasJugador(partido.getJugadorActual(), this.listaDeCartasDeJugadoresEnMano.get(numeroDeJugadorActual), this.listaDeCartasDeJugadoresJugadas.get(numeroDeJugadorActual));			
	}
		
	public void generarBotonEstoyListo(){
		int numeroDeJugadorActual = partido.getOrdenJugadores().getIndexOf(partido.getJugadorActual());
		
		this.generarLabelTurnoDe(numeroDeJugadorActual);
		
		Button botonEstoyListo = new Button ("Estoy Listo");
		BotonEstoyListoEventHandler botonEstoyListoEventHandler = new BotonEstoyListoEventHandler();
		botonEstoyListo.setOnAction(botonEstoyListoEventHandler);
		
		this.botonera.getChildren().clear();
		this.botonera.getChildren().add(botonEstoyListo);
				
		this.controles.getChildren().clear();
		this.controles.getChildren().add(this._tableroTurnoDe);
		this.controles.getChildren().add(this.getBotonera());
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
			cartasJugadorEnMano.getChildren().add(this.crearBotonCarta(jugador.getCartasEnMano().get(i)));
		}	
	}
	
	private Button crearBotonCarta(Carta carta){
		Button nuevoBotonCarta = new Button();
		ImageView visualDeCartaEnMano = GeneradoresVisuales.getInstance().generadorDeVisualDeCarta(carta);
		nuevoBotonCarta.setGraphic(visualDeCartaEnMano);
		nuevoBotonCarta.setOnAction(new BotonCartaEnManoEventHandler(carta));
		return nuevoBotonCarta;
	}

	public void traspasarCartaDeManoAMesa(HBox cartasJugadorEnMano, HBox cartasJugadorJugadas, Carta carta) {
		int posicionEnMano = getPosicionDeCartaEnMano(partido.getJugadorActual(), carta);
		cartasJugadorJugadas.getChildren().add(cartasJugadorEnMano.getChildren().get(posicionEnMano));
	}
	
	private boolean limpiarTableros(){		
		if(this.partido.getRondaActual().esInicioDeRonda()){
			for(int i = 0; i < this.listaDeCartasDeJugadoresEnMano.size(); i++){
				this.listaDeCartasDeJugadoresEnMano.get(i).getChildren().clear();
			}
			
			for(int i = 0; i < this.listaDeCartasDeJugadoresJugadas.size(); i++){
				this.listaDeCartasDeJugadoresJugadas.get(i).getChildren().clear();
			}
						
			this._tableroDeMesa.getChildren().clear();
			this._tableroDeManoYMesa.getChildren().clear();	
			this.pantalla.getChildren().clear();
			return true;
		}
		
		return false;
	}
	
	public void bajarCarta(Carta carta){
		int numeroJugadorActual = partido.getNumeroJugadorActual();
		this.traspasarCartaDeManoAMesa(this.listaDeCartasDeJugadoresEnMano.get(numeroJugadorActual), this.listaDeCartasDeJugadoresJugadas.get(numeroJugadorActual), carta);
		
		this.partido.getJugadorActual().bajarCarta(this.partido.getVueltaActual(), carta);
		
		if(this.limpiarTableros())
			this.imprimirTablero();
		else
			this.transicionDeTurno();	
	}

	public int getPosicionDeCartaEnMano (Jugador jugador, Carta carta){			
		return jugador.getPosiconDeCarta(carta);
	}

}

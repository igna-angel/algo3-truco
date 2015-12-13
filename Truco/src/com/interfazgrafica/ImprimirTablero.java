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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
	
	public void generarPartido2Jugadores(boolean conOSinFlor){		
		this.stage = new Stage();
		this.partido = new Partido(conOSinFlor);
		
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
				if((accion.esDeFlor() && !partido.getJugadorActual().hayFlor())) continue; 
				Button unBoton = new Button();
				unBoton.setText(accion.getID());
				this.getBotonera().getChildren().add(unBoton);
				unBoton.setOnAction(new BotonAccionEventHandler(accion, this.partido.getVueltaActual(), this.partido.getJugadorActual(), this.partido.getJugadorSiguienteAlActual()));
			}
		}
	}	
	
	public void imprimirPuntajeTanto(int puntosEnvido){
		String informePuntos = "Tu tanto es de: " + puntosEnvido;
		Label cantidadDeTanto = new Label();
		cantidadDeTanto.setText(informePuntos);
		cantidadDeTanto.setFont(Font.font ("Calisto MT", 17));
		cantidadDeTanto.setTextFill(Color.DARKBLUE);
		this.botonera.getChildren().add(cantidadDeTanto);
	}
	
	public void mostrarCartasJugador(){
		int numeroDeJugadorActual = partido.getNumeroJugadorActual();	
		ImprimirTablero.getInstance().mostrarCartasJugador(partido.getJugadorActual(), this.listaDeCartasDeJugadoresEnMano.get(numeroDeJugadorActual), this.listaDeCartasDeJugadoresJugadas.get(numeroDeJugadorActual));	
	}
	
	private void crearBotoneraDeRetruqueSegunCorresponda(Accion accion, VBox botonera, Vuelta vuelta){
		if(accion.esDeTanto()){
			this.crearBotoneraDeTanto(accion,this.getBotonera(),vuelta);
		} else {
			this.crearBotoneraDeRetruque(accion, this.getBotonera(), vuelta);
		}
	}
	
	public void crearBotoneraDeTanto(Accion accion, VBox botonera, Vuelta vuelta){
		this.getBotonera().getChildren().clear();
		Label responde = new Label( "Responde el Equipo: " + Integer.toString(partido.getNumeroDeEquipo(partido.getEquipoDeJugador(accion.getDestino())) + 1) );
		this.getBotonera().getChildren().add(responde);
		GeneradoresVisuales.getInstance().generarCartasDadasVuelta(partido.getOrdenJugadores(), listaDeCartasDeJugadoresEnMano);
		Button responderTanto = new Button("Listo para responder al tanto");
		this.getBotonera().getChildren().add(responderTanto);
		responderTanto.setOnAction(new BotonResponderTantoEventHandler(accion,botonera,vuelta));
	}
	
	public void crearBotoneraDeRetruque (Accion accionOriginal, VBox botonera, Vuelta vuelta){
		if(accionOriginal.esDeFlor() && !this.getPartido().getEquipoDeJugador(accionOriginal.getDestino()).hayFlor()){
			this.negarAccion(accionOriginal);
			return;
		}
		
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
			HBox cartasDeUnJugadorEnMano = new HBox();
			cartasDeUnJugadorEnMano.setMinSize(300, 140);
			this.listaDeCartasDeJugadoresEnMano.add(cartasDeUnJugadorEnMano);
		}
	}
	
	private void generarListaDeCartasJugadasDeJugadores(){
		this.listaDeCartasDeJugadoresJugadas = new ArrayList<HBox>();
		
		for (int i=0 ; i < partido.getCantidadDeJugadoresTotales(); i++){
			HBox nuevoEspacio = new HBox();
			nuevoEspacio.setMinSize(300, 140);
			nuevoEspacio.setAlignment(Pos.CENTER);
			nuevoEspacio.setSpacing(5);
			nuevoEspacio.setPadding(new Insets(15));
			nuevoEspacio.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			
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
		ImprimirTablero.getInstance().mostrarCartasJugador();
	}
	
	public void negarAccion(Accion accion){
		if(accion.esDeTruco()){
			accion.negar(this.getPartido().getRondaActual());
			this.limpiarTableros();
			this.imprimirTablero();
		}else if(accion.esDeTanto()){
			accion.negar();
			accion.reemplazarAccionOriginalEnVuelta(this.partido.getVueltaActual());
			accion.limpiarAccionesRelacionadasEnVuelta(this.partido.getVueltaActual());
			this.crearBotoneraAcciones();
			ImprimirTablero.getInstance().mostrarCartasJugador();
		}else{
			accion.negar();
			accion.limpiarAccionesRelacionadasEnVuelta(this.partido.getVueltaActual());
			this.crearBotoneraAcciones();
			ImprimirTablero.getInstance().mostrarCartasJugador();
		}
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
		this.crearBotoneraDeRetruqueSegunCorresponda(accion,this.getBotonera(),vuelta);
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

	public Partido getPartido() {
		return this.partido;
	}
	
	private ImageView crearImagenTruco(){
        Image imagenTruco = new Image("https://raw.githubusercontent.com/igna-angel/algo3-truco/5e5aa5b00030f72bbe2f85ab055ca5482076829b/cartas%20espa%C3%B1olas/truco.png");
        ImageView imagen = new ImageView (imagenTruco);
        imagen.setFitWidth(120);
        imagen.setPreserveRatio(true);
        imagen.setSmooth(true);
        imagen.setCache(true);
        return imagen;
	}
	
	private Label crearPresentacionDeFlor() {
		Label presentacion = new Label ();
		presentacion.setText("Por favor determine criterio de \n"
				+ "juego con o sin Flor: ");
		presentacion.setFont(Font.font ("VerdanaItalic" , FontWeight.BOLD , 12));
		presentacion.setTextFill(Color.ANTIQUEWHITE);
		presentacion.setTextAlignment(TextAlignment.CENTER);
		
		return presentacion;
	}
	
	private Button crearBoton(String nombre){
		Button botonFlor = new Button();
		botonFlor.setText (nombre);
		return botonFlor;
	}
	
	private VBox crearContenedorFlor(Label presentacion, ImageView imagenInicio, Button botonConFlor, Button botonSinFlor){
		VBox contenedorFlor = new VBox(presentacion, botonConFlor, botonSinFlor,imagenInicio);
		contenedorFlor.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		contenedorFlor.setAlignment(Pos.CENTER);
		contenedorFlor.setSpacing(10);
		contenedorFlor.setPadding(new Insets(15));		
		return contenedorFlor;
	}

	public void determinarJuegoConOSinFlor(Stage stage) {
		stage.setTitle ("FonTruco");
		boolean sinFlor = false;
		boolean conFlor = true;
		Label presentacion = crearPresentacionDeFlor();
		ImageView imagenInicio = crearImagenTruco();
		Button botonConFlor = crearBoton("Con Flor");
		Button botonSinFlor = crearBoton("Sin Flor");
		VBox contenedorFlor = crearContenedorFlor(presentacion,imagenInicio,botonConFlor,botonSinFlor);
		
        Scene scene = new Scene(contenedorFlor, 300, 340);
		
		stage.setScene(scene);

		Boton2JugadoresEventHandler boton2JugadoresConFlorEventHandler = new Boton2JugadoresEventHandler(conFlor,stage);
        botonConFlor.setOnAction(boton2JugadoresConFlorEventHandler);
        
        Boton2JugadoresEventHandler boton2JugadoresSinFlorEventHandler = new Boton2JugadoresEventHandler(sinFlor,stage);
        botonSinFlor.setOnAction(boton2JugadoresSinFlorEventHandler);
	}
	
	private Label crearMensajeFinalDePartida(){
		Label mensajeFinal = new Label ();
		mensajeFinal.setText("El ganador de la partida es \n"
							+ "el equipo numero: " + this.getPartido().getEquipoGanador() + "!");
		
		mensajeFinal.setFont(Font.font ("VerdanaItalic" , FontWeight.BOLD , 16));
		mensajeFinal.setTextFill(Color.ANTIQUEWHITE);
		return mensajeFinal;
	}
	
	private Label crearMensajeElegirOpcion(){
		Label elegirOpcion = new Label ();
		elegirOpcion.setText("Por favor elija una opcion");
		elegirOpcion.setFont(Font.font ("VerdanaItalic" , FontWeight.BOLD , 13));
		elegirOpcion.setTextFill(Color.ANTIQUEWHITE);
		return elegirOpcion;
	}

	public void imprimirPantallaFinDeJuego(){
		
		Label mensajeFinal = crearMensajeFinalDePartida();
		Label elegirOpcion = crearMensajeElegirOpcion();
        ImageView imagenFinal = crearImagenTruco();
		Button botonJugarRevancha = crearBoton("Jugar Revancha");
		Button botonVolverAlMenu = crearBoton("Volver al menu principal");
		Button botonSalir = crearBoton("Salir");
		
		HBox mensaje = new HBox(mensajeFinal);
		mensaje.setAlignment(Pos.TOP_CENTER);
		mensaje.setPadding(new Insets (35));
	
		VBox botones = new VBox(imagenFinal,elegirOpcion,botonJugarRevancha,botonVolverAlMenu,botonSalir);
		botones.setAlignment(Pos.CENTER);
		botones.setSpacing(15);
		botones.setPadding(new Insets (50));
		
		VBox contenedorFinal = new VBox(mensaje,botones);
		contenedorFinal.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		contenedorFinal.setSpacing(10);
		contenedorFinal.setPadding(new Insets(15));
		
        Scene scene = new Scene(contenedorFinal, 700, 600);
		
		stage.setScene(scene);
		
		Boton2JugadoresEventHandler boton2JugadoresNuevaPartidaEventHandler = new Boton2JugadoresEventHandler(this.getPartido().seJuegaConnFlor(),stage);
		botonJugarRevancha.setOnAction(boton2JugadoresNuevaPartidaEventHandler);
		
		BotonVueltaAlMenuEventHandler botonVueltaMenuEventHandler = new BotonVueltaAlMenuEventHandler();
		botonVolverAlMenu.setOnAction(botonVueltaMenuEventHandler);
		
		BotonCerrarJuegoEventHandler botonSalirDelJuegoEventHandler = new BotonCerrarJuegoEventHandler(this.stage);
		botonSalir.setOnAction(botonSalirDelJuegoEventHandler);
	}

	public void nuevaPresentacion() throws Exception {
		Presentacion nuevaPresentacion = new Presentacion();
		nuevaPresentacion.start(new Stage());
		stage.close();
	}

}

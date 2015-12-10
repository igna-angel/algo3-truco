package com.modelo;

import java.util.List;
import java.util.Scanner;

import com.exceptions.AccionNoPosibleException;
import com.exceptions.NoContieneCartaException;
import com.modelo.acciones.envido.*;
import com.modelo.acciones.truco.AccionTruco;
import com.modelo.acciones.truco.NoQuiero;
import com.modelo.acciones.truco.Quiero;
import com.modelo.acciones.truco.ReTruco;
import com.modelo.acciones.truco.TrucoDecorator;
import com.modelo.acciones.truco.ValeCuatro;
import com.modelo.cartas.Carta;


public abstract class Jugador implements IRecibible{
	
	protected Mano _mano;
	
	public Jugador(){
		this._mano = new Mano();
	}
	
	protected Mano getMano(){
		return this._mano;
	}
	
	public void recibirCarta(Carta carta){
		this.getMano().recibirCarta(carta);
	}
	
	public int getCantidadCartas(){
		return this.getMano().getCantidadCartas();
	}
	
	public int getCartasEnMano(){
		return this.getMano().getCartasEnMano();
	}
	
	public List<Carta> getListaDeCartasEnMano(){
		return this.getMano().getCartas();
	}
	
	public void imprimirCartas(){
		this.getMano().getCartas().forEach(carta ->
			{
				System.out.println("Palo: " + carta.getPalo() + " Numero: " + carta.getNumero());
			}
		);
	}
	
	public void bajarCarta(Vuelta vuelta, Carta carta){
		try{
			this.getMano().bajarCarta(carta);
			vuelta.recibirCarta(carta);
		}catch(NoContieneCartaException e){
			// relanzar exception o dejarla pasar y catchear en otro lado
		}
	}
		
	public void devolverCartas(){
		this.getMano().devolverCartas();
	}

	public TrucoDecorator responderA(TrucoDecorator accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere truco: ");
		
		String respuesta = respuestaAQuerer();

		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
	
		if (respuesta.equals("retruco")){
			return manejadorDeRonda.cantarReTruco(new ReTruco(accion));
			
		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	private String respuestaAQuerer(){
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		return respuesta;
	}
	
	public TrucoDecorator responderA(ReTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere ReTruco: ");
		
		String respuesta = respuestaAQuerer();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
		
		if (respuesta.equals(("valecuatro"))){
			return manejadorDeRonda.cantarValeCuatro(new ValeCuatro(accion));

		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	public TrucoDecorator responderA(ValeCuatro accion) {
		
		System.out.print("Quiere ValeCuatro: ");
	
		String respuesta = respuestaAQuerer();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else {
			throw new AccionNoPosibleException();
		}
		
	}

	public TantoDecorator responderA(Envido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere Envido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		//scan.close();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero);
			return noquiero;
			
		} else if ((respuesta.equals("envido")) && (this.accionEnvidoValida(accion))){
			Envido nuevoEnvido = new Envido(new QuieroTanto(accion, this, accion.getOrigen()), this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(nuevoEnvido);
			return nuevoEnvido;
			
		} else if (respuesta.equals(("realenvido"))){
			RealEnvido realEnvido = new RealEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(realEnvido);
			return realEnvido;
			
		} else if (respuesta.equals(("faltaenvido"))){
			FaltaEnvido faltaEnvido = new FaltaEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(faltaEnvido);
			return faltaEnvido;
		}

		
		throw new AccionNoPosibleException();
	}

	private boolean accionEnvidoValida(Envido accion) {
		if (accion.cantar() < 3){
			return true;
		} else {
			return false;
		}
	}

	public TantoDecorator responderA(RealEnvido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere RealEnvido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero); 
			return noquiero;
			
		} else if (respuesta.equals(("faltaenvido"))){
			FaltaEnvido faltaEnvido = new FaltaEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(faltaEnvido);
			return faltaEnvido;
		}
		
		throw new AccionNoPosibleException();
	}

	public TantoDecorator responderA(FaltaEnvido accion, ManejadorDeRonda manejadorDeRonda) {
		System.out.print("Quiere FaltaEnvido: ");

		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero);
			return noquiero;
		}
		
		throw new AccionNoPosibleException();
	}
	
	private void mostrarOpcionSegunFlorOContraFlor(Flor accion){
		if (accion.cantar() == 3){
			System.out.print("Respuesta Flor: ");
		} else {
			System.out.print("Respuesta ContraFlor: ");
		}
	}
	
	public TantoDecorator responderA(Flor accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		this.mostrarOpcionSegunFlorOContraFlor(accion);
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
//		scan.close();

		if (respuesta.equals("conflormeachico")){
			FlorMeAchico florMeAchico = new FlorMeAchico(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(florMeAchico);
			return florMeAchico;
			
		} else if (respuesta.equals("conflorquiero")){
			FlorQuiero quiero = new FlorQuiero(accion,this,accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("contraflor") && (this.accionContraFlorValida(accion))){
			
			Flor contraFlor = new Flor(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(contraFlor);
			return contraFlor;
			
		} else if (respuesta.equals("contrafloralresto")){
			ContraFlorAlResto contraFlorAlResto = new ContraFlorAlResto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(contraFlorAlResto);
			return contraFlorAlResto;
			
		} else {
			throw new AccionNoPosibleException();
		}
	}
	
	private boolean accionContraFlorValida(Flor accion) {
		if (accion.cantar() < 6){
			return true;
		} else {
			return false;
		}
	}

	public TantoDecorator responderA(ContraFlorAlResto accion, ManejadorDeRonda manejadorDeRonda) {
		System.out.print("Respuesta ContraFlorAlResto: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("conflorquiero")){
			FlorQuiero quiero = new FlorQuiero(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("conflornoquiero")){
			FlorMeAchico noquiero = new FlorMeAchico(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero);
			return noquiero;
		}
		
		throw new AccionNoPosibleException();
	}

	public int getTantoEnMano(){
		return this.getMano().getMaximosPuntosEnvido();
	}

	public abstract void jugar(Vuelta vuelta);

	public boolean tieneCarta(Carta cartaGanadora) {
		return this.getMano().contiene(cartaGanadora);
	}
	
	public boolean hayFlor(){
		return this.getMano().hayFlor();
	}

	public int getFlorEnMano() {
		if(!this.hayFlor()) return 0;
		
		return this.getMano().getPuntosFlorEnMano();
	}
}

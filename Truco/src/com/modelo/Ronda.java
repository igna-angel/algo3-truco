package com.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.exceptions.TantoYaCantadoException;
import com.exceptions.AccionNoPosibleException;
import com.exceptions.NoHayGanadorException;
import com.exceptions.NoHayVueltasException;
import com.exceptions.NoSeEncuentraJugadorException;
import com.exceptions.VueltaParaCantarTantoNoPosibleException;
import com.modelo.acciones.envido.Envido;
import com.modelo.acciones.envido.TantoDecorator;
import com.modelo.acciones.envido.FaltaEnvido;
import com.modelo.acciones.envido.Flor;
import com.modelo.acciones.envido.RealEnvido;
import com.modelo.acciones.envido.Tanto;
import com.modelo.acciones.truco.AccionTruco;
import com.modelo.acciones.truco.ReTruco;
import com.modelo.acciones.truco.Truco;
import com.modelo.acciones.truco.TrucoDecorator;
import com.modelo.acciones.truco.TrucoNormal;
import com.modelo.acciones.truco.ValeCuatro;
import com.modelo.cartas.Carta;

public abstract class Ronda {

	private static final int NUMERO_MAXIMO_VUELTAS = 3;
	
	private Partido _partido;
	private Jugador _repartio;

	private Stack<Vuelta> _vueltas;
	private List<Jugador> _ganadoresVueltas = null;

	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._ganadoresVueltas = new ArrayList<Jugador>();
		this._vueltas = new Stack<Vuelta>();
	}

	public abstract Ronda getRondaSiguiente(boolean esPicaPica);

	public void asignarPuntos(int puntajeA, int puntajeB){
		this.getPartido().agregarPuntos(puntajeA, puntajeB);
	}

	protected Partido getPartido(){
		return this._partido;
	}

	public Jugador getRepartio(){
		return this._repartio;
	}

	public Jugador getJugadorSiguienteAlActual(){
		return this.getPartido().getJugadorSiguienteAlActual();
	}

	protected Stack<Vuelta> getVueltas(){
		return this._vueltas;
	}

	public Vuelta getVueltaActual(){
		if(this.getVueltas().isEmpty()) throw new NoHayVueltasException();
		return this.getVueltas().peek();
	}
	
	public void nuevaVueltaSecundaria(AccionTruco accion){
		this.getVueltas().push(new Vuelta(this, accion,this.getPartido().getJugadorSiguienteA(this.getRepartio())));
	}

	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}

	public void nuevaVuelta() {
		if(this.getVueltas().isEmpty())
			this.getVueltas().push(new Vuelta(this, this.getPartido().getJugadorSiguienteA(this.getRepartio())));
		else
			this.getVueltas().push(new Vuelta(this, this.getVueltaActual().getJugadorConCartaGanadora()));
	}

	public void seCantoTruco(){
		this.getVueltaActual().getAccionTruco().trucoNoCantadoPreviamente();
		Truco trucoCantado = new Truco(new TrucoNormal());
		TrucoDecorator truco = this._partido.getManejadorDeRonda().cantarTruco(trucoCantado);
		this.getVueltaActual().setAccionTruco(truco);
		this.getVueltaActual().siTrucoNoQueridoFinalizarRonda(this,this._partido);
	}

	public void seCantoReTruco(){
		this.getVueltaActual().getAccionTruco().trucoOReTrucoCantadoPreviamente();
		ReTruco reTrucoCantado = new ReTruco(this._vueltas.peek().getAccionTruco());
		TrucoDecorator reTruco = this._partido.getManejadorDeRonda().cantarReTruco(reTrucoCantado);
		this.getVueltaActual().setAccionTruco(reTruco);
		this.getVueltaActual().siReTrucoNoQueridoFinalizarRonda(this,this._partido);
	}

	public void seCantoValeCuatro(){
		this.getVueltaActual().getAccionTruco().reTrucoOValeCuatroCantadoPreviamente();
		ValeCuatro valeCuatroCantado = new ValeCuatro(this._vueltas.peek().getAccionTruco());
		TrucoDecorator valeCuatro = this._partido.getManejadorDeRonda().cantarValeCuatro(valeCuatroCantado);
		this.getVueltaActual().setAccionTruco(valeCuatro);
		this.getVueltaActual().siValeCuatroNoQueridoFinalizarRonda(this,this._partido);
	}

	public void agregarPuntajeTruco(){

		Accion accionFinal = this.getVueltaActual().getAccionTruco();

		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;

		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}

	public void seCantoEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		
		Envido envidoCantado = new Envido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		TantoDecorator desicion = this.getPartido().getManejadorDeRonda().cantarEnvido(envidoCantado);
		Accion envido = (Accion)desicion;
		this.getVueltaActual().getAccionesEnvido().add(envido);
//		this.getPartido().getManejadorDeRonda().ejecutarRespuestaTanto(desicion);
	}
	
	private boolean esPrimeraVuelta() {
		return this.getVueltas().size() == 1;
	}

	public void seCantoRealEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		RealEnvido realEnvidoCantado = new RealEnvido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		Accion realEnvido = this.getPartido().getManejadorDeRonda().cantarRealEnvido(realEnvidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(realEnvido);
	}

	private boolean yaSeCantoEnvido() {
		return !this.getVueltaActual().getAccionesEnvido().isEmpty();
	}

	public void seCantoFaltaEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		FaltaEnvido faltaEnvidoCantado = new FaltaEnvido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		Accion faltaEnvido = this.getPartido().getManejadorDeRonda().cantarFaltaEnvido(faltaEnvidoCantado);

		Envido envidoCantado = new Envido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		Accion envido = this._partido.getManejadorDeRonda().cantarEnvido(envidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(envido);
	}

	public void seCantoFlor(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		
//		Jugador jugadorAuxiliar2 = jugadorOrigen;
//		
//		Jugador jugadorAuxiliar = new JugadorHumano();
//		jugadorAuxiliar.recibirCarta(new CartaAnchoEspada());
//		jugadorAuxiliar.recibirCarta(new CartaSieteEspada());
//		jugadorAuxiliar.recibirCarta(new CartaTres(Palo.Espada));
//		
//		jugadorOrigen = jugadorAuxiliar;
		
		if(this.jugadorNoTieneFlor(jugadorOrigen)) throw new AccionNoPosibleException();
		
		Flor florCantada = new Flor(new Tanto(),jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		
//		jugadorContrarioConFlor = this.buscarJugadorConFlor();
		
		TantoDecorator desicion = this.getPartido().getManejadorDeRonda().cantarFlor(florCantada);
		Accion flor = (Accion)desicion;
		this.getVueltaActual().getAccionesEnvido().add(flor);
	}
	
	private boolean jugadorNoTieneFlor(Jugador jugador){
		return (!jugador.getMano().hayFlor());
	}
	
	public Jugador getJugadorActual() {
		return this.getVueltaActual().getJugadorActual();
	}

	public void jugar() {
		while(!this.esFinDeRonda()){
			this.nuevaVuelta();
			this.getVueltaActual().jugar();
			this.agregarGanadorDeVuelta(this.getVueltaActual().getJugadorConCartaGanadora());
		}
	}

	public Jugador getJugadorConCartaGanadora(Carta cartaGanadora) {
		return this.getPartido().getJugadorConCartaGanadora(cartaGanadora);
	}
	
	public void agregarGanadorDeVuelta(Jugador jugador){
		this._ganadoresVueltas.add(jugador);
	}

	private List<Jugador> getGanadoresDeVueltas(){
		return this._ganadoresVueltas;
	}
	
	private boolean esFinDeRonda(){
		return this.hayGanador() || this.getVueltas().size() == Ronda.NUMERO_MAXIMO_VUELTAS;
	}

	private boolean hayGanador() {
		return this.getVueltas().size() >= 2 && (this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(0)) == this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(1)));
	}

	public Equipo getEquipoGanador() {
		if(!this.hayGanador()) throw new NoHayGanadorException();
		
		List<Equipo> equiposGanadores = new ArrayList<Equipo>();
		
		for(Jugador jugador : this.getGanadoresDeVueltas()){
			equiposGanadores.add(this.getPartido().getEquipoDeJugador(jugador));
		}
		
		Equipo ganadorA = equiposGanadores.get(0);
		Equipo ganadorB = null;
		
		for(int i = 1; i < equiposGanadores.size(); i++){
			if(ganadorA == equiposGanadores.get(i))
				return ganadorA;
			else ganadorB = equiposGanadores.get(i);
		}
		
		return ganadorB;
	}
	
	public Jugador getJugadorConMayorTantoEnEquipo(Equipo equipo){
		return equipo.getJugadorConMayorTanto();
	}
	
	public Jugador getJugadorConMayorFlorEnEquipo(Equipo equipo){
		return equipo.getJugadorConMayorFlor();
	}

	public int getMayorFlorDeEquipo(Equipo equipo){
		return equipo.getMayorFlor();
	}
	
	public Jugador getJugadorManoEntre(Jugador jugadorA, Jugador jugadorB){
		Jugador jugadorInicial = this.getPartido().getJugadorSiguienteA(this.getRepartio());
		Jugador jugadorActual = jugadorInicial;
		
		do{		
			if(jugadorA == jugadorInicial) return jugadorA;
			else if (jugadorB == jugadorInicial) return jugadorB;
			jugadorActual = this.getPartido().getJugadorSiguienteA(jugadorActual);
		}while(jugadorActual != jugadorInicial);
		
		throw new NoSeEncuentraJugadorException();
	}
}

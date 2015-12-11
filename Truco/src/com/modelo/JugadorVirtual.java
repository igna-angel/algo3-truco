package com.modelo;

public class JugadorVirtual extends Jugador {
	private static final int MIN_TANTO_ENVIDO = 23;
	private static final int MIN_TANTO_REAL_ENVIDO = 28;
	private static final int MIN_TANTO_FALTA_ENVIDO = 31;
	
	public JugadorVirtual() {
		super();
	}
/*	
	public void elegirCartaABajarYBajarla (Vuelta vuelta) {
		Carta cartaAJugar;
		
		try{
			cartaAJugar = this.getMano().getCartaGanadoraMinimaA(vuelta.getCartaGanadora());
		}catch (EmptyListException e){
			cartaAJugar = this.getMano().getCartaMasBaja();
		}
		
		this.bajarCarta(vuelta, cartaAJugar);
	}
	
	public void cantarTantoOFlorSiCorresponde (Ronda ronda) {
		int tantoEnMano = this._mano.getTantoEnMano();
		if (this._mano.hayFlor()) {
			ronda.seCantoFlor(this);
		}
		
		else if (tantoEnMano >= MIN_TANTO_FALTA_ENVIDO) {
			ronda.seCantoFaltaEnvido(this);
		}
		
		else if (tantoEnMano >= MIN_TANTO_REAL_ENVIDO) {
			ronda.seCantoRealEnvido(this);
		}
		
		else if (tantoEnMano >= MIN_TANTO_ENVIDO) {
			ronda.seCantoEnvido(this);
		}
	}
	
	// Dejo todas las respuestas de truco en quiero por ahora, hasta determinar de que manera armar la IA para estos casos
	public Accion responderA(AccionTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		return new Quiero(accion);
		/*System.out.print("Quiere truco: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		scan.close();

		
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
	}	*/
	/*
	public TrucoDecorator responderA(ReTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		return new Quiero(accion);

	}	
	
	public TrucoDecorator responderA(ValeCuatro accion) {
		return new Quiero(accion);
	}

	public TantoDecorator responderA(Envido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		int tantoEnMano = this._mano.getTantoEnMano();
		
		if (tantoEnMano < MIN_TANTO_ENVIDO){
			return new NoQuieroTanto(accion, this, accion.getOrigen());
		} else
		
		if (tantoEnMano >= MIN_TANTO_ENVIDO && tantoEnMano < MIN_TANTO_REAL_ENVIDO){
			return new QuieroTanto(accion, this, accion.getOrigen());
		} else if (tantoEnMano >= MIN_TANTO_REAL_ENVIDO && tantoEnMano < MIN_TANTO_FALTA_ENVIDO){
			return manejadorDeRonda.cantarRealEnvido(new RealEnvido(accion, this, accion.getOrigen()));
		} else if (tantoEnMano >= MIN_TANTO_FALTA_ENVIDO){
			return manejadorDeRonda.cantarFaltaEnvido(new FaltaEnvido(accion, this, accion.getOrigen()));
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public TantoDecorator responderA(RealEnvido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		int tantoEnMano = this._mano.getTantoEnMano();
		
		if (tantoEnMano < MIN_TANTO_REAL_ENVIDO){
			return new NoQuieroTanto(accion, this, accion.getOrigen());
		} else
		
		if (tantoEnMano >= MIN_TANTO_REAL_ENVIDO && tantoEnMano < MIN_TANTO_FALTA_ENVIDO){
			return new QuieroTanto(accion, this, partido.getJugadorSiguienteA(this));
		} else 
			
		if (tantoEnMano >= MIN_TANTO_FALTA_ENVIDO){
			return manejadorDeRonda.cantarFaltaEnvido(new FaltaEnvido(accion, this, accion.getOrigen()));
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public TantoDecorator responderA(FaltaEnvido accion) {
		int tantoEnMano = this._mano.getTantoEnMano();
		
		if (tantoEnMano < MIN_TANTO_FALTA_ENVIDO){
			return new NoQuieroTanto(accion, this, accion.getOrigen());
		} else
		
		if (tantoEnMano >= MIN_TANTO_FALTA_ENVIDO){
			return new QuieroTanto(accion, this, accion.getOrigen());
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public TantoDecorator responderA(Flor accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		// Para hacerlo básico, la flor la queremos siempre (solo querer)
		if (this._mano.hayFlor()) {
			return new FlorQuiero(accion,this,accion.getOrigen());
		}
		
		return new FlorMeAchico(accion,this,accion.getOrigen());
		
	}
*/

	@Override
	public void jugar(Vuelta vuelta) {
		// TODO Auto-generated method stub
	}

}

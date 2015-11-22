package com.modelo.cartas;

public interface IPeleable {

	public Carta ganador(IPeleable peleable);
	
	public Carta ganador(CartaInvalida invalida);
	public Carta ganador(CartaNormal normal);
	public Carta ganador(CartaAnchoFalso anchoFalso);
	public Carta ganador(CartaDos dos);
	public Carta ganador(CartaTres tres);
	public Carta ganador(CartaSieteOro sieteOro);
	public Carta ganador(CartaSieteEspada sieteEspada);
	public Carta ganador(CartaAnchoBasto anchoBasto);
	public Carta ganador(CartaAnchoEspada anchoEspada);
	public Carta ganador(CartaPardaNormal pardaNormal);
	public Carta ganador(CartaPardaAnchoFalso pardaFalso);
	public Carta ganador(CartaPardaDos pardaDos);
	public Carta ganador(CartaPardaTres	pardaTres);
}

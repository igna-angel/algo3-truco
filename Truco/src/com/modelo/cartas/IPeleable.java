package com.modelo.cartas;

public interface IPeleable {

	public boolean ganaA(IPeleable peleable);
	
	public boolean ganaA(CartaNormal normal);
	public boolean ganaA(CartaAnchoFalso anchoFalso);
	public boolean ganaA(CartaDos dos);
	public boolean ganaA(CartaTres tres);
	public boolean ganaA(CartaSieteOro sieteOro);
	public boolean ganaA(CartaSieteEspada sieteOro);
	public boolean ganaA(CartaAnchoBasto anchoBasto);
	public boolean ganaA(CartaAnchoEspada anchoEspada);
}

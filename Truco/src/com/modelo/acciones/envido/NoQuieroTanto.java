package com.modelo.acciones.envido;

public class NoQuieroTanto extends EnvidoDecorator {

	public NoQuieroTanto(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		if (this.accionADecorar.cantar() == 2){
			return this.accionADecorar.cantar() - 1;
		} else if (this.accionADecorar.cantar() == 3){
			return this.accionADecorar.cantar() - 2;
		} else if ((this.accionADecorar.cantar() == 4)){
			return this.accionADecorar.cantar() - 2;
		} else if ((this.accionADecorar.cantar() == 5)){
			return this.accionADecorar.cantar() - 3;
		} else if ((this.accionADecorar.cantar() == 7)){
			return this.accionADecorar.cantar() - 4;
		} else if ((this.accionADecorar.cantar() == 32)){
			return this.accionADecorar.cantar() - 30;
		} else if ((this.accionADecorar.cantar() == 33)){
			return this.accionADecorar.cantar() - 31;
		} else if ((this.accionADecorar.cantar() == 34)){
			return this.accionADecorar.cantar() - 31;
		} else if ((this.accionADecorar.cantar() == 35)){
			return this.accionADecorar.cantar() - 32;
		} else if ((this.accionADecorar.cantar() == 37)){
			return this.accionADecorar.cantar() - 33;
		} else {
			return this.accionADecorar.cantar() - 29;
		}
	}
}

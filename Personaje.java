package Master_Dev_Jorge;

public abstract class Personaje {
	
	//Esta es la clase abstracta de la que heredarán todas las demás. Sus atributos están aquí debajo.
	private int vida;
	private int ataque;
	private int defensa;
	private int velocidad;
	private int vidamax;
	//Este atributo lo usaremos más adelante para la opción "defenderse"
	private boolean defendiendo=false;
	
	//Getters y setters
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getVidamax() {
		return vidamax;
	}
	public void setVidamax(int vidamax) {
		this.vidamax = vidamax;
	}
	public boolean isDefendiendo() {
		return defendiendo;
	}
	public void setDefendiendo(boolean defendiendo) {
		this.defendiendo = defendiendo;
	}
	
	//Método abstracto de salida
	public abstract void Salida();

}

package Master_Dev_Jorge;

public abstract class NPC extends Personaje{
	
	public void Salida() {
		
		System.out.println("Vida: "+getVida()+", Ataque: "+getAtaque()+", Defensa: "+getDefensa());
		
	}
	
}

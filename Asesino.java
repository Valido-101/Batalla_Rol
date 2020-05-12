package Master_Dev_Jorge;

public class Asesino extends NPC{

	private int pcritico;
	
	//Constructor
	public Asesino(int vida,int ataque,int defensa,int velocidad,int pcritico) {

		this.setVida(vida);
		this.setVidamax(vida);
		this.setAtaque(ataque);
		this.setDefensa(defensa);
		this.setVelocidad(velocidad);
		this.pcritico=pcritico;
		
	}

	public int getPcritico() {
		return pcritico;
	}

	public void setPcritico(int pcritico) {
		this.pcritico = pcritico;
	}
	
	//Se realiza la acción que se haya generado aleatoriamente. Tiene al enemigo como parámetro
	public void Movimiento(Enemigo enemigo) {
		
		//El random sólo llega a dos porque el asesino sólo puede golpear normal o con un golpe crítico
		int op=(int)(Math.random()*2+1);
		
		//Este if controla si la vida es 0 o menor para que, si es el caso, no pueda hacer nada por estar muerto
		if(getVida()<=0) {
			
			op=0;
			
		}
		
		//Aquí se realiza una acción u otra dependiendo de lo que salga en el random
		switch (op) {
		
			default:
				//No puede hacer nada porque está muerto
				System.out.println("El asesino no puede hacer nada porque está muerto.");
			break;
		
			case 1: 
					//Ataca con su daño normal y se multiplica la velocidad por 1.5. Muestra los resultados por pantalla
					System.out.println("El asesino ha atacado con éxito. Velocidad multiplicada por 1.5.\n");
					int velocidad_antes=getVelocidad();
					int dannio=getAtaque()-enemigo.getDefensa();
					if(dannio<0) {
						
						dannio=0;
					}
					enemigo.setVida(enemigo.getVida()-dannio);
					setVelocidad((int)(getVelocidad()*1.5));
					System.out.println("Puntos de ataque del asesino: "+getAtaque());
					System.out.println("Puntos de defensa del enemigo: "+enemigo.getDefensa());
					System.out.println("Daño causado: "+dannio);
					System.out.println("\nVelocidad antes: "+velocidad_antes);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nVida restante del enemigo: "+enemigo.getVida()+" HP");
				break;
			case 2:
					//Ataca con el doble de su daño normal y se multiplica la velocidad por 2.2. Muestra los resultados por pantalla
					System.out.println("¡El asesino ha atacado con un golpe crítico! Velocidad multiplicada por 2.2.\n");
					int velocidad_antes_crit=getVelocidad();
					int dannio_critico=(getAtaque()*2)-enemigo.getDefensa();
					if(dannio_critico<0) {
						
						dannio_critico=0;
					}
					enemigo.setVida(enemigo.getVida()-dannio_critico);
					setVelocidad((int)(getVelocidad()*2.2));
					
					System.out.println("Puntos de ataque del asesino: "+getAtaque()*2);
					System.out.println("Puntos de defensa del enemigo: "+enemigo.getDefensa());
					System.out.println("Daño causado: "+dannio_critico);
					System.out.println("\nVelocidad antes: "+velocidad_antes_crit);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nVida restante del enemigo: "+enemigo.getVida()+" HP");
				break;
		}
		
	}

@Override
public void Salida() {
	
	System.out.println("Asesino -> Vida: "+getVida()+", Ataque: "+getAtaque()+", Defensa: "+getDefensa()+", Velocidad: "+getVelocidad()+", Probabilidad de golpe crítico: "+pcritico+"%");
	
}


}

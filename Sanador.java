package Master_Dev_Jorge;

public class Sanador extends NPC{
	
	private int cura;
	
	//Constructor
	public Sanador(int vida,int ataque,int defensa,int velocidad,int cura) {

		this.setVida(vida);
		this.setVidamax(vida);
		this.setAtaque(ataque);
		this.setDefensa(defensa);
		this.setVelocidad(velocidad);
		this.cura=cura;
		
	}
	
	public int getCura() {
		return cura;
	}

	public void setCura(int cura) {
		this.cura = cura;
	}
	
	//Este método es llamado por la acción 3, cura 50 puntos de salud a todo el equipo, asegurándose de que con la curación no superen su máximo de puntos de vida
	public void Curar(Jugador jugador, Asesino asesino) {
		
		jugador.setVida(jugador.getVida()+cura);
		if(jugador.getVida()>jugador.getVidamax()) {
			
			jugador.setVida(jugador.getVidamax());
		}
		
		asesino.setVida(asesino.getVida()+cura);
		if(asesino.getVida()>asesino.getVidamax()) {
			
			asesino.setVida(asesino.getVidamax());
		}
		
		this.setVida(this.getVida()+cura);
		if(this.getVida()>this.getVidamax()) {
			
			this.setVida(this.getVidamax());
		}
	}
	
	//Aquí se genera la acción aleatoriamente, usando como parámetro un enemigo, un jugador y un asesino
	public void Movimiento(Enemigo enemigo,Jugador jugador,Asesino asesino) {
		
		//Este if controla que si está defendiendo se desactive el estado y el boost de defensa al principio dl siguiente turno
		if(isDefendiendo()==true) {
			
			setDefendiendo(false);
			setDefensa((int)(getDefensa()/2));
			
		}
		
		//Con este random se genera la opción
		int op=(int)(Math.random()*3+1);
		
		//Este if controla si la vida es 0 o menor para que, si es el caso, no pueda hacer nada por estar muerto
		if(getVida()<=0) {
			
			op=0;
			
		}
		
		//Dependiendo de la opción se hace una cosa u otra
		switch (op) {
			
			default:
				//No hace nada porque ha muerto
				System.out.println("El sanador no puede hacer nada porque está muerto.");
			break;
		
			case 1: 
					//Ataca al enemigo y duplica la velocidad. El daño se calculará restando la defensa enemiga a los puntos de ataque. Luego se muestran los resultados por pantalla
					System.out.println("El sanador ha atacado con éxito. Velocidad multiplicada por 2.\n");
					int velocidad_antes=getVelocidad();
					int dannio=getAtaque()-enemigo.getDefensa();
					if(dannio<0) {
						
						dannio=0;
					}
					enemigo.setVida(enemigo.getVida()-dannio);
					setVelocidad(getVelocidad()*2);
					System.out.println("\nPuntos de ataque del sanador: "+getAtaque());
					System.out.println("Puntos de defensa del enemigo: "+enemigo.getDefensa());
					System.out.println("Daño causado: "+dannio);
					System.out.println("\nVelocidad antes: "+velocidad_antes);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nVida restante del enemigo: "+enemigo.getVida()+" HP");					
				break;
			case 2:
					//Activa el atributo defendiendose y duplica la defensa por un turno, además de disminuir un cuarto la velocidad durante un turno también. Muestra los cambios en pantalla
					System.out.println("El sanador se está defendiendo. Defensa multiplicada por 2 (1 turno). Velocidad disminuida en 1/4 (1 turno).\n");
					int velocidad_antes_def=getVelocidad();
					int defensa_antes_def=getDefensa();
					setDefensa(getDefensa()*2);
					setDefendiendo(true);
					setVelocidad((int)(getVelocidad()*0.75));
					System.out.println("\nDefensa antes: "+defensa_antes_def);
					System.out.println("Defensa ahora: "+getDefensa());
					System.out.println("\nVelocidad antes: "+velocidad_antes_def);
					System.out.println("Velocidad ahora: "+getVelocidad());
				break;
			case 3:
					//Llama al método curar y restaura 50 hp a todo el equipo. Muestra los resultados por pantalla
					Curar(jugador, asesino);
					System.out.println("¡Curación activada! ¡Todos los aliados recuperan "+cura+" HP!\n");
					System.out.println("Vida del sanador: "+getVida()+" HP");
					System.out.println("Vida del asesino: "+asesino.getVida()+" HP");
					System.out.println("Tu vida: "+jugador.getVida()+" HP");
				break;
		
		}
		
	}
	
	@Override
	public void Salida() {
		
		System.out.println("Sanador -> Vida: "+getVida()+", Ataque: "+getAtaque()+", Defensa: "+getDefensa()+", Velocidad: "+getVelocidad()+", Cura: "+cura+" HP");
		
	}

}

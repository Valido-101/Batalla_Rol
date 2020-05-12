package Master_Dev_Jorge;

public class Enemigo extends Personaje{
	
	//Atributos exclusivos del enemigo
	private int esp;
	private int cont;
	
	//Constructor
	public Enemigo(int vida, int ataque, int defensa,int velocidad, int esp) {
		
		this.setVida(vida);
		this.setVidamax(vida);
		this.setAtaque(ataque);
		this.setDefensa(defensa);
		this.setVelocidad(velocidad);
		this.esp=esp;
		this.cont=0;
		
	}

	public int getEsp() {
		return esp;
	}

	public void setEsp(int esp) {
		this.esp = esp;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}
	
	//Se encarga de la acción, metiendo por parámetro los tres personajes del equipo contrario. Se automatiza la opción mediante un random
	public void Movimiento(Jugador j1,Sanador s1,Asesino a1) {
		
		//Aumenta el contador para que cuando llegue a 3 se haga el especial
		cont++;
		//Opción random
		int op=(int)(Math.random()*2+1);
		//Si el contador es 3, la opción cambia a 3, que es la del especial y el contador vuelve a ser cero
		if(cont==3) {
			
			op=3;
			cont=0;
			
		}
		
		//Este if controla que si está defendiendo se desactive el estado y el boost de defensa al principio dl siguiente turno
		if(isDefendiendo()==true) {
			
			setDefendiendo(false);
			setDefensa((int)(getDefensa()/2));
			
		}
		
		//Este if controla si la vida es 0 o menor para que, si es el caso, no pueda hacer nada por estar muerto
		if(getVida()<=0) {
			
			op=0;
			
		}
		
		//Este switch case se encarga de realizar la acción que haya salido en el random
		switch (op) {
		
			default:
					//Muerte, no puede hacer nada.
					System.out.println("El enemigo no puede hacer nada porque está muerto.");
				break;
				
			//El primer caso hace que ataque	
			case 1: 
					//Este random genera una opción entre uno y tres para elegir al objetivo del ataque. Todos los métodos de ataque tienen el mismo efecto (restan a la vida del objetivo su daño de ataque menos la defensa y aumentan la velocidad por 2)
					int objetivo=(int)(Math.random()*3+1);
					switch (objetivo) {
					
						case 1:
								System.out.println("¡El enemigo te ha atacado! Velocidad multiplicada por 2.\n");
								int velocidad_antes1=getVelocidad();
								int dannio1=getAtaque()-j1.getDefensa();
								if(dannio1<0) {
									
									dannio1=0;
								}
								j1.setVida(j1.getVida()-dannio1);
								setVelocidad(getVelocidad()*2);								
								System.out.println("Puntos de ataque del enemigo: "+getAtaque());
								System.out.println("Tus puntos de defensa: "+j1.getDefensa());
								System.out.println("Daño causado: "+dannio1);
								System.out.println("\nVelocidad antes: "+velocidad_antes1);
								System.out.println("Velocidad ahora: "+getVelocidad());
								System.out.println("\nVida restante del Jugador: "+j1.getVida()+" HP");
							break;
						case 2:
								System.out.println("¡El enemigo ha atacado al sanador! Velocidad multiplicada por 2.\n");
								int velocidad_antes2=getVelocidad();
								int dannio2=getAtaque()-s1.getDefensa();
								if(dannio2<0) {
									
									dannio2=0;
								}
								s1.setVida(s1.getVida()-dannio2);
								setVelocidad(getVelocidad()*2);
								System.out.println("Puntos de ataque del enemigo: "+getAtaque());
								System.out.println("Puntos de defensa del sanador: "+s1.getDefensa());
								System.out.println("Daño causado: "+dannio2);
								System.out.println("\nVelocidad antes: "+velocidad_antes2);
								System.out.println("Velocidad ahora: "+getVelocidad());
								System.out.println("\nVida restante del Sanador: "+s1.getVida()+" HP");
							break;
						case 3:
								System.out.println("¡El enemigo ha atacado al asesino! Velocidad multiplicada por 2.\n");	
								int velocidad_antes3=getVelocidad();
								int dannio3=getAtaque()-a1.getDefensa();
								if(dannio3<0) {
									
									dannio3=0;
								}
								a1.setVida(a1.getVida()-dannio3);
								setVelocidad(getVelocidad()*2);
								
								System.out.println("Puntos de ataque del enemigo: "+getAtaque());
								System.out.println("Puntos de defensa del asesino: "+a1.getDefensa());
								System.out.println("Daño causado: "+dannio3);
								System.out.println("\nVelocidad antes: "+velocidad_antes3);
								System.out.println("Velocidad ahora: "+getVelocidad());
								System.out.println("\nVida restante del Asesino: "+a1.getVida()+" HP");
							break;
					}
					
				break;
			//El segundo caso hace que se defienda. Dobla la defensa y baja la velocidad a la mitad. Muestra los resultados al acabar.
			case 2:
					int velocidad_antes_def=getVelocidad();
					int defensa_antes_def=getDefensa();
					setDefensa(getDefensa()*2);
					setDefendiendo(true);
					setVelocidad((int)(getVelocidad()*0.5));
					System.out.println("El enemigo se está defendiendo. Defensa multiplicada por 2 (1 turno). Velocidad reducida a la mitad (1 turno).");
					System.out.println("\nDefensa antes: "+defensa_antes_def);
					System.out.println("Defensa ahora: "+getDefensa());
					System.out.println("\nVelocidad antes: "+velocidad_antes_def);
					System.out.println("Velocidad ahora: "+getVelocidad());
				break;
			//Realiza el especial al pasar tres turnos. Hace daño a todos los objetivos y multiplica su velocidad por 4
			case 3:
					System.out.println("¡Cuidado!¡Ataque especial activado! Daño a todo el equipo contrario y velocidad multiplicada por 4.\n");
					int velocidad_antes_esp=getVelocidad();
					int dannio_esp_1=esp-j1.getDefensa();
					int dannio_esp_2=esp-s1.getDefensa();
					int dannio_esp_3=esp-a1.getDefensa();
					if(dannio_esp_1<0) {
						
						dannio_esp_1=0;
					}
					if(dannio_esp_2<0) {
						
						dannio_esp_2=0;
					}
					if(dannio_esp_3<0) {
						
						dannio_esp_3=0;
					}
					j1.setVida(j1.getVida()-dannio_esp_1);
					s1.setVida(s1.getVida()-dannio_esp_2);
					a1.setVida(a1.getVida()-dannio_esp_3);
					setVelocidad(getVelocidad()*4);
					System.out.println("\nVelocidad antes: "+velocidad_antes_esp);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nDaño especial del enemigo: "+esp);
					System.out.println("Defensa del Jugador: "+j1.getDefensa());
					System.out.println("Daño total Jugador: "+dannio_esp_1);
					System.out.println("\nVida restante del Jugador: "+j1.getVida()+" HP");
					System.out.println("\nDaño especial del enemigo: "+esp);
					System.out.println("Defensa del Sanador: "+s1.getDefensa());
					System.out.println("Daño total Sanador: "+dannio_esp_2);
					System.out.println("\nVida restante del Sanador: "+s1.getVida()+" HP");
					System.out.println("\nDaño especial del enemigo: "+esp);
					System.out.println("Defensa del Asesino: "+a1.getDefensa());
					System.out.println("Daño total Asesino: "+dannio_esp_3);
					System.out.println("\nVida restante del Asesino: "+a1.getVida()+" HP");
				break;
		
		}
		
	}
	
	@Override
	public void Salida() {
		
		System.out.println("Enemigo -> Vida: "+getVida()+", Ataque: "+getAtaque()+", Defensa: "+getDefensa()+", Velocidad: "+getVelocidad()+", Daño Especial: "+esp+", Turnos hasta Ataque Especial: "+(3-(cont+1)));
		
	}
}

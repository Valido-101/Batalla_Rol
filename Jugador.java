package Master_Dev_Jorge;

public class Jugador extends Personaje{
	
	//Atributos de la clase
	private int cont_esp=0;
	private int esp;
	private boolean esp_activado=false;
	
	//Constructor
	public Jugador(int vida, int ataque, int defensa,int velocidad, int esp) {
		
		this.setVida(vida);
		this.setVidamax(vida);
		this.setAtaque(ataque);
		this.setDefensa(defensa);
		this.setVelocidad(velocidad);
		this.esp=esp;
		
	}
	
	//Getters y setters
	public int getCont_esp() {
		return cont_esp;
	}

	public void setCont_esp(int cont_esp) {
		this.cont_esp = cont_esp;
	}

	public int getEsp() {
		return esp;
	}

	public void setEsp(int esp) {
		this.esp = esp;
	}

	public boolean isEsp_activado() {
		return esp_activado;
	}

	public void setEsp_activado(boolean esp_activado) {
		this.esp_activado = esp_activado;
	}

	//Este método se encarga de realizar una cosa u otra en función de la opción que elija el usuario. Dicha opción y el enemigo al que se le aplicará se meten por parámetro.
	public void Movimiento(int op, Enemigo enemigo) {
		
		//Este if controla si la vida es 0 o menor para que, si es el caso, no pueda hacer nada por estar muerto
		if(getVida()<=0) {
			
			op=0;
			
		}
		
		//Este if se encarga de que se disminuya el contador del especial si está activado con cada turno y, cuando llegue a cero, desactive el estado de ataque especial, devolviendo la defensa y la velocidad a la normalidad
		if(esp_activado==true) {
			
			cont_esp--;
			if(cont_esp==0) {
				
				esp_activado=false;
				setVelocidad((int)(getVelocidad()/5));
				setDefensa((int)(getDefensa()/2));
				
			}
			
		}
		
		//Este if controla que si está defendiendo se desactive el estado y el boost de defensa al principio dl siguiente turno
		if(isDefendiendo()==true) {
			
			setDefendiendo(false);
			setDefensa((int)(getDefensa()/2));
			
		}
		
		//Este switch case se encarga de realizar la acción que elige el usuario
		switch (op) {
		
			default:
					//Muerte, no puede hacer nada.
					System.out.println("No puedes hacer nada. Estás muerto.");
				break;
		
			case 1: 
					//Ataca al enemigo y duplica la velocidad. El daño se calculará restando la defensa enemiga a los puntos de ataque. Luego se muestran los resultados por pantalla
					int velocidad_antes=getVelocidad();
					System.out.println("\nHas atacado con éxito. Velocidad multiplicada por 2.\n");
					int dannio=getAtaque()-enemigo.getDefensa();
					if(dannio<0) {
						
						dannio=0;
					}
					enemigo.setVida(enemigo.getVida()-dannio);
					setVelocidad(getVelocidad()*2);
					System.out.println("Tus puntos de ataque: "+getAtaque());
					System.out.println("Puntos de defensa del enemigo: "+enemigo.getDefensa());
					System.out.println("Daño causado: "+dannio);
					System.out.println("\nVelocidad antes: "+velocidad_antes);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nVida restante del enemigo: "+enemigo.getVida()+" HP");
				break;
			case 2:
					//Activa el atributo defendiendose y duplica la defensa por un turno, además de disminuir un cuarto la velocidad durante un turno también. Muestra los cambios en pantalla
					int velocidad_antes_def=getVelocidad();
					int defensa_antes_def=getDefensa();
					if(esp_activado==false) {
						
						System.out.println("Te estás defendiendo. Defensa multiplicada por 2 (1 turno). Velocidad disminuida 1/4 (1 turno).");
					
					}else {
						
						System.out.println("Te estás defendiendo. Defensa multiplicada por 2 (1 turno).");	
					
					}
					setDefensa(getDefensa()*2);
					setDefendiendo(true);
					if(esp_activado=false) {
						setVelocidad((int)(getVelocidad()*0.75));
					}
					System.out.println("\nDefensa antes: "+defensa_antes_def);
					System.out.println("Defensa ahora: "+getDefensa());
					if(esp_activado==false) {
						
						System.out.println("\nVelocidad antes: "+velocidad_antes_def);
						System.out.println("Velocidad ahora: "+getVelocidad());
					
					}
				break;
			case 3:
					//Ataque especial. Daña al enemigo con los puntos de ataque especial y multiplica la velocidad por 5 y la defensa por dos durante tres turnos. Mientras esté activo el especial, al defenderse no se reducirá la velocidad
					cont_esp=3;
					esp_activado=true;
					int velocidad_antes_esp=getVelocidad();
					int defensa_antes_esp=getDefensa();
					int dannio_esp=getEsp()-enemigo.getDefensa();
					if(dannio_esp<0) {
						
						dannio_esp=0;
					}
					enemigo.setVida(enemigo.getVida()-dannio_esp);
					setVida(getVida()+dannio_esp);
					if(getVida()>getVidamax()) {
						
						setVida(getVidamax());
					}
					setVelocidad(getVelocidad()*5);
					setDefensa(getDefensa()*2);
					System.out.println("¡Ataque especial activado! Boost Velocidad por 5 (3 turnos) y Defensa por 2 (3 turnos). Mientras el especial esté activo (3 turnos), defenderte no reducirá la velocidad.\n");
					System.out.println("Tus puntos de ataque especial: "+esp);
					System.out.println("Puntos de defensa del enemigo: "+enemigo.getDefensa());
					System.out.println("Daño causado: "+dannio_esp);
					System.out.println("\nVelocidad antes: "+velocidad_antes_esp);
					System.out.println("Velocidad ahora: "+getVelocidad());
					System.out.println("\nDefensa antes: "+defensa_antes_esp);
					System.out.println("Defensa ahora: "+getDefensa());
					System.out.println("\nVida restante del enemigo: "+enemigo.getVida()+" HP");
				break;
		
		}
		
	}
	//Método de salida
	@Override
	public void Salida() {
		
		System.out.println("Jugador -> Vida: "+getVida()+", Ataque: "+getAtaque()+", Defensa: "+getDefensa()+", Velocidad: "+getVelocidad()+", Daño Especial: "+esp);
		
	}
	
}

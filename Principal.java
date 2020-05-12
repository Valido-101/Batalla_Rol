package Master_Dev_Jorge;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		//Variable que hace que se repita infinitamente el bucle de abajo
		boolean fin_partida=false;
		//Donde introduce el usuario su elecci�n
		int opcion;
		//Un contador de los turnos
		int turnos=1;
		//String que utilizo solamente para parar el programa despu�s de cada acci�n
		String tecla_espera;
		//Dos teclados: uno para la opci�n del usuario y otro para la espera del programa
		Scanner teclado=new Scanner(System.in);
		Scanner espera=new Scanner(System.in);
		
		//Array de personajes donde guardo a cada subclase ordenadas por velocidad
		Personaje[] orden_personajes= new Personaje[4];
		
		//Array de int para guardar las velocidades de cada personaje y poder ordenarlas
		int[] velocidades=new int[4];
		
		//Instanciaci�n de cada clase que participar�
		Jugador j1=new Jugador(100,50,30,40,50);
		Sanador s1=new Sanador(150,30,40,30,50);
		Asesino a1=new Asesino(90,60,25,60,50);
		Enemigo e1=new Enemigo(500,60,40,70,50);
		
		//Inicio del bucle que se repetir� hasta el fin de la partida
		do {
			
		//Muestra el turno actual
		System.out.println("Turno "+turnos+"\n\n");
		turnos++;
		
		//Introduce las velocidades en el array de int
		velocidades[0]=j1.getVelocidad();
		velocidades[1]=s1.getVelocidad();
		velocidades[2]=a1.getVelocidad();
		velocidades[3]=e1.getVelocidad();
		
		//Ordena el array de velocidades
		Arrays.sort(velocidades);
		
		//Introduce a los personajes en el array de personajes ordenados por velocidad de mayor a menor (el Arrays.sort() los ordena de menor a mayor, as� que los he introducido en el array de personajes al rev�s)
		for(int x=0;x<orden_personajes.length;x++) {
			
			if(j1.getVelocidad()==velocidades[x]) {
				
				orden_personajes[(orden_personajes.length-1)-x]=j1;
				
			}else {
				
				if(s1.getVelocidad()==velocidades[x]) {
					
					orden_personajes[(orden_personajes.length-1)-x]=s1;
					
				}else {
					
					if(a1.getVelocidad()==velocidades[x]) {
						
						orden_personajes[(orden_personajes.length-1)-x]=a1;
						
					}else {
						
						if(e1.getVelocidad()==velocidades[x]) {
							
							orden_personajes[(orden_personajes.length-1)-x]=e1;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		//Saca por pantalla el orden que se seguir� (ya se ha ordenado el array) y sacar� los stats de cada personaje repetando dicho orden.
		System.out.println("Orden de jugada y stats:\n");
		
		for(int x=0;x<orden_personajes.length;x++) {
			
			orden_personajes[x].Salida();
			
		}
		//Esto es lo que hace que se pare la partida y sea m�s f�cil seguir el hilo
		System.out.print("\nPulse Enter para continuar...");
		tecla_espera=espera.nextLine();
		
		//Deja un margen para que se vea m�s limpio y ordenado
		System.out.println("\n\n\n");
		
		//LLega el momento de la verdad. Realiza la acci�n de cada personaje respetando el orden del array de personajes.
		for(int x=0;x<orden_personajes.length;x++) {
			
			//Si el primero es de la clase Jugador, se ejecuta esta parte del c�digo
			if(orden_personajes[x].getClass()==j1.getClass()) {
				
				//Se crea un bucle por si se intenta hacer el especial sin cumplir las condiciones
				do {
					//Este if avisa al jugador si se acerca el especial del enemigo
					if(e1.getCont()==2) {
						
						System.out.println("�Atenci�n! El enemigo va a hacer su ataque especial en su pr�ximo turno.\n");
						
					}
					
					//Este if informa de cu�ndo est� activado el especial
					if(j1.isEsp_activado()==true) {
						
						System.out.println("Ataque especial: Activado\n");
						
					}
					
					//Si se cumplen las condiciones para poder realizar el especial te informar� de ello junto con un porcentaje de la vida actual
					if(j1.getVida()<=j1.getVidamax()*0.75 && j1.isEsp_activado()==false) {
						
						System.out.println("�Puedes activar el Ataque Especial! (Salud: "+(j1.getVida()*100/j1.getVidamax())+"%)\n");
						
					}
					//Este if sirve para que junto con el men� te diga si el enemigo est� defendi�ndose, por si te hace cambiar de opini�n.
					if(e1.isDefendiendo()==true) {
						//Si se cumple la condici�n del especial, te muestra la opci�n para que sepas qu� bot�n es				
						if(j1.getVida()<=j1.getVidamax()*0.75) {
							
							System.out.print("�Tu turno! Elige una opci�n:\n\n1: Atacar (Nota: El enemigo se est� defendiendo)\n2: Defender\n3: Ataque Especial\n>");
							
						}else {
							
							System.out.print("�Tu turno! Elige una opci�n:\n\n1: Atacar (Nota: El enemigo se est� defendiendo)\n2: Defender\n>");
							
						}
					//Men� de opciones normal si el enemigo no se est� defendiendo.
					}else {
						//Si se cumple la condici�n del especial, te muestra la opci�n para que sepas qu� bot�n es
						if(j1.getVida()<=j1.getVidamax()*0.75) {
							
							System.out.print("�Tu turno! Elige una opci�n:\n\n1: Atacar\n2: Defender\n3: Ataque Especial\n>");
							
						}else {
							
							System.out.print("�Tu turno! Elige una opci�n:\n\n1: Atacar\n2: Defender\n>");
							
						}
						
					}
					//Introduce la opci�n del jugador en la variable
					opcion=teclado.nextInt();
					//Si la opci�n no es tres o lo es y se cumplen las condiciones del especial se rompe el bucle e invoca al m�todo de las acciones del jugador
					if(opcion!=3 || opcion==3 && j1.getVida()<=j1.getVidamax()*0.75 && j1.isEsp_activado()==false) {
						break;
					}
					else {
						//Si la opci�n es tres y el especial ya est� activado no se romple el bucle y te informa de ello
						if(opcion==3 && j1.isEsp_activado()==true) {
							
							System.out.println("El ataque especial ya ha sido activado. Elige otra opci�n.\n");
							
						}
						
					}
					//Si la opci�n es tres y no se cumplen las condiciones del especial no se rompe el bucle y te informa
					if(opcion==3 && j1.getVida()>j1.getVidamax()*0.75) {
						
						System.out.println("No puedes usar el especial a�n (Tu salud debe estar por debajo del 75%).\n");
						
					}
					}while(opcion==3 && j1.getVida()>j1.getVidamax()*0.75 || opcion==3 && j1.getVida()<=j1.getVidamax()*0.75 && j1.isEsp_activado()==true);
					
					//M�todo qu realiza la acci�n del jugador
					j1.Movimiento(opcion, e1);
					
					//Si el jugador hace que la vida del enemigo llegue a cero o menos, se llama al m�todo Game_Over() y acaba la partida cerrando el programa
					if(e1.getVida()<=0) {
						
						Game_Over("Jugador");
					}
					
					//Esto es lo que hace que se pare la partida y sea m�s f�cil seguir el hilo
					System.out.print("\nPulse Enter para continuar...");
					tecla_espera=espera.nextLine();
					
				}else {
					
					//Si el primero es de la clase Sanador, se ejecuta esta parte del c�digo
					if(orden_personajes[x].getClass()==s1.getClass()) {
						
						System.out.println("Turno del Sanador:\n");
						
						//LLama al m�todo que decide la acci�n del sanador introduciendo por par�metro al enemigo, al jugador y al asesino
						s1.Movimiento(e1, j1, a1);
						
						//Si el sanador hace que la vida del enemigo llegue a cero o menos, se llama al m�todo Game_Over() y acaba la partida cerrando el programa
						if(e1.getVida()<=0) {
							
							Game_Over("Sanador");
							
						}
						
						//Esto es lo que hace que se pare la partida y sea m�s f�cil seguir el hilo
						System.out.print("\nPulse Enter para continuar...");
						tecla_espera=espera.nextLine();
						
					}else {
						
						//Si el primero es de la clase Asesino, se ejecuta esta parte del c�digo
						if(orden_personajes[x].getClass()==a1.getClass()) {
							
							System.out.println("Turno del Asesino:\n");
							
							//LLama al m�todo que decide la acci�n del asesino introduciendo por par�metro al enemigo
							a1.Movimiento(e1);
							
							//Si el asesino hace que la vida del enemigo llegue a cero o menos, se llama al m�todo Game_Over() y acaba la partida cerrando el programa
							if(e1.getVida()<=0) {
								
								Game_Over("Asesino");
								
							}
							
							//Esto es lo que hace que se pare la partida y sea m�s f�cil seguir el hilo
							System.out.print("\nPulse Enter para continuar...");
							tecla_espera=espera.nextLine();
							
						}else {
							
							//Si el primero es de la clase Enemigo, se ejecuta esta parte del c�digo
							if(orden_personajes[x].getClass()==e1.getClass()) {
								
								System.out.println("Turno del Enemigo:\n");
								
								//LLama al m�todo que decide la acci�n del enemigo introduciendo por par�metro al jugador, al asesino y al sanador
								e1.Movimiento(j1, s1, a1);
								
								//Si el enemigo hace que las vidas del asesino, el jugador y el sanador lleguen a cero o menos, se llama al m�todo Game_Over() y acaba la partida cerrando el programa
								if(j1.getVida()<=0 && s1.getVida()<=0 && a1.getVida()<=0) {
									
									Game_Over("Enemigo");
									
								}
								
								//Esto es lo que hace que se pare la partida y sea m�s f�cil seguir el hilo
								System.out.print("\nPulse Enter para continuar...");
								tecla_espera=espera.nextLine();
								
							}
							
						}
						
					}
					
				}
				
				//Deja un margen para que se vea m�s limpio y ordenado
				System.out.println("\n\n");
			}
		

		//Bucle infinito
		}while(fin_partida==false);
		
		teclado.close();
		espera.close();
		
	}
	
	//Este es el m�todo que cierra el programa y te muestra por pantalla qui�n ha ganado o en el caso del equipo del jugador, qui�n ha dado el golpe final
	public static void Game_Over(String ganador) {
		
		System.out.println("\n�Fin de la partida!");
		
		if(ganador.equalsIgnoreCase("Enemigo")) {
			
			System.out.println("\n�Has perdido!");
			
		}else {
			
			System.out.println("\n�Victoria! El "+ganador+" ha asestado el �ltimo golpe.");
			
		}
		System.exit(0);
		
	}
		
}

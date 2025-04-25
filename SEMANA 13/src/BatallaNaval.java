import java.util.Scanner;
import java.util.Random;

public class BatallaNaval {
    
    public static void main(String[] args) {
        // Declarar los arreglos para los barcos
        int[] barcosJugador = new int[20];
        int[] barcosComputadora = new int[20];
        
        // Variables para contar barcos hundidos
        int barcosHundidosJugador = 0;
        int barcosHundidosComputadora = 0;
        
        // Variable para controlar el tiempo
        long tiempoInicio = System.currentTimeMillis();
        long tiempoLimite = 3 * 60 * 1000; // 3 minutos en milisegundos
        
        // Crear scanner para leer entrada del usuario
        Scanner entrada = new Scanner(System.in);
        
        // Crear objeto para generar números aleatorios
        Random aleatorio = new Random();
        
        // Poner barcos para el jugador
        colocarBarcos(barcosJugador);
        
        // Poner barcos para la computadora
        colocarBarcos(barcosComputadora);
        
        // Mostrar mensaje de bienvenida
        System.out.println("--- Batalla Naval ---");
        System.out.println("Tiempo para ganar - 3 minutos");
        
        // Mostrar el tablero del jugador
        System.out.println("\nTu tablero:");
        mostrarTablero(barcosJugador);
        
        // Bucle principal del juego
        boolean juegoTerminado = false;
        
        while (!juegoTerminado) {
            // Verificar si se acabó el tiempo
            long tiempoActual = System.currentTimeMillis();
            long tiempoTranscurrido = tiempoActual - tiempoInicio;
            
            if (tiempoTranscurrido >= tiempoLimite) {
                System.out.println("\n - Se acabó el tiempo - Perdiste.");
                juegoTerminado = true;
                break;
            }
            
            // Calcular tiempo restante
            long segundosRestantes = (tiempoLimite - tiempoTranscurrido) / 1000;
            System.out.println("\nTiempo restante: " + segundosRestantes + " segundos");
            
            // Turno del jugador
            System.out.print("\n¿Dónde quieres disparar? (0-19): ");
            int posicionDisparo = entrada.nextInt();
            
            // Verificar si el disparo es válido
            if (posicionDisparo < 0 || posicionDisparo > 19) {
                System.out.println("Posición inválida. Debe ser entre 0 y 19.");
                continue;
            }
            
            // Verificar si le dio a un barco
            if (barcosComputadora[posicionDisparo] > 0) {
                System.out.println("¡Le diste al barco " + barcosComputadora[posicionDisparo] + "!");
                barcosHundidosComputadora++;
                barcosComputadora[posicionDisparo] = 0;
            } else {
                System.out.println("No le diste a ningún barco.");
            }
            
            // Verificar si el jugador ganó
            if (barcosHundidosComputadora == 5) {
                System.out.println("\nHundiste todos los barcos de la computadora.");
                juegoTerminado = true;
                break;
            }
            
            // Turno de la computadora
            int disparoComputadora = aleatorio.nextInt(20);
            System.out.println("\nLa computadora dispara a la posición: " + disparoComputadora);
            
            // Verificar si le dio a un barco del jugador
            if (barcosJugador[disparoComputadora] > 0) {
                System.out.println("computadora hundió tu barco " + barcosJugador[disparoComputadora] );
                barcosHundidosJugador++;
                barcosJugador[disparoComputadora] = 0;
            } else {
                System.out.println("La computadora falló.");
            }
            
            // Verificar si la computadora ganó
            if (barcosHundidosJugador == 5) {
                System.out.println("\nLa computadora hundió todos tus barcos. Perdiste");
                juegoTerminado = true;
                break;
            }
            
            // Mostrar tableros actualizados
            System.out.println("\nTu tablero:");
            mostrarTablero(barcosJugador);
            
            // Para propósitos de prueba, mostrar también el tablero de la computadora
            System.out.println("\nTablero de la computadora:");
            mostrarTablero(barcosComputadora);
        }
        
        // Cerrar el scanner
        entrada.close();
    }
    
    // Función para colocar los barcos en el tablero
    public static void colocarBarcos(int[] tablero) {
        Random aleatorio = new Random();
        
        for (int i = 1; i <= 5; i++) {
            boolean barcoColocado = false;
            
            while (!barcoColocado) {
                int posicion = aleatorio.nextInt(20);
                
                if (tablero[posicion] == 0) {
                    tablero[posicion] = i;
                    barcoColocado = true;
                }
            }
        }
    }
    
    // Función para mostrar el tablero
    public static void mostrarTablero(int[] tablero) {
        for (int i = 0; i < 20; i++) {
            System.out.print(tablero[i] + " ");
        }
        System.out.println();
    }
}
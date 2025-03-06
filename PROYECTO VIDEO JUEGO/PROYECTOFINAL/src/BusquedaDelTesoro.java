import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;

public class BusquedaDelTesoro {
    
    // Variables globales
    private static String ubicacionActual;
    private static String[] inventario = new String[3];
    private static int itemsEnInventario = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static final String RUTA_MAPA = "src/main/resources/mapa.jpg"; // Ajusta esta ruta según donde guardes la imagen
    
    
    
    private static void abrirMapa() {
        try {
            File archivoMapa = new File(RUTA_MAPA);
            if (archivoMapa.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivoMapa);
                    System.out.println("Abriendo el mapa de la isla...");
                } 
            } 
        } catch (IOException e) {
            System.out.println("Error al intentar abrir el mapa: ");
        }
    }
    
    private static void limpiarPantalla() {
        
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

    }
    
    private static void iniciarJuego() {
        limpiarPantalla();
        System.out.println("=== EL TESORO DEL CAPITÁN ARVID ===");
        System.out.println("Eres Finn, nieto de un legendario capitán pirata. Has encontrado un viejo diario");
        System.out.println("con pistas crípticas que te han llevado a esta misteriosa isla del Caribe.");
        System.out.println("Tu misión es encontrar el tesoro escondido de tu abuelo.");
        System.out.println("----------------------------------------");
        System.out.println("Presiona 'M' en cualquier momento para ver el mapa de la isla.");
        
        ubicacionActual = "playa";
        
        System.out.println("\nPresiona Enter para comenzar la aventura...");
        scanner.nextLine();
        limpiarPantalla();
    }
    
    private static void mostrarDescripcionUbicacion() {
        System.out.println("\n--- " + ubicacionActual.toUpperCase() + " ---");
        
        switch(ubicacionActual) {
            case "playa":
                System.out.println("Estás en una playa de arena blanca. Las olas golpean suavemente la orilla.");
                System.out.println("Ves un cofre viejo semienterrado en la arena.");
                System.out.println("Al norte se extiende una densa jungla.");
                break;
            case "jungla":
                System.out.println("Te encuentras en medio de una espesa jungla tropical.");
                System.out.println("Los sonidos de animales exóticos te rodean por todas partes.");
                System.out.println("Ves un camino hacia el este que lleva a unas ruinas, otro hacia el oeste");
                System.out.println("que parece dirigirse a una montaña, y al sur está la playa.");
                break;
            case "ruinas":
                System.out.println("Estás entre los restos de lo que parece ser un antiguo asentamiento.");
                System.out.println("Columnas derruidas y paredes a medio derrumbar te rodean.");
                System.out.println("Puedes ver un sendero hacia el norte que conduce a una cueva oscura,");
                System.out.println("y otro hacia el oeste que regresa a la jungla.");
                break;
            case "montaña":
                System.out.println("Has ascendido a una montaña con vistas panorámicas de toda la isla.");
                System.out.println("El aire es más fresco aquí arriba y puedes ver el mar en todas direcciones.");
                System.out.println("Hay un camino hacia el este que regresa a la jungla y");
                System.out.println("otro estrecho sendero hacia el noreste que parece llevar a una cueva.");
                break;
            case "cueva":
                System.out.println("Te encuentras en una cueva oscura y húmeda. Gotas de agua caen del techo.");
                System.out.println("Extrañas marcas y símbolos están tallados en las paredes.");
                System.out.println("Hay salidas hacia el sur que llevan a las ruinas y");
                System.out.println("hacia el suroeste que conducen a la montaña.");
                break;
        }
    }
    
    private static int pedirOpcion() {
        System.out.println("\n¿Qué deseas hacer?");
        
        if (ubicacionActual.equals("playa")) {
            System.out.println("1. Examinar cofre");
            System.out.println("2. Ir al norte (Jungla)");
        } else if (ubicacionActual.equals("jungla")) {
            System.out.println("1. Examinar alrededores");
            System.out.println("2. Ir al este (Ruinas)");
            System.out.println("3. Ir al oeste (Montaña)");
            System.out.println("4. Ir al sur (Playa)");
        } else if (ubicacionActual.equals("ruinas")) {
            System.out.println("1. Examinar ruinas");
            System.out.println("2. Ir al norte (Cueva)");
            System.out.println("3. Ir al oeste (Jungla)");
        } else if (ubicacionActual.equals("montaña")) {
            System.out.println("1. Examinar cima");
            System.out.println("2. Ir al este (Jungla)");
            System.out.println("3. Ir al noreste (Cueva)");
        } else if (ubicacionActual.equals("cueva")) {
            System.out.println("1. Examinar marcas en la pared");
            System.out.println("2. Ir al sur (Ruinas)");
            System.out.println("3. Ir al suroeste (Montaña)");
        }
        
        System.out.println("0. Ver inventario");
        System.out.println("8. Ver mapa de la isla");
        System.out.println("9. Salir del juego");
        
        System.out.print("Elige una opción: ");

        
        while (!scanner.hasNextInt()) {
            String entrada = scanner.nextLine().toUpperCase();
            if (entrada.equals("M")) {
                abrirMapa();
                System.out.print("\nElige una opción: ");
            } else {
                System.out.println("Por favor, ingresa un número válido o 'M' para ver el mapa.");
                System.out.print("Elige una opción: ");
            }
        }
        return scanner.nextInt();
    } 
    
    private static boolean procesarOpcion(int opcion) {
        
        //Opcion que termina el bucle
        if (opcion == 9) {
            System.out.println("¿Estás seguro que deseas salir del juego? (S/N)");
            scanner.nextLine(); 
            String confirmacion = scanner.nextLine().toUpperCase();
            if (confirmacion.startsWith("S")) {
                return false; 
            } else {
                return true;                                                                                                                                                                                                                                                                                                                                                                                                                                  
            }
        }
        
        if (opcion == 0) {
            mostrarInventario();
            return true;
        }
        
        if (opcion == 8) {
            abrirMapa();
            return true;
        }
        
        // Lógica según ubicación actual
        if (ubicacionActual.equals("playa")) {
            switch(opcion) {
                case 1:
                    System.out.println("Examinas el cofre. Está cerrado con un candado oxidado.");
                    System.out.println("Necesitarás algo para abrirlo.");
                    break;
                case 2:
                    ubicacionActual = "jungla";
                    System.out.println("Te adentras en la jungla...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("jungla")) {
            switch(opcion) {
                case 1:
                    System.out.println("Al examinar los alrededores, encuentras una pala vieja entre la vegetación.");
                    if (itemsEnInventario < 3) {
                        agregarAlInventario("Pala");
                    } else {
                        System.out.println("Tu inventario está lleno. No puedes llevar más objetos.");
                    }
                    break;
                case 2:
                    ubicacionActual = "ruinas";
                    System.out.println("Te diriges hacia las ruinas...");
                    break;
                case 3:
                    ubicacionActual = "montaña";
                    System.out.println("Comienzas a subir la montaña...");
                    break;
                case 4:
                    ubicacionActual = "playa";
                    System.out.println("Regresas a la playa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("ruinas")) {
            switch(opcion) {
                case 1:
                    System.out.println("Examinas detenidamente las ruinas. Entre los escombros encuentras una antorcha.");
                    if (itemsEnInventario < 3) {
                        agregarAlInventario("Antorcha");
                    } else {
                        System.out.println("Tu inventario está lleno. No puedes llevar más objetos.");
                    }
                    break;
                case 2:
                    ubicacionActual = "cueva";
                    System.out.println("Te adentras en la oscura cueva...");
                    break;
                case 3:
                    ubicacionActual = "jungla";
                    System.out.println("Regresas a la jungla...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("montaña")) {
            switch(opcion) {
                case 1:
                    System.out.println("Desde la cima puedes ver toda la isla. Notas un extraño patrón en la vegetación,");
                    System.out.println("como si formara una flecha apuntando hacia la cueva.");
                    break;
                case 2:
                    ubicacionActual = "jungla";
                    System.out.println("Desciendes hacia la jungla...");
                    break;
                case 3:
                    ubicacionActual = "cueva";
                    System.out.println("Sigues el estrecho sendero hacia la cueva...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("cueva")) {
            switch(opcion) {
                case 1:
                    System.out.println("Examinas las marcas en la pared. Parecen ser un mapa de la isla con una X marcada.");
                    System.out.println("También encuentras una cuerda abandonada en un rincón.");
                    if (itemsEnInventario < 3) {
                        agregarAlInventario("Cuerda");
                    } else {
                        System.out.println("Tu inventario está lleno. No puedes llevar más objetos.");
                    }
                    break;
                case 2:
                    ubicacionActual = "ruinas";
                    System.out.println("Sales de la cueva hacia las ruinas...");
                    break;
                case 3:
                    ubicacionActual = "montaña";
                    System.out.println("Tomas el sendero que lleva a la montaña...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        
        return true;
    }
    
    private static void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ---");
        if (itemsEnInventario == 0) {
            System.out.println("Tu inventario está vacío.");
        } else {
            for (int i = 0; i < itemsEnInventario; i++) {
                System.out.println((i+1) + ". " + inventario[i]);
            }
        }
        System.out.println("Capacidad: " + itemsEnInventario + "/3");
    }
    
    private static void agregarAlInventario(String objeto) {
        if (itemsEnInventario < 3) {
            // Verificar si el objeto ya está en el inventario
            boolean yaExiste = false;
            for (int i = 0; i < itemsEnInventario; i++) {
                if (inventario[i].equals(objeto)) {
                    yaExiste = true;
                    break;
                }
            }
            
            if (yaExiste) {
                System.out.println("Ya tienes " + objeto + " en tu inventario.");
            } else {
                inventario[itemsEnInventario] = objeto;
                itemsEnInventario++;
                System.out.println("¡Has añadido " + objeto + " a tu inventario!");
            }
        } else {
            System.out.println("Tu inventario está lleno. No puedes llevar más objetos.");
        }
    }

    public static void main(String[] args) {
        iniciarJuego();
        
        boolean juegoEnCurso = true;
        while (juegoEnCurso) {
            mostrarDescripcionUbicacion();
            int opcion = pedirOpcion();
            juegoEnCurso = procesarOpcion(opcion);

            if (juegoEnCurso) {
                System.out.println("\nPresiona Enter para continuar...");
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                scanner.nextLine(); // Esperar a que el usuario presione Enter
                limpiarPantalla();
            }
        }
        
        scanner.close();
        System.out.println("Proyecto video juego, fundamentos de programacion");
    }
}
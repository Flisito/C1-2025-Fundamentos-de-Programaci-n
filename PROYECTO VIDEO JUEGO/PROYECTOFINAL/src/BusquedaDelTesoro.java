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
    private static final String RUTA_MAPA = "C:\\src\\resources\\mapa.jpg"; // Ajustar esta ruta según donde se guarde la imagen

    // Variables globales adicionales para combate
    private static int vidaJugador = 100;
    private static boolean tesoroEncontrado = false;
    private static boolean guardiánDerrotado = false;
    private static boolean trampaActivada = false;
    private static String nombreGuardian;
    private static int vidaGuardian;
    private static int fuerzaGuardian;
    private static String ataqueEspecial;
    private static String debilidadGuardian;
    
    private static void abrirMapa() {
        try {
            File archivoMapa = new File(RUTA_MAPA);
            if (archivoMapa.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivoMapa);
                    System.out.println("Abriendo el mapa de la isla...");
                } else {
                    System.out.println("No se puede abrir el mapa automáticamente en este sistema.");
                    System.out.println("Por favor, abre manualmente el archivo en: " + RUTA_MAPA);
                }
            } else {
                System.out.println("¡Error! No se encuentra el archivo del mapa en: " + RUTA_MAPA);
            }
        } catch (IOException e) {
            System.out.println("Error al intentar abrir el mapa: " + e.getMessage());
        }
    }
    
    private static void limpiarPantalla() {
        try {
            String sistemaOperativo = System.getProperty("os.name");
            
            // Para Windows
            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Para Unix/Linux/MacOS
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
                // Alternativa si el código ANSI no funciona
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            // Si los métodos anteriores fallan, intentamos con una solución alternativa
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private static void iniciarJuego() {
        limpiarPantalla();
        System.out.println("\n\n");
        System.out.println("=== EL TESORO DEL CAPITÁN ARVID ===\n");
        System.out.println("Eres Finn, nieto de un legendario capitán pirata. Unos hombres te ha dado un viejo diario");
        System.out.println("con pistas crípticas que te han llevado a esta misteriosa isla del Caribe.");
        System.out.println("Tu misión es encontrar el tesoro escondido de tu abuelo.");
        System.out.println("----------------------------------------");
        System.out.println("Presiona 'M' en cualquier momento para ver el mapa de la isla.");
        
        ubicacionActual = "playa";
        seleccionarGuardián();
        
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
                
                if (guardiánDerrotado) {
                    System.out.println("La entrada al tesoro está abierta, y dentro puedes ver el brillo del oro.");
                } else {
                    System.out.println("Hay un camino hacia el este que regresa a la jungla y");
                    System.out.println("otro estrecho sendero hacia el noreste que parece llevar a una cueva.");
                }
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
        
        System.out.print("\nElige una opción: ");
        
        // Manejo de entrada inválida
        while (!scanner.hasNextInt()) {
            String entrada = scanner.nextLine().toUpperCase();
            if (entrada.equals("M")) {
                abrirMapa();
                System.out.print("\nElige una opción: ");
            } else {
                System.out.println("Por favor, ingresa un número válido o 'M' para ver el mapa.");
                System.out.print("\nElige una opción: ");
            }
        }
        System.out.println("");
        return scanner.nextInt();
    }
    
    private static boolean procesarOpcion(int opcion) {

        if (opcion == 9) {
            System.out.println("¿Estás seguro que deseas salir del juego? (S/N)");
            scanner.nextLine(); 
            String confirmacion = scanner.nextLine().toUpperCase();
            if (confirmacion.startsWith("S")) {
                return false; // Terminar juego
            } else {
                return true; // Continuar juego
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
    
                    System.out.println("¿Quieres intentar forzar el cofre? (S/N)");
                    scanner.nextLine(); // Consumir la nueva línea
                    String respuestaForzar = scanner.nextLine().toUpperCase();
    
                        if (respuestaForzar.startsWith("S")) {
                            // Comprobar si el jugador tiene la herramienta adecuada
                            boolean tienePala = false;
                            for (int i = 0; i < itemsEnInventario; i++) {
                                if (inventario[i].equals("Pala")) tienePala = true;
                            }
                        
                        if (tienePala) {
                            System.out.println("Usas la pala para hacer palanca y abrir el cofre.");
                            System.out.println("Dentro encuentras un mapa antiguo que marca la ubicación del tesoro.");
                            System.out.println("Ahora sabes que debes buscar en la montaña.");
                        } else {
                            System.out.println("Al intentar forzar el cofre sin las herramientas adecuadas...");
                            System.out.println("¡Se activa una trampa!");
                            trampaActivada = true;
                            vidaJugador -= 30; // La trampa daña al jugador
                            System.out.println("Una nube de gas venenoso escapa del cofre y te causa 30 puntos de daño.");
                            
                            if (vidaJugador <= 0) {
                                mostrarFinalTrampa();
                            }
                        }
                    }
                    break;

                case 2:
                    ubicacionActual = "jungla";
                    System.out.println("Te adentras en la jungla...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } 
        else if (ubicacionActual.equals("jungla")) {
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
        } 
        else if (ubicacionActual.equals("ruinas")) {
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
        } 
        else if (ubicacionActual.equals("montaña")) {
            switch(opcion) {
                case 1:
                    System.out.println("Desde la cima puedes ver toda la isla. Notas un extraño patrón en la vegetación,");
                    System.out.println("como si formara una flecha apuntando hacia la cueva.");
                    
                    // Nuevo código para detectar el tesoro si tienes todos los objetos
                    if (itemsEnInventario == 3) {
                        boolean tienePala = false;
                        boolean tieneAntorcha = false;
                        boolean tieneCuerda = false;
                        
                        for (int i = 0; i < itemsEnInventario; i++) {
                            if (inventario[i].equals("Pala")) tienePala = true;
                            if (inventario[i].equals("Antorcha")) tieneAntorcha = true;
                            if (inventario[i].equals("Cuerda")) tieneCuerda = true;
                        }
                        
                        if (tienePala && tieneAntorcha && tieneCuerda) {
                            System.out.println("\n¡Con la ayuda de tus objetos, descubres una entrada secreta en la cima!");
                            System.out.println("¿Deseas explorar la entrada? (S/N)");
                            scanner.nextLine(); // Consumir la nueva línea
                            String respuesta = scanner.nextLine().toUpperCase();
                            
                            if (respuesta.startsWith("S")) {
                                limpiarPantalla();
                                System.out.println("\nUtilizas la cuerda para descender por la entrada secreta.");
                                System.out.println("La antorcha ilumina tu camino mientras avanzas.");
                                System.out.println("Al final del túnel, usas la pala para excavar y...");
                                System.out.println("\n¡ENCONTRASTE EL COFRE DEL TESORO!");
                                System.out.println("\nPero cuando te acercas, el suelo tiembla y aparece el guardián: ¡EL " + nombreGuardian + "!");

                                
                                iniciarCombate();
                            }
                        }
                    }
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
        } 
        else if (ubicacionActual.equals("cueva")) {
            switch(opcion) {
                case 1:
                    System.out.println("Examinas las marcas en la pared. Parecen ser un monstruo que esta vigilando un objeto valioso.");
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
   
    private static void seleccionarGuardián() {
        int tipoGuardián = (int)(Math.random() * 5); // Número aleatorio entre 0 y 4
        
        switch(tipoGuardián) {
            case 0:
                nombreGuardian = "Kraken de Sangre";
                vidaGuardian = 120;
                fuerzaGuardian = 15;
                ataqueEspecial = "Tentáculos Fantasmales";
                debilidadGuardian = "Antorcha";
                break;
            case 1:
                nombreGuardian = "Golem de Coral";
                vidaGuardian = 150;
                fuerzaGuardian = 12;
                ataqueEspecial = "Lluvia de Piedras";
                debilidadGuardian = "Pala";
                break;
            case 2:
                nombreGuardian = "Jaguar Fantasma";
                vidaGuardian = 100;
                fuerzaGuardian = 20;
                ataqueEspecial = "Zarpazo Mortal";
                debilidadGuardian = "Cuerda";
                break;
            case 3:
                nombreGuardian = "Serpiente Marina Ancestral";
                vidaGuardian = 130;
                fuerzaGuardian = 14;
                ataqueEspecial = "Veneno Paralizante";
                debilidadGuardian = "Antorcha";
                break;
            case 4:
                nombreGuardian = "Espíritu del Capitán ARVID";
                vidaGuardian = 110;
                fuerzaGuardian = 17;
                ataqueEspecial = "Maldición Pirata";
                debilidadGuardian = "Cuerda";
                break;
        }
    }

    private static void iniciarCombate() {
        System.out.println("\n=== COMBATE CONTRA " + nombreGuardian + " ===");
        System.out.println("El guardián del tesoro te mira con ojos brillantes y amenazadores.");
        System.out.println("Tu vida: " + vidaJugador + "  |  Vida del " + nombreGuardian + ": " + vidaGuardian);

        boolean combateActivo = true;
        while (combateActivo) {
            mostrarBarraVida(vidaJugador, 100, "Tú");
            mostrarBarraVida(vidaGuardian, vidaGuardian, nombreGuardian);
            
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Atacar con tu arma principal");
            
            // Mostrar opciones de objetos como armas
            for (int i = 0; i < itemsEnInventario; i++) {
                System.out.println((i + 2) + ". Usar " + inventario[i]);
            }
            
            System.out.println("9. Intentar huir");
            
            System.out.print("Elige una opción: ");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Por favor, ingresa un número válido.");
                System.out.print("Elige una opción: ");
            }
            int accion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            
            if (accion == 1) {
                // Ataque básico
                int daño = (int)(Math.random() * 15) + 10; // Entre 5 y 20 de daño
                vidaGuardian -= daño;
                System.out.println("\n¡Atacas al " + nombreGuardian + " y le causas " + daño + " puntos de daño!");
            } else if (accion >= 2 && accion <= itemsEnInventario + 1) {
                // Usar objeto del inventario
                String objeto = inventario[accion - 2];
                usarObjetoEnCombate(objeto);
            } else if (accion == 9) {
                // Intentar huir
                int probabilidadHuida = (int)(Math.random() * 10);
                if (probabilidadHuida > 7) { // 30% de probabilidad de huir
                    System.out.println("\n¡Lograste escapar del combate!");
                    System.out.println("Pero el tesoro sigue custodiado por el guardián...");
                    return;
                } else {
                    System.out.println("\n¡Intentas huir pero el " + nombreGuardian + " te bloquea el paso!");
                    int dañoGuardian = (int)(Math.random() * 15) + 10;
                    vidaJugador -= dañoGuardian;
                    System.out.println("Te ataca y te causa " + dañoGuardian + " puntos de daño.");
                }
            } else {
                System.out.println("\nOpción no válida. Pierdes tu turno.");
            }
            
            // Verificar si el guardián fue derrotado
            if (vidaGuardian <= 0) {
                guardiánDerrotado = true;
                tesoroEncontrado = true;
                System.out.println("\n¡Has derrotado al " + nombreGuardian + "!");
                System.out.println("El camino hacia el tesoro está libre. Te acercas al cofre y...");
                System.out.println("\n¡HAS ENCONTRADO EL TESORO DEL CAPITÁN ARVID!");
                mostrarFinalVictoria();
                combateActivo = false;
                return;
            }
            
            // Turno del guardián
            if (combateActivo) {
                System.out.println("\nEl " + nombreGuardian + " se prepara para atacar...");
                int ataqueGuardián = (int)(Math.random() * 3); // 0, 1 o 2
                
                if (ataqueGuardián == 0) {
                    // Ataque normal
                    int dañoGuardián = (int)(Math.random() * 10) + fuerzaGuardian; // Base + aleatorio
                    vidaJugador -= dañoGuardián;
                    System.out.println("¡El " + nombreGuardian + " te ataca y te causa " + dañoGuardián + " puntos de daño!");
                } else if (ataqueGuardián == 1) {
                    // Ataque especial
                    int dañoGuardián = (int)(Math.random() * 15) + fuerzaGuardian + 5; // Base + aleatorio + bonus
                    vidaJugador -= dañoGuardián;
                    System.out.println("¡El " + nombreGuardian + " usa " + ataqueEspecial + " y te causa " + dañoGuardián + " puntos de daño!");
                } else {
                    // Ataque que falla
                    System.out.println("¡El " + nombreGuardian + " intenta atacarte pero logras esquivarlo!");
                }
            }
                if (vidaJugador <= 0) {
                    System.out.println("\nHas recibido demasiado daño...");
                    System.out.println("Tu visión se nubla mientras caes al suelo.");
                    mostrarFinalDerrota();
                    combateActivo = false;
                    return;
                }
                
                System.out.println("\nPresiona Enter para continuar el combate...");
                scanner.nextLine();
            }
        }

    private static void mostrarBarraVida(int vidaActual, int vidaMaxima, String nombre) {
    int longitud = 20; // Longitud de la barra de vida
    int barrasLlenas = (int)((double)vidaActual / vidaMaxima * longitud);
    
    if (vidaActual < 0) vidaActual = 0;
    
    System.out.print("\n" + nombre + " [");
    for (int i = 0; i < longitud; i++) {
        if (i < barrasLlenas) {
            System.out.print("█");
        } else {
            System.out.print(" ");}
    }
    System.out.print("] " + vidaActual + "/" + vidaMaxima);
}

    private static void usarObjetoEnCombate(String objeto) {
    int daño;
    
    // Verificar si el objeto es la debilidad del guardián para causar daño extra
    if (objeto.equals(debilidadGuardian)) {
        daño = (int)(Math.random() * 30) + 25; // Entre 25 y 55 de daño
        System.out.println("\n¡Usas " + objeto + " contra el " + nombreGuardian + "!");
        System.out.println("¡Es super efectivo! Le causas " + daño + " puntos de daño.");
    } else {
        if (objeto.equals("Pala")) {
            daño = (int)(Math.random() * 20) + 15; // Entre 15 y 35 de daño
            System.out.println("\n¡Usas la pala como arma y golpeas al " + nombreGuardian + "!");
        } else if (objeto.equals("Antorcha")) {
            daño = (int)(Math.random() * 25) + 20; // Entre 20 y 45 de daño
            System.out.println("\n¡Lanzas la antorcha encendida al " + nombreGuardian + "!");
        } else if (objeto.equals("Cuerda")) {
            daño = (int)(Math.random() * 15) + 10; // Entre 10 y 25 de daño
            System.out.println("\n¡Usas la cuerda para inmovilizar temporalmente al " + nombreGuardian + "!");
            
            // Recuperar algo de vida
            int recuperación = (int)(Math.random() * 15) + 10; // Entre 10 y 25 de recuperación
            vidaJugador += recuperación;
            if (vidaJugador > 100) vidaJugador = 100;
            System.out.println("Aprovechas para recuperar " + recuperación + " puntos de vida.");
        } else {
            daño = (int)(Math.random() * 10) + 5; // Entre 5 y 15 de daño
            System.out.println("\n¡Usas " + objeto + " contra el " + nombreGuardian + "!");
        }
        
        System.out.println("Le causas " + daño + " puntos de daño.");
    }
    
    vidaGuardian -= daño;
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

    private static void mostrarFinalVictoria() {
        System.out.println("\n=== VICTORIA ===");
        System.out.println("Abres el cofre del tesoro y encuentras oro, joyas y reliquias de incalculable valor.");
        System.out.println("También encuentras el diario completo de tu abuelo, el Capitán Arvid, donde explica");
        System.out.println("cómo llego este tesoro a la isla durante sus aventuras por los siete mares.\n\n"+
                        "En una remota isla del Caribe, el joven capitán pirata Arvid encontró un tesoro indescriptible.\n"+
                        "Sin embargo, su propia tripulación, presa de la codicia, planeaba un boicot. Decidido a proteger su hallazgo, Arvid averió las velas del barco, encallando a la tripulación en la isla. Mientras ellos reparaban la embarcación, él se dedicó a vaciar el cofre original y esconder el tesoro. Ademas dejo en la playas un cofre hechizo que fabricó con restos del barco. Para ocultar su astuta jugada, llenó el cofre original con arena y guardó la llave en su pecho.\r\n" + 
                        "Cuando la nave estuvo lista para zarpar, el boicot estalló. En medio del caos, Arvid, luchando por su vida, lanzó la llave al mar, 'sellando' su fortuna para siempre. La furiosa tripulación ató al capitán al asta principal, preparándose para sacrificarlo. Su única petición fue que entregaran su viejo diario a su querido nieto, Finn, un niño que había escuchado historias sobre el legado de su abuelo.\r\n" + 
                        "Los piratas, sintiéndose victoriosos, regresaron a tierra con el cofre de acero. Sin embargo, abrirlo tomó meses; finalmente, tras mucho esfuerzo, lograron abrir el cofre. Pero el verdadero tesoro estaba en el diario de Arvid, un libro plagado de anagramas y pistas crípticas.\r\n" + 
                        "Años después, Finn se convirtió en un joven astuto y perspicaz. Dedicó horas a descifrar los anagramas de su abuelo, mezclando su ingenio con la inteligencia adquirida de las viejas leyendas. Con paciencia y determinación, logró descomponer las palabras y convertirlas en coordenadas precisas de la isla donde su abuelo había escondido el tesoro.\r\n" + 
                        "Con el mapa en mano, Finn partió de aventura hacia la isla que había sido parte de su historia familiar.");
        System.out.println("\n\n...Con el mapa y el tesoro en tu poder, regresas victorioso a tu barco.");
        System.out.println("\nHas completado el legado de tu abuelo y ahora tú también eres una leyenda.");
        System.out.println("\n=== FIN DEL JUEGO ===\n\n");
        
        System.out.println("\n¿Qué deseas hacer con el tesoro? (1. Llevarlo a un museo / 2. Quedártelo)");
        int decisionTesoro = scanner.nextInt();
        scanner.nextLine(); 

        if (decisionTesoro == 1) {
            System.out.println("\nDecides donar el tesoro a un museo para que todos puedan");
            System.out.println("apreciar la historia de tu abuelo. Te conviertes en un héroe local");
            System.out.println("y el museo dedica una exposición completa al Capitán Arvid.");
        } else {
            System.out.println("\nDecides quedarte con el tesoro. Te conviertes en una persona rica");
            System.out.println("y utilizas parte de la fortuna para financiar tus propias aventuras,");
            System.out.println("siguiendo los pasos de tu legendario abuelo.");
        }
        tesoroEncontrado = true;
        // Terminar el juego
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
        System.exit(0);
        mostrarEstadisticasFinales();
    }
    
    private static void mostrarFinalDerrota() {
        System.out.println("\n=== DERROTA ===");
        System.out.println("El " + nombreGuardian + " ha sido demasiado poderoso para ti.");
        System.out.println("Tu aventura termina aquí, y el tesoro del Capitán Arvid");
        System.out.println("permanecerá oculto, esperando a un nuevo aventurero...");
        System.out.println("\n=== FIN DEL JUEGO ===");
        
        // Terminar el juego
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
        System.exit(0);
        mostrarEstadisticasFinales();
    }
    
    private static void mostrarFinalTrampa() {
        System.out.println("\n=== TRAMPA MORTAL ===");
        System.out.println("Al intentar abrir el cofre, activas una trampa oculta.");
        System.out.println("Un gas venenoso empieza a llenar la cámara rápidamente.");
        System.out.println("Por más que intentas encontrar una salida, es demasiado tarde...");
        System.out.println("\nTu aventura termina aquí, víctima de la última protección");
        System.out.println("que tu abuelo puso para proteger su tesoro de los intrusos.");
        System.out.println("\n=== FIN DEL JUEGO ===");
        
        // Terminar el juego
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
        System.exit(0);
        mostrarEstadisticasFinales();
    }

    private static void mostrarEstadisticasFinales() {
        System.out.println("\n=== ESTADÍSTICAS DE TU AVENTURA ===");
        System.out.println("Tesoro encontrado: " + (tesoroEncontrado ? "Sí" : "No"));
        System.out.println("Guardián derrotado: " + (guardiánDerrotado ? "Sí" : "No"));
        System.out.println("Trampas activadas: " + (trampaActivada ? "Sí" : "No"));
        System.out.println("Vida final: " + vidaJugador);
        System.out.println("Objetos recolectados: " + itemsEnInventario + "/3");
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

    private static void mostrarEstadoJugador() {
        System.out.println("\n--- ESTADO ---");
        mostrarBarraVida(vidaJugador, 100, "Vida");
    }
    
public static void main(String[] args) {
    iniciarJuego();
    
    boolean juegoEnCurso = true;
    while (juegoEnCurso) {
        mostrarDescripcionUbicacion();
        mostrarEstadoJugador();
        int opcion = pedirOpcion();
        juegoEnCurso = procesarOpcion(opcion);
        
        // Verificar si el jugador ha perdido toda su vida
        if (vidaJugador <= 0) {
            mostrarFinalDerrota();
            juegoEnCurso = false;
        }
        
        // Pausa para que el jugador pueda leer los resultados 
        if (juegoEnCurso) {
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
            scanner.nextLine();
            limpiarPantalla();
        }
    }
    
        scanner.close();
        System.out.println("¡Gracias por jugar!");
    }
}
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;
import java.util.HashMap;
import java.util.Map;

public class BusquedaDelTesoro2 {
    
    // Variables globales
    private static String ubicacionActual;
    private static String[] inventario = new String[6]; // Aumentamos la capacidad del inventario
    private static int itemsEnInventario = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static final String RUTA_MAPA = "src/main/resources/mapa.jpg";
    
    // Variables para el seguimiento de acertijos
    private static Map<String, Boolean> objetosEncontrados = new HashMap<>();
    private static Map<String, Boolean> acertijosResueltos = new HashMap<>();
    private static boolean acertijoCoordenadasActivo = false;
    private static boolean acertijoSimbolosActivo = false;
    private static boolean acertijoFinalActivo = false;
    private static boolean tesoroEncontrado = false;
    
    // Inicialización de los objetos y acertijos
    private static void inicializarObjetos() {
        objetosEncontrados.put("Pala", false);
        objetosEncontrados.put("Antorcha", false);
        objetosEncontrados.put("Cuerda", false);
        objetosEncontrados.put("Llave Oxidada", false);
        objetosEncontrados.put("Mapa Antiguo", false);
        objetosEncontrados.put("Brújula", false);
        objetosEncontrados.put("Medallón", false);
        objetosEncontrados.put("Linterna", false);
        
        acertijosResueltos.put("Coordenadas", false);
        acertijosResueltos.put("Símbolos", false);
        acertijosResueltos.put("Final", false);
    }
    
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
        inicializarObjetos();
        
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
                if (!objetosEncontrados.get("Brújula")) {
                    System.out.println("Algo metálico brilla entre las rocas cercanas a la orilla.");
                }
                System.out.println("Al norte se extiende una densa jungla.");
                break;
            case "jungla":
                System.out.println("Te encuentras en medio de una espesa jungla tropical.");
                System.out.println("Los sonidos de animales exóticos te rodean por todas partes.");
                if (!objetosEncontrados.get("Linterna")) {
                    System.out.println("Un destello metálico se asoma entre la espesa vegetación.");
                }
                System.out.println("Ves un camino hacia el este que lleva a unas ruinas, otro hacia el oeste");
                System.out.println("que parece dirigirse a una montaña, y al sur está la playa.");
                break;
            case "ruinas":
                System.out.println("Estás entre los restos de lo que parece ser un antiguo asentamiento.");
                System.out.println("Columnas derruidas y paredes a medio derrumbar te rodean.");
                if (!objetosEncontrados.get("Mapa Antiguo")) {
                    System.out.println("En un nicho de la pared notas lo que parece ser un pergamino viejo y desgastado.");
                }
                System.out.println("Puedes ver un sendero hacia el norte que conduce a una cueva oscura,");
                System.out.println("y otro hacia el oeste que regresa a la jungla.");
                break;
            case "montaña":
                System.out.println("Has ascendido a una montaña con vistas panorámicas de toda la isla.");
                System.out.println("El aire es más fresco aquí arriba y puedes ver el mar en todas direcciones.");
                if (!objetosEncontrados.get("Llave Oxidada")) {
                    System.out.println("Entre las rocas, hay un destello metálico que llama tu atención.");
                }
                System.out.println("Hay un camino hacia el este que regresa a la jungla y");
                System.out.println("otro estrecho sendero hacia el noreste que parece llevar a una cueva.");
                break;
            case "cueva":
                System.out.println("Te encuentras en una cueva oscura y húmeda. Gotas de agua caen del techo.");
                if (tieneObjeto("Linterna")) {
                    System.out.println("Con tu linterna, iluminas las extrañas marcas y símbolos tallados en las paredes.");
                    if (!objetosEncontrados.get("Medallón")) {
                        System.out.println("En un pequeño hueco de la pared, notas algo que brilla tenuemente.");
                    }
                } else {
                    System.out.println("Está demasiado oscuro para ver bien. Necesitarías alguna fuente de luz.");
                }
                System.out.println("Hay salidas hacia el sur que llevan a las ruinas y");
                System.out.println("hacia el suroeste que conducen a la montaña.");
                break;
            case "tesoro":
                System.out.println("Has llegado a una pequeña cámara oculta. La luz se filtra por una grieta en el techo.");
                System.out.println("En el centro de la habitación, ves un cofre ornamentado de madera y hierro.");
                System.out.println("¡Es el tesoro del Capitán Arvid!");
                if (tieneObjeto("Llave Oxidada") && !tesoroEncontrado) {
                    System.out.println("El cofre tiene un candado que parece coincidir con la llave que encontraste.");
                }
                break;
        }
    }
    
    private static int pedirOpcion() {
        System.out.println("\n¿Qué deseas hacer?");
        
        if (ubicacionActual.equals("playa")) {
            System.out.println("1. Examinar cofre");
            if (!objetosEncontrados.get("Brújula")) {
                System.out.println("2. Examinar las rocas brillantes");
            }
            System.out.println("3. Ir al norte (Jungla)");
            
            if (acertijosResueltos.get("Final") && !tesoroEncontrado) {
                System.out.println("4. Excavar en la X marcada");
            }
        } else if (ubicacionActual.equals("jungla")) {
            System.out.println("1. Examinar alrededores");
            if (!objetosEncontrados.get("Linterna")) {
                System.out.println("2. Investigar el destello metálico");
            }
            System.out.println("3. Ir al este (Ruinas)");
            System.out.println("4. Ir al oeste (Montaña)");
            System.out.println("5. Ir al sur (Playa)");
        } else if (ubicacionActual.equals("ruinas")) {
            System.out.println("1. Examinar ruinas");
            if (!objetosEncontrados.get("Mapa Antiguo")) {
                System.out.println("2. Examinar el pergamino");
            }
            System.out.println("3. Ir al norte (Cueva)");
            System.out.println("4. Ir al oeste (Jungla)");
            
            if (acertijoCoordenadasActivo && tieneObjeto("Mapa Antiguo") && tieneObjeto("Brújula")) {
                System.out.println("5. Resolver el acertijo de las coordenadas");
            }
        } else if (ubicacionActual.equals("montaña")) {
            System.out.println("1. Examinar la cima");
            if (!objetosEncontrados.get("Llave Oxidada")) {
                System.out.println("2. Examinar el destello entre las rocas");
            }
            System.out.println("3. Ir al este (Jungla)");
            System.out.println("4. Ir al noreste (Cueva)");
        } else if (ubicacionActual.equals("cueva")) {
            if (tieneObjeto("Linterna")) {
                System.out.println("1. Examinar las marcas en la pared");
                if (!objetosEncontrados.get("Medallón")) {
                    System.out.println("2. Examinar el hueco brillante");
                }
                
                if (acertijoSimbolosActivo && tieneObjeto("Medallón")) {
                    System.out.println("3. Resolver el acertijo de los símbolos");
                }
            } else {
                System.out.println("1. Necesitas una fuente de luz para ver bien aquí");
            }
            System.out.println("4. Ir al sur (Ruinas)");
            System.out.println("5. Ir al suroeste (Montaña)");
        } else if (ubicacionActual.equals("tesoro")) {
            if (!tesoroEncontrado && tieneObjeto("Llave Oxidada")) {
                System.out.println("1. Abrir el cofre con la llave");
            } else if (tesoroEncontrado) {
                System.out.println("1. Examinar el tesoro");
            }
            System.out.println("2. Regresar a la cueva");
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
        // Opción que termina el bucle
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
                    if (!objetosEncontrados.get("Brújula")) {
                        System.out.println("Removiendo la arena entre las rocas, encuentras una brújula antigua");
                        System.out.println("pero aún funcional. Será útil para orientarte en la isla.");
                        agregarAlInventario("Brújula");
                        objetosEncontrados.put("Brújula", true);
                        
                        // Activar acertijo de las coordenadas si ya tiene el mapa
                        if (tieneObjeto("Mapa Antiguo")) {
                            System.out.println("\nAhora que tienes la brújula y el mapa antiguo, podrías");
                            System.out.println("intentar descifrar las coordenadas marcadas en las ruinas.");
                            acertijoCoordenadasActivo = true;
                        }
                    }
                    break;
                case 3:
                    ubicacionActual = "jungla";
                    System.out.println("Te adentras en la jungla...");
                    break;
                case 4:
                    if (acertijosResueltos.get("Final") && !tesoroEncontrado) {
                        if (tieneObjeto("Pala")) {
                            System.out.println("Comienzas a excavar en el punto exacto marcado con la X.");
                            System.out.println("Después de un rato, tu pala golpea algo sólido...");
                            System.out.println("¡Has encontrado una entrada secreta que lleva a una cámara subterránea!");
                            ubicacionActual = "tesoro";
                        } else {
                            System.out.println("Necesitarás una pala para excavar aquí.");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("jungla")) {
            switch(opcion) {
                case 1:
                    System.out.println("Al examinar los alrededores, encuentras una pala vieja entre la vegetación.");
                    if (!objetosEncontrados.get("Pala")) {
                        agregarAlInventario("Pala");
                        objetosEncontrados.put("Pala", true);
                    }
                    break;
                case 2:
                    if (!objetosEncontrados.get("Linterna")) {
                        System.out.println("Entre la vegetación, encuentras una linterna en sorprendente buen estado.");
                        System.out.println("Parece que aún funciona y será útil en lugares oscuros.");
                        agregarAlInventario("Linterna");
                        objetosEncontrados.put("Linterna", true);
                    }
                    break;
                case 3:
                    ubicacionActual = "ruinas";
                    System.out.println("Te diriges hacia las ruinas...");
                    break;
                case 4:
                    ubicacionActual = "montaña";
                    System.out.println("Comienzas a subir la montaña...");
                    break;
                case 5:
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
                    if (!objetosEncontrados.get("Antorcha")) {
                        agregarAlInventario("Antorcha");
                        objetosEncontrados.put("Antorcha", true);
                    }
                    break;
                case 2:
                    if (!objetosEncontrados.get("Mapa Antiguo")) {
                        System.out.println("Desenrollas cuidadosamente el pergamino. Es un mapa antiguo de la isla");
                        System.out.println("con extrañas marcas y números que parecen coordenadas.");
                        System.out.println("Si tuvieras una brújula, podrías interpretarlo mejor.");
                        agregarAlInventario("Mapa Antiguo");
                        objetosEncontrados.put("Mapa Antiguo", true);
                        
                        // Activar acertijo de las coordenadas si ya tiene la brújula
                        if (tieneObjeto("Brújula")) {
                            System.out.println("\nAhora que tienes el mapa antiguo y la brújula, podrías");
                            System.out.println("intentar descifrar las coordenadas marcadas aquí en las ruinas.");
                            acertijoCoordenadasActivo = true;
                        }
                    }
                    break;
                case 3:
                    ubicacionActual = "cueva";
                    System.out.println("Te adentras en la oscura cueva...");
                    break;
                case 4:
                    ubicacionActual = "jungla";
                    System.out.println("Regresas a la jungla...");
                    break;
                case 5:
                    if (acertijoCoordenadasActivo && tieneObjeto("Mapa Antiguo") && tieneObjeto("Brújula")) {
                        System.out.println("Extiendes el mapa antiguo y lo orientas con la brújula.");
                        System.out.println("Las coordenadas parecen indicar que hay algo importante en la costa sur,");
                        System.out.println("cerca de donde llegaste inicialmente a la isla.");
                        System.out.println("Combinas esto con los dibujos del mapa y descubres que señalan");
                        System.out.println("a un punto específico que podrías encontrar en la playa.");
                        
                        System.out.println("\nAhora solo necesitas descifrar los símbolos de la cueva para");
                        System.out.println("completar el acertijo final y encontrar el tesoro.");
                        
                        acertijosResueltos.put("Coordenadas", true);
                        
                        // Activar acertijo final si ya resolvió el de los símbolos
                        if (acertijosResueltos.get("Símbolos")) {
                            System.out.println("\n¡Has descifrado todos los acertijos! Ahora sabes que el tesoro");
                            System.out.println("está enterrado en un punto específico de la playa.");
                            System.out.println("Deberías ir allí y excavar en el lugar exacto marcado por la X.");
                            acertijosResueltos.put("Final", true);
                        }
                    }
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
                    if (!objetosEncontrados.get("Llave Oxidada")) {
                        System.out.println("Entre las rocas encuentras una llave pequeña y oxidada.");
                        System.out.println("Parece muy antigua, pero sorprendentemente resistente.");
                        agregarAlInventario("Llave Oxidada");
                        objetosEncontrados.put("Llave Oxidada", true);
                    }
                    break;
                case 3:
                    ubicacionActual = "jungla";
                    System.out.println("Desciendes hacia la jungla...");
                    break;
                case 4:
                    ubicacionActual = "cueva";
                    System.out.println("Sigues el estrecho sendero hacia la cueva...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("cueva")) {
            switch(opcion) {
                case 1:
                    if (tieneObjeto("Linterna")) {
                        System.out.println("Con la linterna, examinas detalladamente las marcas en la pared.");
                        System.out.println("Son símbolos extraños que parecen contar una historia sobre un tesoro escondido.");
                        System.out.println("Hay lo que parece ser un medallón dibujado junto a los símbolos.");
                        
                        if (tieneObjeto("Medallón") && !acertijoSimbolosActivo) {
                            System.out.println("\nEse medallón que encontraste parece coincidir con estos dibujos.");
                            System.out.println("Quizás puedas usarlo para descifrar estos símbolos.");
                            acertijoSimbolosActivo = true;
                        }
                    } else {
                        System.out.println("Está demasiado oscuro. Necesitas alguna fuente de luz para ver mejor.");
                    }
                    break;
                case 2:
                    if (tieneObjeto("Linterna") && !objetosEncontrados.get("Medallón")) {
                        System.out.println("Con la ayuda de tu linterna, alcanzas a ver dentro del hueco y encuentras");
                        System.out.println("un medallón antiguo con extraños símbolos grabados en su superficie.");
                        agregarAlInventario("Medallón");
                        objetosEncontrados.put("Medallón", true);
                        
                        System.out.println("\nEste medallón parece coincidir con los dibujos en la pared.");
                        System.out.println("Quizás puedas usarlo para descifrar los símbolos.");
                        acertijoSimbolosActivo = true;
                    }
                    break;
                case 3:
                    if (acertijoSimbolosActivo && tieneObjeto("Medallón")) {
                        System.out.println("Colocas el medallón sobre la marca correspondiente en la pared.");
                        System.out.println("Encaja perfectamente, y al girarlo, los símbolos comienzan a alinearse.");
                        System.out.println("Los símbolos parecen formar instrucciones sobre el tesoro:");
                        System.out.println("'Donde el sol se oculta en el horizonte, a 20 pasos desde la roca partida.'");
                        
                        acertijosResueltos.put("Símbolos", true);
                        
                        // Activar acertijo final si ya resolvió el de las coordenadas
                        if (acertijosResueltos.get("Coordenadas")) {
                            System.out.println("\n¡Has descifrado todos los acertijos! Combinando esta información con");
                            System.out.println("las coordenadas del mapa, ahora sabes que el tesoro está enterrado");
                            System.out.println("en un punto específico de la playa, a 20 pasos desde la roca partida,");
                            System.out.println("en dirección a donde se pone el sol.");
                            System.out.println("Deberías ir allí y excavar en el lugar exacto.");
                            acertijosResueltos.put("Final", true);
                        }
                    }
                    break;
                case 4:
                    ubicacionActual = "ruinas";
                    System.out.println("Sales de la cueva hacia las ruinas...");
                    break;
                case 5:
                    ubicacionActual = "montaña";
                    System.out.println("Tomas el sendero que lleva a la montaña...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("tesoro")) {
            switch(opcion) {
                case 1:
                    if (!tesoroEncontrado && tieneObjeto("Llave Oxidada")) {
                        System.out.println("Introduces la llave oxidada en el candado del cofre. Gira con dificultad,");
                        System.out.println("pero finalmente escuchas un satisfactorio 'clic'.");
                        System.out.println("\nLevantas lentamente la tapa del cofre para revelar su contenido...");
                        System.out.println("\n¡El tesoro del Capitán Arvid! Monedas de oro, joyas brillantes y piedras preciosas");
                        System.out.println("llenan el cofre hasta los bordes. También encuentras una carta sellada dirigida a ti.");
                        System.out.println("\nLa carta dice:");
                        System.out.println("'Querido Finn, si estás leyendo esto, has demostrado tener el ingenio y");
                        System.out.println("la determinación de un verdadero pirata. Este tesoro es tuyo por derecho,");
                        System.out.println("pero recuerda que la verdadera riqueza está en las aventuras vividas");
                        System.out.println("y en las historias que podrás contar. Con orgullo, tu abuelo, Capitán Arvid.'");
                        System.out.println("\n¡FELICIDADES! Has completado la búsqueda del tesoro.");
                        tesoroEncontrado = true;
                    } else if (tesoroEncontrado) {
                        System.out.println("Contemplas el tesoro con satisfacción. Las monedas de oro y joyas brillan");
                        System.out.println("a la luz que se filtra desde arriba. La carta de tu abuelo descansa sobre ellas,");
                        System.out.println("un testimonio de su astucia y de tu éxito al seguir sus pistas.");
                    }
                    break;
                case 2:
                    ubicacionActual = "cueva";
                    System.out.println("Sales de la cámara del tesoro y regresas a la cueva...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        
        return true;
    }
    
    private static boolean tieneObjeto(String objeto) {
        return objetosEncontrados.getOrDefault(objeto, false);
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
        System.out.println("Capacidad: " + itemsEnInventario + "/6");
        
        if (acertijoCoordenadasActivo || acertijoSimbolosActivo || acertijosResueltos.get("Final")) {
            System.out.println("\n--- ACERTIJOS ---");
            if (acertijoCoordenadasActivo) {
                System.out.print("Acertijo de las Coordenadas: ");
                System.out.println(acertijosResueltos.get("Coordenadas") ? "RESUELTO" : "PENDIENTE");
            }
            if (acertijoSimbolosActivo) {
                System.out.print("Acertijo de los Símbolos: ");
                System.out.println(acertijosResueltos.get("Símbolos") ? "RESUELTO" : "PENDIENTE");
            }
            if (acertijosResueltos.get("Final")) {
                System.out.println("¡Todos los acertijos resueltos! El tesoro se encuentra en la playa.");
            }
        }
    }
    
    private static void agregarAlInventario(String objeto) {
        if (itemsEnInventario < 6) {
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

        import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;
import java.util.HashMap;
import java.util.Map;

public class BusquedaDelTesoro {
    
    // Variables globales
    private static String ubicacionActual;
    private static String[] inventario = new String[6]; // Aumentamos la capacidad del inventario
    private static int itemsEnInventario = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static final String RUTA_MAPA = "src/main/resources/mapa.jpg";
    
    // Variables para el seguimiento de acertijos
    private static Map<String, Boolean> objetosEncontrados = new HashMap<>();
    private static Map<String, Boolean> acertijosResueltos = new HashMap<>();
    private static boolean acertijoCoordenadasActivo = false;
    private static boolean acertijoSimbolosActivo = false;
    private static boolean acertijoFinalActivo = false;
    private static boolean tesoroEncontrado = false;
    
    // Inicialización de los objetos y acertijos
    private static void inicializarObjetos() {
        objetosEncontrados.put("Pala", false);
        objetosEncontrados.put("Antorcha", false);
        objetosEncontrados.put("Cuerda", false);
        objetosEncontrados.put("Llave Oxidada", false);
        objetosEncontrados.put("Mapa Antiguo", false);
        objetosEncontrados.put("Brújula", false);
        objetosEncontrados.put("Medallón", false);
        objetosEncontrados.put("Linterna", false);
        
        acertijosResueltos.put("Coordenadas", false);
        acertijosResueltos.put("Símbolos", false);
        acertijosResueltos.put("Final", false);
    }
    
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
        inicializarObjetos();
        
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
                if (!objetosEncontrados.get("Brújula")) {
                    System.out.println("Algo metálico brilla entre las rocas cercanas a la orilla.");
                }
                System.out.println("Al norte se extiende una densa jungla.");
                break;
            case "jungla":
                System.out.println("Te encuentras en medio de una espesa jungla tropical.");
                System.out.println("Los sonidos de animales exóticos te rodean por todas partes.");
                if (!objetosEncontrados.get("Linterna")) {
                    System.out.println("Un destello metálico se asoma entre la espesa vegetación.");
                }
                System.out.println("Ves un camino hacia el este que lleva a unas ruinas, otro hacia el oeste");
                System.out.println("que parece dirigirse a una montaña, y al sur está la playa.");
                break;
            case "ruinas":
                System.out.println("Estás entre los restos de lo que parece ser un antiguo asentamiento.");
                System.out.println("Columnas derruidas y paredes a medio derrumbar te rodean.");
                if (!objetosEncontrados.get("Mapa Antiguo")) {
                    System.out.println("En un nicho de la pared notas lo que parece ser un pergamino viejo y desgastado.");
                }
                System.out.println("Puedes ver un sendero hacia el norte que conduce a una cueva oscura,");
                System.out.println("y otro hacia el oeste que regresa a la jungla.");
                break;
            case "montaña":
                System.out.println("Has ascendido a una montaña con vistas panorámicas de toda la isla.");
                System.out.println("El aire es más fresco aquí arriba y puedes ver el mar en todas direcciones.");
                if (!objetosEncontrados.get("Llave Oxidada")) {
                    System.out.println("Entre las rocas, hay un destello metálico que llama tu atención.");
                }
                System.out.println("Hay un camino hacia el este que regresa a la jungla y");
                System.out.println("otro estrecho sendero hacia el noreste que parece llevar a una cueva.");
                break;
            case "cueva":
                System.out.println("Te encuentras en una cueva oscura y húmeda. Gotas de agua caen del techo.");
                if (tieneObjeto("Linterna")) {
                    System.out.println("Con tu linterna, iluminas las extrañas marcas y símbolos tallados en las paredes.");
                    if (!objetosEncontrados.get("Medallón")) {
                        System.out.println("En un pequeño hueco de la pared, notas algo que brilla tenuemente.");
                    }
                } else {
                    System.out.println("Está demasiado oscuro para ver bien. Necesitarías alguna fuente de luz.");
                }
                System.out.println("Hay salidas hacia el sur que llevan a las ruinas y");
                System.out.println("hacia el suroeste que conducen a la montaña.");
                break;
            case "tesoro":
                System.out.println("Has llegado a una pequeña cámara oculta. La luz se filtra por una grieta en el techo.");
                System.out.println("En el centro de la habitación, ves un cofre ornamentado de madera y hierro.");
                System.out.println("¡Es el tesoro del Capitán Arvid!");
                if (tieneObjeto("Llave Oxidada") && !tesoroEncontrado) {
                    System.out.println("El cofre tiene un candado que parece coincidir con la llave que encontraste.");
                }
                break;
        }
    }
    
    private static int pedirOpcion() {
        System.out.println("\n¿Qué deseas hacer?");
        
        if (ubicacionActual.equals("playa")) {
            System.out.println("1. Examinar cofre");
            if (!objetosEncontrados.get("Brújula")) {
                System.out.println("2. Examinar las rocas brillantes");
            }
            System.out.println("3. Ir al norte (Jungla)");
            
            if (acertijosResueltos.get("Final") && !tesoroEncontrado) {
                System.out.println("4. Excavar en la X marcada");
            }
        } else if (ubicacionActual.equals("jungla")) {
            System.out.println("1. Examinar alrededores");
            if (!objetosEncontrados.get("Linterna")) {
                System.out.println("2. Investigar el destello metálico");
            }
            System.out.println("3. Ir al este (Ruinas)");
            System.out.println("4. Ir al oeste (Montaña)");
            System.out.println("5. Ir al sur (Playa)");
        } else if (ubicacionActual.equals("ruinas")) {
            System.out.println("1. Examinar ruinas");
            if (!objetosEncontrados.get("Mapa Antiguo")) {
                System.out.println("2. Examinar el pergamino");
            }
            System.out.println("3. Ir al norte (Cueva)");
            System.out.println("4. Ir al oeste (Jungla)");
            
            if (acertijoCoordenadasActivo && tieneObjeto("Mapa Antiguo") && tieneObjeto("Brújula")) {
                System.out.println("5. Resolver el acertijo de las coordenadas");
            }
        } else if (ubicacionActual.equals("montaña")) {
            System.out.println("1. Examinar la cima");
            if (!objetosEncontrados.get("Llave Oxidada")) {
                System.out.println("2. Examinar el destello entre las rocas");
            }
            System.out.println("3. Ir al este (Jungla)");
            System.out.println("4. Ir al noreste (Cueva)");
        } else if (ubicacionActual.equals("cueva")) {
            if (tieneObjeto("Linterna")) {
                System.out.println("1. Examinar las marcas en la pared");
                if (!objetosEncontrados.get("Medallón")) {
                    System.out.println("2. Examinar el hueco brillante");
                }
                
                if (acertijoSimbolosActivo && tieneObjeto("Medallón")) {
                    System.out.println("3. Resolver el acertijo de los símbolos");
                }
            } else {
                System.out.println("1. Necesitas una fuente de luz para ver bien aquí");
            }
            System.out.println("4. Ir al sur (Ruinas)");
            System.out.println("5. Ir al suroeste (Montaña)");
        } else if (ubicacionActual.equals("tesoro")) {
            if (!tesoroEncontrado && tieneObjeto("Llave Oxidada")) {
                System.out.println("1. Abrir el cofre con la llave");
            } else if (tesoroEncontrado) {
                System.out.println("1. Examinar el tesoro");
            }
            System.out.println("2. Regresar a la cueva");
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
        // Opción que termina el bucle
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
                    if (!objetosEncontrados.get("Brújula")) {
                        System.out.println("Removiendo la arena entre las rocas, encuentras una brújula antigua");
                        System.out.println("pero aún funcional. Será útil para orientarte en la isla.");
                        agregarAlInventario("Brújula");
                        objetosEncontrados.put("Brújula", true);
                        
                        // Activar acertijo de las coordenadas si ya tiene el mapa
                        if (tieneObjeto("Mapa Antiguo")) {
                            System.out.println("\nAhora que tienes la brújula y el mapa antiguo, podrías");
                            System.out.println("intentar descifrar las coordenadas marcadas en las ruinas.");
                            acertijoCoordenadasActivo = true;
                        }
                    }
                    break;
                case 3:
                    ubicacionActual = "jungla";
                    System.out.println("Te adentras en la jungla...");
                    break;
                case 4:
                    if (acertijosResueltos.get("Final") && !tesoroEncontrado) {
                        if (tieneObjeto("Pala")) {
                            System.out.println("Comienzas a excavar en el punto exacto marcado con la X.");
                            System.out.println("Después de un rato, tu pala golpea algo sólido...");
                            System.out.println("¡Has encontrado una entrada secreta que lleva a una cámara subterránea!");
                            ubicacionActual = "tesoro";
                        } else {
                            System.out.println("Necesitarás una pala para excavar aquí.");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("jungla")) {
            switch(opcion) {
                case 1:
                    System.out.println("Al examinar los alrededores, encuentras una pala vieja entre la vegetación.");
                    if (!objetosEncontrados.get("Pala")) {
                        agregarAlInventario("Pala");
                        objetosEncontrados.put("Pala", true);
                    }
                    break;
                case 2:
                    if (!objetosEncontrados.get("Linterna")) {
                        System.out.println("Entre la vegetación, encuentras una linterna en sorprendente buen estado.");
                        System.out.println("Parece que aún funciona y será útil en lugares oscuros.");
                        agregarAlInventario("Linterna");
                        objetosEncontrados.put("Linterna", true);
                    }
                    break;
                case 3:
                    ubicacionActual = "ruinas";
                    System.out.println("Te diriges hacia las ruinas...");
                    break;
                case 4:
                    ubicacionActual = "montaña";
                    System.out.println("Comienzas a subir la montaña...");
                    break;
                case 5:
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
                    if (!objetosEncontrados.get("Antorcha")) {
                        agregarAlInventario("Antorcha");
                        objetosEncontrados.put("Antorcha", true);
                    }
                    break;
                case 2:
                    if (!objetosEncontrados.get("Mapa Antiguo")) {
                        System.out.println("Desenrollas cuidadosamente el pergamino. Es un mapa antiguo de la isla");
                        System.out.println("con extrañas marcas y números que parecen coordenadas.");
                        System.out.println("Si tuvieras una brújula, podrías interpretarlo mejor.");
                        agregarAlInventario("Mapa Antiguo");
                        objetosEncontrados.put("Mapa Antiguo", true);
                        
                        // Activar acertijo de las coordenadas si ya tiene la brújula
                        if (tieneObjeto("Brújula")) {
                            System.out.println("\nAhora que tienes el mapa antiguo y la brújula, podrías");
                            System.out.println("intentar descifrar las coordenadas marcadas aquí en las ruinas.");
                            acertijoCoordenadasActivo = true;
                        }
                    }
                    break;
                case 3:
                    ubicacionActual = "cueva";
                    System.out.println("Te adentras en la oscura cueva...");
                    break;
                case 4:
                    ubicacionActual = "jungla";
                    System.out.println("Regresas a la jungla...");
                    break;
                case 5:
                    if (acertijoCoordenadasActivo && tieneObjeto("Mapa Antiguo") && tieneObjeto("Brújula")) {
                        System.out.println("Extiendes el mapa antiguo y lo orientas con la brújula.");
                        System.out.println("Las coordenadas parecen indicar que hay algo importante en la costa sur,");
                        System.out.println("cerca de donde llegaste inicialmente a la isla.");
                        System.out.println("Combinas esto con los dibujos del mapa y descubres que señalan");
                        System.out.println("a un punto específico que podrías encontrar en la playa.");
                        
                        System.out.println("\nAhora solo necesitas descifrar los símbolos de la cueva para");
                        System.out.println("completar el acertijo final y encontrar el tesoro.");
                        
                        acertijosResueltos.put("Coordenadas", true);
                        
                        // Activar acertijo final si ya resolvió el de los símbolos
                        if (acertijosResueltos.get("Símbolos")) {
                            System.out.println("\n¡Has descifrado todos los acertijos! Ahora sabes que el tesoro");
                            System.out.println("está enterrado en un punto específico de la playa.");
                            System.out.println("Deberías ir allí y excavar en el lugar exacto marcado por la X.");
                            acertijosResueltos.put("Final", true);
                        }
                    }
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
                    if (!objetosEncontrados.get("Llave Oxidada")) {
                        System.out.println("Entre las rocas encuentras una llave pequeña y oxidada.");
                        System.out.println("Parece muy antigua, pero sorprendentemente resistente.");
                        agregarAlInventario("Llave Oxidada");
                        objetosEncontrados.put("Llave Oxidada", true);
                    }
                    break;
                case 3:
                    ubicacionActual = "jungla";
                    System.out.println("Desciendes hacia la jungla...");
                    break;
                case 4:
                    ubicacionActual = "cueva";
                    System.out.println("Sigues el estrecho sendero hacia la cueva...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("cueva")) {
            switch(opcion) {
                case 1:
                    if (tieneObjeto("Linterna")) {
                        System.out.println("Con la linterna, examinas detalladamente las marcas en la pared.");
                        System.out.println("Son símbolos extraños que parecen contar una historia sobre un tesoro escondido.");
                        System.out.println("Hay lo que parece ser un medallón dibujado junto a los símbolos.");
                        
                        if (tieneObjeto("Medallón") && !acertijoSimbolosActivo) {
                            System.out.println("\nEse medallón que encontraste parece coincidir con estos dibujos.");
                            System.out.println("Quizás puedas usarlo para descifrar estos símbolos.");
                            acertijoSimbolosActivo = true;
                        }
                    } else {
                        System.out.println("Está demasiado oscuro. Necesitas alguna fuente de luz para ver mejor.");
                    }
                    break;
                case 2:
                    if (tieneObjeto("Linterna") && !objetosEncontrados.get("Medallón")) {
                        System.out.println("Con la ayuda de tu linterna, alcanzas a ver dentro del hueco y encuentras");
                        System.out.println("un medallón antiguo con extraños símbolos grabados en su superficie.");
                        agregarAlInventario("Medallón");
                        objetosEncontrados.put("Medallón", true);
                        
                        System.out.println("\nEste medallón parece coincidir con los dibujos en la pared.");
                        System.out.println("Quizás puedas usarlo para descifrar los símbolos.");
                        acertijoSimbolosActivo = true;
                    }
                    break;
                case 3:
                    if (acertijoSimbolosActivo && tieneObjeto("Medallón")) {
                        System.out.println("Colocas el medallón sobre la marca correspondiente en la pared.");
                        System.out.println("Encaja perfectamente, y al girarlo, los símbolos comienzan a alinearse.");
                        System.out.println("Los símbolos parecen formar instrucciones sobre el tesoro:");
                        System.out.println("'Donde el sol se oculta en el horizonte, a 20 pasos desde la roca partida.'");
                        
                        acertijosResueltos.put("Símbolos", true);
                        
                        // Activar acertijo final si ya resolvió el de las coordenadas
                        if (acertijosResueltos.get("Coordenadas")) {
                            System.out.println("\n¡Has descifrado todos los acertijos! Combinando esta información con");
                            System.out.println("las coordenadas del mapa, ahora sabes que el tesoro está enterrado");
                            System.out.println("en un punto específico de la playa, a 20 pasos desde la roca partida,");
                            System.out.println("en dirección a donde se pone el sol.");
                            System.out.println("Deberías ir allí y excavar en el lugar exacto.");
                            acertijosResueltos.put("Final", true);
                        }
                    }
                    break;
                case 4:
                    ubicacionActual = "ruinas";
                    System.out.println("Sales de la cueva hacia las ruinas...");
                    break;
                case 5:
                    ubicacionActual = "montaña";
                    System.out.println("Tomas el sendero que lleva a la montaña...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } else if (ubicacionActual.equals("tesoro")) {
            switch(opcion) {
                case 1:
                    if (!tesoroEncontrado && tieneObjeto("Llave Oxidada")) {
                        System.out.println("Introduces la llave oxidada en el candado del cofre. Gira con dificultad,");
                        System.out.println("pero finalmente escuchas un satisfactorio 'clic'.");
                        System.out.println("\nLevantas lentamente la tapa del cofre para revelar su contenido...");
                        System.out.println("\n¡El tesoro del Capitán Arvid! Monedas de oro, joyas brillantes y piedras preciosas");
                        System.out.println("llenan el cofre hasta los bordes. También encuentras una carta sellada dirigida a ti.");
                        System.out.println("\nLa carta dice:");
                        System.out.println("'Querido Finn, si estás leyendo esto, has demostrado tener el ingenio y");
                        System.out.println("la determinación de un verdadero pirata. Este tesoro es tuyo por derecho,");
                        System.out.println("pero recuerda que la verdadera riqueza está en las aventuras vividas");
                        System.out.println("y en las historias que podrás contar. Con orgullo, tu abuelo, Capitán Arvid.'");
                        System.out.println("\n¡FELICIDADES! Has completado la búsqueda del tesoro.");
                        tesoroEncontrado = true;
                    } else if (tesoroEncontrado) {
                        System.out.println("Contemplas el tesoro con satisfacción. Las monedas de oro y joyas brillan");
                        System.out.println("a la luz que se filtra desde arriba. La carta de tu abuelo descansa sobre ellas,");
                        System.out.println("un testimonio de su astucia y de tu éxito al seguir sus pistas.");
                    }
                    break;
                case 2:
                    ubicacionActual = "cueva";
                    System.out.println("Sales de la cámara del tesoro y regresas a la cueva...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        
        return true;
    }
    
    private static boolean tieneObjeto(String objeto) {
        return objetosEncontrados.getOrDefault(objeto, false);
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
        System.out.println("Capacidad: " + itemsEnInventario + "/6");
        
        if (acertijoCoordenadasActivo || acertijoSimbolosActivo || acertijosResueltos.get("Final")) {
            System.out.println("\n--- ACERTIJOS ---");
            if (acertijoCoordenadasActivo) {
                System.out.print("Acertijo de las Coordenadas: ");
                System.out.println(acertijosResueltos.get("Coordenadas") ? "RESUELTO" : "PENDIENTE");
            }
            if (acertijoSimbolosActivo) {
                System.out.print("Acertijo de los Símbolos: ");
                System.out.println(acertijosResueltos.get("Símbolos") ? "RESUELTO" : "PENDIENTE");
            }
            if (acertijosResueltos.get("Final")) {
                System.out.println("¡Todos los acertijos resueltos! El tesoro se encuentra en la playa.");
            }
        }
    }
    
    private static void agregarAlInventario(String objeto) {
        if (itemsEnInventario < 6) {
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
    }
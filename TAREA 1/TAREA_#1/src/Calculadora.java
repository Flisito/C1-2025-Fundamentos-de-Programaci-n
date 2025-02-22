import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Calculadora {

    // Constantes para las opciones del menú
    private static final int OPCION_SUMA = 1;
    private static final int OPCION_RESTA = 2;
    private static final int OPCION_MULTIPLICACION = 3;
    private static final int OPCION_DIVISION = 4;
    private static final int OPCION_SALIR = 5;
    private static final String ERROR_OPCION = "\n******Opción no válida******";
    private static final String ERROR_NUMERO_NEGATIVO = "\nError: Los números deben ser positivos"; 
    private static final String ERROR_DIVISION_CERO = "\nNo se puede dividir por cero";
    private static final String ERROR_RESTA_NEGATIVA = "\nLa resta no puede ser negativa";
    private static final String MENSAJE_DESPEDIDA = "\nPrograma Terminado\n4";

    // Constantes para mensajes
    private static final String MENU_PROGRAMA = 
    "\n ======== CALCULADORA ========="+
    "\n |    1.    Suma              |"+
    "\n |    2.    Resta             |"+
    "\n |    3.    Multiplicación    |"+
    "\n |    4.    División          |"+
    "\n |    5.    Salir             |"+
    "\n | -> Seleccione una opción   |"+
    "\n ============================\n"+
    "\n Opción: ";

    // Configuración de entrada/salida
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    
    public static void main(String[] args) throws Exception {
        int opcionMenu;
        double numero1, numero2, resultado;
        boolean continuar = true;

        while (continuar) {
            // Mostrar menú y leer opción
            out.print(MENU_PROGRAMA);
            opcionMenu = Integer.parseInt(in.readLine());

            // Validar opción
            if (opcionMenu >= OPCION_SUMA && opcionMenu <= OPCION_SALIR) {
                if (opcionMenu == OPCION_SALIR) {
                    continuar = false;
                    out.println(MENSAJE_DESPEDIDA);
                    continue;
                }
                
                // Solicitar y validar números
                out.print("Ingrese el primer número: ");
                numero1 = Double.parseDouble(in.readLine());

                out.print("Ingrese el segundo número: ");
                numero2 = Double.parseDouble(in.readLine());

                if (numero1 < 0 || numero2 < 0) {
                    out.println(ERROR_NUMERO_NEGATIVO);
                    continue;
                }

                // Realizar operación según la opción seleccionada
                switch (opcionMenu) {
                    case OPCION_SUMA:
                        resultado = numero1 + numero2;
                        out.printf("\nLa suma es: "+resultado+"%n");
                        break;

                    case OPCION_RESTA:
                        if (numero1 < numero2) {
                            out.println(ERROR_RESTA_NEGATIVA);
                            continue;
                        }
                        resultado = numero1 - numero2;
                        out.printf("\nLa resta es: "+resultado+"%n");
                        break;

                    case OPCION_MULTIPLICACION:
                        resultado = numero1 * numero2;
                        out.printf("\nLa multiplicación es: "+resultado+"%n");
                        break;

                    case OPCION_DIVISION:
                        if (numero2 == 0) {
                            out.println(ERROR_DIVISION_CERO);
                            continue;
                        }
                        if(numero1 == 0){resultado=0;
                        }
                        else{resultado = numero1 / numero2;}
                        out.printf("\nLa división es: "+String.format("%.3f",resultado)+"%n");
                        break;
                 }
            } else {
                out.println(ERROR_OPCION);
            }

        }        
    }
}

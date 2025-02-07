import java.io.*;
//Importamos java util que nos ofrece una coleccion de clases.
public class App {
    public static void main(String[] args) {
// verificar si hay consola para poder ejecutar el programa
         Console console = System.console();
         if (console == null) {
            System.out.println("No hay consola disponible");
            return;
         }
// difinicion de variables
    int opcion = 0;
    boolean continuar = true;
    Ejercicio_1 Ej1 = new Ejercicio_1();
    Ejercicio_2 Ej2 = new Ejercicio_2();
    Ejercicio_3 Ej3 = new Ejercicio_3();
    Ejercicio_4 Ej4 = new Ejercicio_4();
    Ejercicio_5 Ej5 = new Ejercicio_5();
    Ejercicio_6 Ej6 = new Ejercicio_6();
//
    while (continuar) {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("Elija la opcion del ejercicio del 1 al 6");
        System.out.println("Opcion 7 para salir");
        System.out.print("Seleccione una opción: ");
//
        try{
                opcion = Integer.parseInt(console.readLine());

                switch(opcion)
                {
                    case 1:
                        Ej1.Ejercicio1();
                        break;
                    case 2:
                        Ej2.Ejercicio2();
                        break;
                    case 3:
                        Ej3.Ejercicio3();
                        break;
                    case 4:
                        Ej4.Ejercicio4();
                        break;
                    case 5:
                        Ej5.Ejercicio5();
                        break;
                    case 6:
                        Ej6.Ejercicio6();
                        break;
                    case 7:
                        System.out.println("Codigo finalizado");
                        continuar = false; 
                    default:
                        System.out.println("Opción no válida");
                }
            } 
                catch (NumberFormatException e){
                    System.out.println("Por favor, ingrese un número válido");
            }
        }
    }
}

/*Una sastrería quiere calcular la cantidad de tela que debe comprar 
para hacer unos vestidos. La cantidad de tela depende del número de 
personas y de cuánto se gasta por cada persona. El sastre calcula que 
por cada persona se gasta 3 metros de tela, por lo que teniendo la 
cantidad personas, calcule en un nuevo programa la cantidad de tela 
necesaria, almacene su resultado en una variable llamada metrosTela 
e imprima el resultado por pantalla. */

public class Ejercicio_2 {
    public static void Ejercicio2() {
        int numPersonas = 0;
        boolean valido = false;
        
        while (!valido) {
            try {
                System.out.print("Ingrese el número de personas: ");
                byte[] entrada = new byte[10];
                System.in.read(entrada);
                String valor = new String(entrada).trim();
                numPersonas = Integer.parseInt(valor);
                
                if (numPersonas > 0) {
                    valido = true;
                    int metrosTela = numPersonas * 3;
                    System.out.println("Para " + numPersonas + " personas, necesitará " + metrosTela + " metros de tela.");
                } else {
                    System.out.println("Error: El número de personas debe ser positivo");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido");
            }
        }
    }
}

/*Crear un programa en Java que dado un número entero positivo N, 
calcule la sumatoria de todos los números desde 1 hasta N. 
Para realizarlo almacene el resultado de la fórmula (N*(N+1))/2 
en una nueva variable llamada sumatoria e imprima su resultado 
por pantalla.*/

public class Ejercicio_1 {
    //Pedimos y validamos el nuemro positivo
    
    public static void Ejercicio1() {
        int numero = 0;
        boolean valido = false;
        
        while (!valido) {
            try {
                System.out.print("Ingrese un número positivo: ");
                byte[] entrada = new byte[10];
                System.in.read(entrada);
                String valor = new String(entrada).trim();
                numero = Integer.parseInt(valor);
                
                if (numero > 0) {
                    valido = true;
                    int sumatoria = (numero * (numero + 1)) / 2;
                    System.out.println("La sumatoria de los números desde 1 hasta " + numero + " es: " + sumatoria);
                } else {
                    System.out.println("Error: El número debe ser positivo");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido");
            }
        }
    }
}




/*Crear un nuevo programa que calcule la velocidad de un vehículo que 
viaja X distancia en T segundos utilizando la fórmula Velocidad = 
Distancia / Tiempo. Almacene el resultado en una variable llamada 
velocidad e imprima el resultado por pantalla. */

    public class Ejercicio_4 {
        public static void Ejercicio4() {
        float distancia = 0, tiempo = 0;
        
        try {
            // Pedir distancia
            System.out.print("Ingrese la distancia: ");
            byte[] entradaDistancia = new byte[10];
            System.in.read(entradaDistancia);
            distancia = Float.parseFloat(new String(entradaDistancia).trim());
            System.in.skip(System.in.available());
            
            // Pedir tiempo
            System.out.print("Ingrese el tiempo: ");
            byte[] entradaTiempo = new byte[10];
            System.in.read(entradaTiempo);
            tiempo = Float.parseFloat(new String(entradaTiempo).trim());
            
            if (tiempo > 0) {
                float velocidad = distancia / tiempo;
                System.out.println("La velocidad es: " + velocidad + " metros por segundo");
            } else {
                System.out.println("Error: El tiempo debe ser mayor que 0");
            }
            
        } catch (Exception e) {
            System.out.println("Error: Ingrese valores numéricos válidos");
        }
    }
}
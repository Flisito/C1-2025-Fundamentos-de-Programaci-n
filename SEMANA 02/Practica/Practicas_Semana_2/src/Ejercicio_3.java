/*Crear un nuevo programa en Java que lea tres cadenas distintas de 
caracteres e imprima en pantalla las 3 cadenas orden invertido.
• Ejemplo: Si la primera variable tiene el valor de “Alto”, la segunda 
“Bajo” y la tercera “Corto” imprima por pantalla: “Corto, Bajo, Alto”.
• Para leer una cadena de caracteres use: in.readLine() */

import java.io.*;
public class Ejercicio_3 {
    public static void Ejercicio3() {
        String cadena1 = "", cadena2 = "", cadena3 = "";
        
        try {
            //Leer y almacenar primera cadena
            System.out.print("Ingrese la primera cadena: ");
            byte[] entrada1 = new byte[50];
            System.in.read(entrada1);
            cadena1 = new String(entrada1).trim();
            System.in.skip(System.in.available());

            //Leer y almacenar segunda cadena
            System.out.print("Ingrese la segunda cadena: ");
            byte[] entrada2 = new byte[50];
            System.in.read(entrada2);
            cadena2 = new String(entrada2).trim();
            System.in.skip(System.in.available());

            //Leer y almacenar tercera cadena
            System.out.print("Ingrese la tercera cadena: ");
            byte[] entrada3 = new byte[50];
            System.in.read(entrada3);
            cadena3 = new String(entrada3).trim();
            
            // Imprim en orden invertido
            System.out.println(cadena3 + ", " + cadena2 + ", " + cadena1);
            
        } catch (IOException e) {
            System.out.println("Error al leer las cadenas");
        }
    }
}
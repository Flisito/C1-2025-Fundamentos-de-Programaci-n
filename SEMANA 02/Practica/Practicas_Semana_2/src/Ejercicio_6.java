/*En una campaña de vacunación en un zoológico se debe vacunar 
a todas las especies dentro del recinto. Sabiendo que hay N 
jaulas de animales, K animales por jaula y por cada animal 
se utilizan 5cc de medicamento, en un nuevo programa imprima 
la cantidad total de medicamento que será necesario durante la 
campaña. */
import java.io.*;
public class Ejercicio_6 {

    //Clase para la cantidad y contenido de jaula
    class Jaulas {
        private int animales;
        
    // Constructor
        public Jaulas(int animales) {
            this.animales = animales;
        }
        
        // Cantidad de cc por jaula
        public int ccJaula() {
            int totalCc = animales * 5;
            return totalCc;
        }
    }

    public static void Ejercicio6(){
        Ejercicio_6 llama_Clase = new Ejercicio_6();
        int totalCcJaulas = 0;
        try{
        //Preguntar por cantidad de jaulas
        System.out.println("Cantidad de jaulas del recinto");
        byte[] entradaJaula = new byte[16];
        System.in.read(entradaJaula);
        int numeroJaula = Integer.parseInt(new String(entradaJaula).trim());

        //Creamos el array para la cantidad de jaulas
        Jaulas[] jaulas = new Jaulas[numeroJaula];

        // Loop para pedir la cantidad de animales por jaula
        for(int i = 0; i < numeroJaula; i++){
            System.out.println("\nCuantos animales hay en la jaula "+ (i+1) +":");

            //Pedir cantidad de animales por jaula
            byte[] entradaAnimales = new byte[16];
            System.in.read(entradaAnimales);
            int cantidadAnimales = Integer.parseInt(new String(entradaAnimales).trim());

            System.in.skip(System.in.available());

            //Aplicamos la clase Jaulas
            jaulas[i] = llama_Clase.new Jaulas(cantidadAnimales);
        }

        // Resultados
        System.out.println("Se necesitan la siguente cantidad de cc por jaula: \n");
        for(int i = 0; i < jaulas.length; i++) 
            {
                System.out.println("Para la jaula "+(i+1)+" se necesitan "+jaulas[i+1]+" cc\n");
                //totalCcJaulas=totalCcJaulas+jaulas[(i+1)]
            }
        } catch (Exception e){
            System.out.println("Error: Ingrese valores numéricos válidos");
        }
    }
}

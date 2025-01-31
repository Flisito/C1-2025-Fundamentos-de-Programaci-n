//
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hola, Me llamo Cristian Carrion! \n\n");
        System.out.println("Temas vistos de la segunda clase \n\n");
        System.out.println("Paradigmas, Forma de ejecucion, nivel de abstraccion.\n");
        System.out.println("Lenguaje de bajo nivel y alto nivel. \n");
        System.out.println("Lenguaje de bajo nivel, mucho poder pero tneer mucho cuidado.\n");
        System.out.println("Lenguaje de alto nivel, son mas faciles de programar pero tiene menos poder.\n");
        System.out.println("Que es un algotimo: secuencia de pasos para resolver un problema, tiene que ser finitos.\n");
        System.out.println("Pseudo codigo: algoritmo con leguaje natural, entendible facilmente por el humano.\n");
        System.out.println("Diagrama de flujo: Cual va a ser el camino para generar la solucion.\n");
        System.out.println("Estructuras secuenciales, Condicionales e iterativas.\n");
        System.out.println("Proceso abstraccion: Tomar una situacion y ponerla en un plano que sea mas simple de entender.\n");
        System.out.println("Procesos para crear un programa estructurado. \n");
        System.out.println(" \n");
//ejercicio
        Scanner scanner = new Scanner(System.in);
        Animacion animacion =new Animacion();
        //
        System.out.print("\nIntroduce valor 1: ");
        int valor1 =scanner.nextInt();
        System.out.print("\nIntroduce valor 2: ");
        int valor2 =scanner.nextInt();
        System.out.print("\nIntroduce valor 3: ");
        int valor3 =scanner.nextInt();
        System.out.print("\n\n"); 
        //
        animacion.start("Calculando la multiplicacion de los 3 numeros");
        try{Thread.sleep(5000);}
        catch (InterruptedException e){e.printStackTrace();}
        animacion.stop();
        //4
        int valorfinal=valor1*valor2*valor3;
        System.out.println("Calculo Completado! El valor es "+valorfinal);
    }
}

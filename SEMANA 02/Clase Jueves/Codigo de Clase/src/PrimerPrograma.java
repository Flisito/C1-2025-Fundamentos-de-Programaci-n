/*
Proceso para crear un programa estructurado
Tipo de Variables, int-float-double-char-boolean-string
Como declarar variable, tipo + valor + ;
Recomendacion, inicializar las variables al inicio
Estandares, camelCase, PascaCase. No utilizar caracteres especiales, espacios en blanco o numeros.
camelCase: Primera palabra minuscula, segunda mayuscula, ej. primerNombre
PascalCase: Ambas con la primera letra mayuscula, PrimerNombre

*/

import java.util.Scanner;
import java.io.*;
public class PrimerPrograma {
    public static void main(String[] args) throws Exception {
//Problema 1, Leer 3 numero, calcular la suma, la mulplicacion y el promedio.
static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
static PrintStream out = System.out;
Scanner scanner = new Scanner(System.in);
//
int valor1, valor2, valor3;
float suma, producto, promedio;
//
        out.println("\nIntroduce valor 1: ");
        valor1 =scanner.nextInt();
        //
        out.println("\nIntroduce valor 2: ");
        valor2 =scanner.nextInt();
        //
        out.println("\nIntroduce valor 3: ");
        valor3 =scanner.nextInt();
//
    suma=valor1+valor2+valor3;
    multiplicacion=valor1*valor2*valor3;
    promedio=(valor1+valor2+valor3)/3;
//
        System.out.print("\n\nEl valor sumado es:"+suma+"\n\nel valor multiplicado es: "+multiplicacion+"\n\nEl Promedio es: "+promedio+"\n");
    }
}

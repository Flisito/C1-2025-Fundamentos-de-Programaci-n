/*
Que es una equivalencia
Particion valida e invalida
    Valida: Valida esta listo para resolverse
    Invalida: No esta preparada para recibir el valor, ej poner una edad negativa, la cual no deberia ser negativa, *se puede resolver con try catch*

    Analizar el valor limite

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double Salario=0;
        double Excedente=0;
        double Impuesto=0;
        System.out.println("Ingrese el salario a analizar: \n");
        Salario = Double.parseDouble(br.readLine());
        if(Salario>1000000 && Salario<=1500000){
            Excedente=Salario-1000000;
            Impuesto=Excedente*0.10;
            Salario=Salario-Impuesto;
        }
        if(Salario>1500000){
            Excedente=Salario-1500000;
            Impuesto=Excedente*0.15;
            Salario=Salario-Impuesto;
        }
        System.out.println("El salario final es: "+Salario);      
    }
} 
    
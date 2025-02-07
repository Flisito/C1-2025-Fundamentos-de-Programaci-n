import  java.io.*;
import java.util.*;
public class App {
    class calcularParImpar{
        //Identificar si el numero es par o impar, pedir numero al usuario
        try{
        double numero=0;
        String resultado="";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //
        System.out.println("Ingrese numero a analizar: \n");
        numero = Double.parseDouble(br.readLine());
        //
        if(numero%2==0){
            resultado="Numero es par";
        }
            else{
                resultado="Numero es impar";
            }
        System.out.println(resultado);    
    }catch (Exception e) {
        System.out.println("Ingresar un numero valido");
    }
}
    public static void main(String[] args) throws Exception {
        App app = new App ();        
        NumeroPar numeroPar = app.new NumeroPar();
        numeroPar.calcularParImpar();
    }
}

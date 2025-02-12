/*
 Estrucutras de seleccion anidadas
 Preguntas compuestas dentro de if

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PracticaClase1 {
    public static void main(String[] args) throws Exception {
        //
        double montoCompra=0, montoDescuento=0, precioFinal=0, porcentajeDescuento=0;
        //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //
        System.out.println("\nIngrese el monto de la compra: ");
        montoCompra = Double.parseDouble(br.readLine()); 
        //Preguntas de comparacion
        if(montoCompra<=5000){
            porcentajeDescuento=0;
        }
        else if(montoCompra>5000 && montoCompra<=10000){
            porcentajeDescuento=0.05;
        }
        else if(montoCompra>10000 && montoCompra<=25000){
            porcentajeDescuento=0.11;
        }
        else if(montoCompra>25000 && montoCompra<=50000){
            porcentajeDescuento=0.18;
        }
        else{porcentajeDescuento=0.25;}
        //Calculo de resultados
        montoDescuento=montoCompra*porcentajeDescuento;
        precioFinal=montoCompra-montoDescuento;
        //Resultado
        System.out.println("\n\n El monto de la compra es "+montoCompra+", el porcentaje de descuento es "+ Math.round(porcentajeDescuento*100) +"%, \n \n El precio final es "+precioFinal+"\n\n");
    }
}
import java.io.*;
public class CalculoSalario {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double Salario=0;
        double Excedente=0;
        double Impuesto=0;
        System.out.println("Ingrese el salario a analizar: \n");
        Salario = Double.parseDouble(br.readLine());
        if(Salario>1000000){
            Excedente=Salario-1000000;
            Impuesto=Excedente*0.10;
            Salario=Salario-Impuesto;
        }
        System.out.println("El salario final es: "+Salario);       
    }
}



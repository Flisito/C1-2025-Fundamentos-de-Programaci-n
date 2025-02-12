import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PracticaClase2 {
    public static void main(String[] args) throws Exception {
        //Variables
        String idioma; 
        int edad, experiencia;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Pedir datos
        System.out.println("\nIngrese la sigla del idioma : ");
        idioma = (br.readLine());
        System.out.println("\nIngrese su edad: ");
        edad = Integer.parseInt(br.readLine());
        System.out.println("\nIngrese su experiencia: ");
        experiencia = Integer.parseInt(br.readLine());
        //condicion compleja
        if((idioma=="i" || idioma=="p")&& edad>18 && experiencia>=1){
            System.out.println("\n\nClasifica");
        }
        else{System.out.println("\nNoClasifica\n\n");}

    }
}
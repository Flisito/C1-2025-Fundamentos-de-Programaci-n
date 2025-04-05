import java.util.Scanner;
public class App {
    //Constante para el menu
    public static final int OPCION_INGRESAR_PALABRA = 1;
    public static final int OPCION_SALIR = 2;
    public static final String MenuPrincipal=
        "\n=== MENÚ PRINCIPAL ===\n"+
        "1. Ingresar palabra para analizar\n"+
        "2. Salir\n"+
        "Seleccione una opcion: ";
    public static void main(String[] args) throws Exception {  
        
        Scanner scanner = new Scanner(System.in);

        //Solicitar la hilera de carateres
        System.out.println("Ingrese un texto: ");
        String hilera = scanner.nextLine();

        //Entrar en ciclo para buscar letras
        char letra;
        int contador=0;
        while (true){
            System.out.println("Ingrese la letra a buscar");
            String entrada = scanner.nextLine();

            //Verificar que se ingreso solo un caracter
            if (entrada.length()==1){

                //Extraer la primera letras del string
                letra = entrada.charAt(0);            

                for (int i=0; i < hilera.length(); i++){

                    //Verifica que la letra sea igual a la letra a comparar
                    if(letra==hilera.charAt(i)){
                    contador++;}
                }

                //Mostrar el resultado
                System.out.println("La letra "+ letra + " aparece " + contador + " veces");
            }
            else System.out.print("Ingresar solo 1 caracter");
        }
    }
}

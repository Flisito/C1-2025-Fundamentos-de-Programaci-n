import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class claseMartes {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    public static void main(String[] args) throws Exception {
        
        // Variables 
        String talla = "";
        Integer edad = 0;
        String cicloVida = "";
        String tipoAlimento = "";
        String cantidadComida = "";
        String frecuenciaComida = "";

        // Entrada de datos
        out.println("Ingrese la talla del perro (Grande/Mediano/Pequeño): ");
        talla = reader.readLine().toLowerCase();
            
        out.print("Ingrese la edad del perro en años: ");
        edad = Integer.parseInt(reader.readLine());

        // ciclo de vida y tipo de alimento
        if (edad >= 0 && edad <= 1) {
            cicloVida = "cachorro";
            tipoAlimento = "Small bites";
        } else if (edad > 1 && edad <= 8) {
            cicloVida = "adulto";
            tipoAlimento = "Regular";
        } else {
            cicloVida = "mayor";
            tipoAlimento = "Soft";
        }

        switch (cicloVida) {
            case "cachorro":
                switch (talla) {
                    case "pequeño":
                        cantidadComida = "0.5 tazas";
                        frecuenciaComida = "en 3 horas de comida";
                        break;
                    case "mediano":
                        cantidadComida = "1 taza";
                        frecuenciaComida = "en 3 horas de comida";
                        break;
                    case "grande":
                        cantidadComida = "1.5 tazas";
                        frecuenciaComida = "en 3 horas de comida";
                        break;
                    default:
                        out.println("Talla no válida");
                }
                break;
                    
                case "adulto":
                    switch (talla) {
                        case "pequeño":
                            cantidadComida = "1 taza";
                            frecuenciaComida = "en 2 horas de comida";
                            break;
                        case "mediano":
                            cantidadComida = "1.5 tazas";
                            frecuenciaComida = "en 2 horas de comida";
                            break;
                        case "grande":
                            cantidadComida = "2 tazas";
                            frecuenciaComida = "en 2 horas de comida";
                            break;
                        default:
                            out.println("Talla no válida");
                    }
                    break;
                                        
                case "mayor":
                    switch (talla) {
                        case "pequeño":
                            cantidadComida = "0.5 taza";
                            frecuenciaComida = "una vez al día";
                            break;
                        case "mediano":
                            cantidadComida = "1 taza";
                            frecuenciaComida = "una vez al día";
                            break;
                        case "grande":
                            cantidadComida = "1.5 tazas";
                            frecuenciaComida = "una vez al día";
                            break;
                        default:
                            out.println("Talla no válida");
                    }
                    break;
                    
                default:
                    out.println("Talla no válida");
            }
            
            // Mostrar recomendación
            out.println("\nRecomendación de alimentación:");
            out.printf("Su perro es un "+cicloVida+", por lo que debe suministrarle "+cantidadComida+" de alimento "+tipoAlimento+", dividido "+frecuenciaComida+" \n\n");
                
    }
}

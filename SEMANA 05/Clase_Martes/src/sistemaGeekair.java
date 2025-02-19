import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class sistemaGeekair {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    public static void main(String[] args) throws Exception {
        int cantidadTiquetesRegulares = 0, cantidadTiquetesPreferenciales=0, cantidadPrimeraClase=0, ruta=0;
        
        String Origen = "SJO", destino = "";

        int distancia, precioRegular, precioPreferencial, precioPrimeraClase;
        int ingresosPreferencial, ingresosPrimeraClase, ingresosRegular, ingresosTotales, pasajerosTotales;
        
        out.println("Bienvenido al sistema de reportes de GeekAir \n");
        out.println("1- BFS - Belfast");
        out.println("2- LHR - Reading");
        out.println("3- MSO - Orlando");
        out.println("4- SJC - California");
        out.println("5- HND - Tokio");
        
        out.print("Digite la ruta de vuelo que desea calcular");
        ruta = Integer.parseInt(br.readLine());

    }
}

public class BuscarMenor {
    public static void main(String[] args) {
        int[] arreglo = {20, 5, 89, 12, 2, 500, 12, 7, 15, 30};
        
        int menor = arreglo[0];
        for (int i = 1; i < arreglo.length; i++) {
            if (arreglo[i] < menor) {
                menor = arreglo[i];
            }
        }
        
        System.out.println("El numero menor es: " + menor);
    }
}
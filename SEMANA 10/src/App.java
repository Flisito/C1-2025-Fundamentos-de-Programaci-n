public class App {
    public static int extraeNum(String hilera) {
        // Definir variables 
        int numero = 0;
        int i = 0;
        String hileraTemp = "";
        char c = '0';
        boolean bandera = true;
        
        // Loop principal que se ejecuta mientras bandera sea true
        while (bandera == true) {
            c = hilera.charAt(i);
            
            // Verificar si c es un número
            if (Character.isDigit(c)) {
                // Si c es un número, concatenarlo a hileraTemp
                hileraTemp = hileraTemp + c;
            } else {
                // Si c no es un número, cambiar bandera a false
                bandera = false;
            }
            i = i + 1;
            // Verificar si hemos llegado al final de la hilera
            if (i >= hilera.length()) {
                bandera = false;
            }
        }
        
        // Convertir hileraTemp a número y retornarlo
        if (!hileraTemp.isEmpty()) {
            numero = Integer.parseInt(hileraTemp);
        }
        
        return numero;
    }
    
    public static void main(String[] args) {
        
        String prueba = "123abc";
        int resultado = extraeNum(prueba);
        System.out.println("Número extraído: " + resultado);

    }
}

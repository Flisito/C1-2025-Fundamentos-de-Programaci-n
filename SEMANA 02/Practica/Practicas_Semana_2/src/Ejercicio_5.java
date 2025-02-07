/*Una empresa pequeña quiere crear un nuevo programa para calcular el 
salario de dos empleados. Para ambos el salario se obtiene al multiplicar
la cantidad de horas de trabajo por el precio de cada hora. Es importante
que al segundo empleado se le deduce un impuesto del 15%. Imprima por 
pantalla el salario de ambos empleados y la suma de ambos montos. */

public class Ejercicio_5 {

// Clase Empleado
class Empleado {
    private float horas;
    private float precioHora;
    private boolean tieneImpuesto;
    
// Constructor
    public Empleado(float horas, float precioHora, boolean tieneImpuesto) {
        this.horas = horas;
        this.precioHora = precioHora;
        this.tieneImpuesto = tieneImpuesto;
    }
    
    // Método para calcular salario
    public float calcularSalario() {
        float salario = horas * precioHora;
        if (tieneImpuesto) {
            salario = salario - (salario * 0.15f); // Descuento del 15%
        }
        return salario;
    }
}

    public static void Ejercicio5() {
        boolean boolImpuesto = true;
        Ejercicio_5 llama_Clase = new Ejercicio_5();

        try {
            // Pedir número de empleados
            System.out.print("¿Cuántos empleados desea analizar?: ");
            byte[] entradaNum = new byte[10];
            System.in.read(entradaNum);
            int numEmpleados = Integer.parseInt(new String(entradaNum).trim());
            
            System.in.skip(System.in.available());
            
            // Array para guardar los empleados
            Empleado[] empleados = new Empleado[numEmpleados];
            
            
            // Loop para pedir datos para cada empleado
            for(int i = 0; i < numEmpleados; i++) {
                System.out.println("\nDatos del empleado " + (i+1) + ":");
                
                //Pedir Horas
                System.out.print("Ingrese las horas trabajadas: ");
                byte[] entradaHoras = new byte[10];
                System.in.read(entradaHoras);
                float horas = Float.parseFloat(new String(entradaHoras).trim());
                
                System.in.skip(System.in.available());
                
                //Pedir precio por hora
                System.out.print("Ingrese el precio por hora: ");
                byte[] entradaPrecio = new byte[10];
                System.in.read(entradaPrecio);
                float precio = Float.parseFloat(new String(entradaPrecio).trim());
                
                System.in.skip(System.in.available());
                
                //Preguntar si se deduce el impuesto
                System.out.print("¿Tiene impuesto? (1: Sí, 0: No): ");
                byte[] entradaImpuesto = new byte[10];
                System.in.read(entradaImpuesto);
                int IntImpuesto = Integer.parseInt(new String(entradaImpuesto).trim());
                if(IntImpuesto==0){
                    boolImpuesto = false;
                }
                
                
                System.in.skip(System.in.available());
                
                // Crear y guardar el empleado
                empleados[i] = llama_Clase.new Empleado(horas, precio, boolImpuesto);
            }
            
            // Resultados
            System.out.println("\nResultados:");
            for(int i = 0; i < empleados.length; i++) 
            {
                float salario = empleados[i].calcularSalario();
                System.out.println("Salario del empleado " + (i+1) + ": $" + salario + "\n");
            }

            
            
        } catch (Exception e) {
            System.out.println("Error: Ingrese valores numéricos válidos");
        }
    }
}
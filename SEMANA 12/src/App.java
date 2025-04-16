public class App {
    public static void GeneraDatos(int V[]){
        int i=0;
        while (i < V.length){
            int nuevoNumero = 1 + (int)(Math.random()*20);
            boolean repetido = false;
            for (int j = 0; j < i; j++){
                if (V[j] == nuevoNumero){
                    repetido = true;
                    break;
                }
            }
            if(!repetido){
                V[i] = nuevoNumero;
                i++;
            }
        }
    }   
    
    public static void Imprimir(int[] V )throws Exception{
        int i;
        for (i = 0; i < V.length ; i++) {
            System.out.print(" " + V[i]);
        }
        System.out.println("\n");
        
    }
    
    public static int BuscarMenor(int[] V )throws Exception {
            
        int menor = V[0];
        for (int i = 1; i < V.length; i++) {
            if (V[i] < menor) {
                menor = V[i];
            }
        }
        //System.out.println("El numero menor es: " + menor);
        return menor;
    }
    
    public static void OrdenarNum(int[] V )throws Exception {    
        int Ord[]=new int [V.length];
        for (int i = 0; i < Ord.length; i++){
            int menor;
            menor=BuscarMenor(V);
            Ord[i]=menor;
            for(int j = 0; j < Ord.length; j++){
                if(V[j]==menor){V[j]=9999;}
            }

            
        }

        for (int i = 0; i < Ord.length; i++){
            if(i == Ord.length-1){
                System.out.print(Ord[i]);
            }else{
                System.out.print(Ord[i]+" , ");
            }
        }
    }  

    public static void main(String[] args) throws Exception {
        int arregloNumeros[] = new int[10];
        GeneraDatos(arregloNumeros);
        Imprimir(arregloNumeros);
        
        int numMenor=0;
        numMenor=BuscarMenor(arregloNumeros);
        System.out.println("El numero menor del arreglo es: "+numMenor+"\n");

        OrdenarNum(arregloNumeros);
    }
}
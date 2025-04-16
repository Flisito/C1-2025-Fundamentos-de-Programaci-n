public class Ordenar {

    public static void main(String[] args){

        int[] arreglo = {20,5,89,12,2,500,13};

        for (int i = 0; i < arreglo.length -1; i++){
            for(int j = 0; j < arreglo.length -1; j++){
                if (arreglo[j] > arreglo[j+1]){
                    int temp = arreglo[j];
                    arreglo[j]=arreglo[j + 1];
                    arreglo[j + 1] =temp;
                    
                }
            }
        }

        for (int i = 0; i < arreglo.length; i++){
            if(i == arreglo.length-1){
                System.out.print(arreglo[i]);
            }else{
                System.out.print(arreglo[i]+" , ");
            }

        }
    }
}

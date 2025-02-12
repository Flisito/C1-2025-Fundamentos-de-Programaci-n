public class Animacion {
        private static final char[] SimbolosProgresivos = {'|', '/', '-', '\\'};
        private int index = 0;
        private boolean corriendo = false;
        private Thread hiloanimado;
    
        public void start(String mensaje) 
        {
            if (corriendo) return;
            corriendo = true;
            hiloanimado = new Thread(()->{
                try {
                    while (corriendo) {
                        System.out.print("\r");
                        System.out.print(mensaje + " " + SimbolosProgresivos[index]);
                        
                        index = (index + 1) % SimbolosProgresivos.length;
                        
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.print("\r");
                }
            });
            hiloanimado.start();
        }
    
        public void stop() {
            corriendo = false;
            if (hiloanimado != null) {
                try {
                    hiloanimado.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println();
        }
    
}

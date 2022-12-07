// A interface Runnable é implementada para que eu possa rodar isso em threads separadas.
public class Filosofo implements Runnable {
    // Os garfos dos dois lados desse filósofo.
    private Object garfoEsquerdo;
    private Object garfoDireito;

    public Filosofo(Object garfoEsquerdo, Object garfoDireito) {
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    private void agir(String acao) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + acao);
        Thread.sleep(((int) (Math.random() * 100))); // Suspende a Thread por um tempo aleatório.
    }
    
    // Para simular um filósofo pegando um garfo é necessário travá-lo para que duas Threads Filósofos 
    // não o obtenham ao mesmo tempo.

    // Para fazer isso nós usamos a palavra chave synchronized para adquirir o monitor interno do objeto garfo
    // e impedir outras Threads de fazer o mesmo.
    @Override
    public void run() {
        try {
            while (true) {
                // Pensando
                agir(System.nanoTime() + ": Pensando.");
                synchronized (garfoEsquerdo) {
                    agir(
                      System.nanoTime() 
                        + ": Pega o Garfo Esquerdo.");
                    synchronized (garfoDireito) {
                        // Comendo.
                        agir(
                          System.nanoTime() 
                            + ": Pega o Garfo Direito - comendo."); 
                        
                        agir(
                          System.nanoTime() 
                            + ": Larga o Garfo Direito.");
                    }
                    // Vamos pensar de novo.
                    agir(
                      System.nanoTime() 
                        + ": Larga o Garfo Esquerdo. Volta a pensar.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

}
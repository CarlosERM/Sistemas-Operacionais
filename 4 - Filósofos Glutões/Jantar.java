public class Jantar {

    public static void main(String[] args) throws Exception {

        Filosofo[] filosofos = new Filosofo[5];
        Object[] garfos = new Object[filosofos.length];
        
        for (int i = 0; i < garfos.length; i++) {
            // Cada garfo é modelado como um objeto genérico.
            // Vai ter o mesmo tanto de filósofos e garfos.
            garfos[i] = new Object();
        }

        for (int i = 0; i < filosofos.length; i++) {
            // Cada filósofo vai receber o seu garfo da direita e o seu garfo da direita
            // que ele tenta desbloquear utilizando a palavra-chave synchronized
            Object garfoEsquerdo = garfos[i];
            Object garfoDireito = garfos[(i + 1) % garfos.length];
            
            if (i == filosofos.length - 1) {
                // O último filosófo pega o garfo da direita primeiro.
                filosofos[i] = new Filosofo(garfoDireito, garfoEsquerdo); 
            } else {
                filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito);
            }
            
            Thread t = new Thread(filosofos[i], "Filósofo " + (i + 1));
            t.start();
        }
    }
}
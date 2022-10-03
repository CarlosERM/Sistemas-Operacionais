import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Trabalhar com Threads.
        // O semáforo mutex simboliza que o buffer está livre ou não. 
        // Buffer é uma array. 
        // Semáforo Full, Empty e Mutex são variáveis. 
        // O produtor coloca algo no buffer. 
        // O consumidor retira algo do buffer. 
        Random gerador = new Random();
        int i = 0; 

        Processo p1 = new Processo("p1", "W", "P");
        Processo p2 = new Processo("p2", "W", "P");
        Processo[] processos = {p1, p2};
        int semaforoFull = 0;
        int semaforoEmpty = 3;
        int semaforoMutex = 1;
        ArrayList<String> buffer = new ArrayList<String>(); // Create an ArrayList object


        while(i < 10) {
            Processo processo = processos[gerador.nextInt(2)];
            
            if(processo.nome == "p1") {
                System.out.println("TRANSIÇÃO DE ESTADO P => E");
                processo.estado = "E";

                if(semaforoFull < 4) {
                    if(semaforoEmpty != 0) {
                        // Down no semáforo empty;
                        semaforoEmpty--;
                        System.out.println("DOWN SEMÁFORO EMPTY " + semaforoEmpty);
                    }
                    if(semaforoMutex != 0) {
                        System.out.println("DOWN SEMÁFORO MUTEX " + semaforoEmpty);
                        semaforoMutex--;
                    }
                    
                    System.out.println("DADO ADICIONADO");
                    buffer.add("D" + gerador.nextInt(100));
                    semaforoMutex++;
                    processo.estado = "P";
                    System.out.println("TRANSIÇÃO DE ESTADO E => P");
                }
            }
            if(processo.nome == "p2") {
                processo.estado = "E";
                if(semaforoFull != 0) {
                    // Down no semáforo full;
                    semaforoFull--;
                    System.out.println("DOWN SEMÁFORO FULL " + semaforoFull);
                    if(semaforoMutex != 0) {
                        System.out.println("DOWN SEMÁFORO MUTEX " + semaforoEmpty);
                        semaforoMutex--;
                    }
                    
                    String dado = buffer.remove(buffer.size() - 1);
                    System.out.println("DADO " + dado + " RETIRADO");
                    semaforoMutex++;
                    processo.estado = "P";
                    System.out.println("TRANSIÇÃO DE ESTADO E => P");
                }
            }

            i++;
        }

    }
}
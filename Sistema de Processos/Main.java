import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.SwingPropertyChangeSupport;

class Main {
    // IDENTIFICADOR DE PROCESSO (PID)
    // TEMPO DE PROCESSAMENTO (TP)
    // CONTADOR DE PROGRAMA (CP)
    // ESTADO DO PROCESSO (EP)
    // NÚMERO DE VEZES QUE REALIZOU UMA OPERAÇÃO DE E/S (NES)
    // NÚMERO DE VEZES QUE USOU A CPU (N_CPU)
    public static void main(String[] args) {
        Processo[] processos = new Processo[10];
        int tempoExecucao[] = { 10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000 };
        ArrayList<Processo> tabelaProcessos = new ArrayList<Processo>();
        Random random = new Random();
        int j = 0;
        for (int i : tempoExecucao) {
            processos[j] = new Processo(j, 0, 0, null, 0, 0, i);
            j++;
        }
        while(true) {
            for (Processo processo : processos) {
                int entradaSaida = 0;
                System.out.println(processo.EP + " >>>>> EXECUTANDO");
                processo.EP = "EXECUTANDO";
                tabelaProcessos.add(processo);

                if(processo.TP < processo.limiteTP) {
                    processo.TP += 1000;
                    processo.CP = processo.TP + 1;
                } 
                for( int h = 0; h < 1000; h++) {
                    if(processo.TP < processo.limiteTP) {
                        processo.TP +=1;
                        processo.CP = processo.TP + 1;
                    } else {
                        processo.EP = "PRONTO";
                        break;
                    }
                    if(random.nextInt(100) + 1 <= 5) { 
                        if(processo.EP == "BLOQUEADO") {
                            if(random.nextInt(100) + 1 <=30) {
                                System.out.println(processo.EP + " >>>>> PRONTO");
                                processo.EP = "PRONTO";
                                tabelaProcessos.add(processo);
                            }
                        }
                        entradaSaida++;
                        operacaoEntradaSaida(processo);
                        tabelaProcessos.add(processo);

                    }
                }
              if(entradaSaida != 0) {
                System.out.println(processo.EP + " >>>>> PRONTO");
                processo.EP = "PRONTO";
                tabelaProcessos.add(processo);
              }
              System.out.println("PID |  TP  |  CP  |     EP     | NES | N_CPU | ");
              System.out.println(processo.PID + "   | " + processo.TP + " | " + processo.CP + " | " + processo.EP + " | " + 
              processo.NES + " | " + processo.N_CPU + " | ");
            }
            if(verificador(processos)) {
                break;
            }
        }
        System.out.println(
        );
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("PID |   TP   |  CP  |     EP     | NES | N_CPU | ");       
        System.out.println("-----------------------------------------------");
        for (Processo processo : processos) {
            System.out.println(processo.PID + "   |  " + processo.TP + "  | " + 
            processo.CP + " | " + processo.EP + " | " + processo.NES + " | " +
            processo.N_CPU + " | ");
        }
        
    }
    static boolean verificador(Processo processos[]) {
        boolean decide = true;
        for (Processo processo : processos) {
            if(processo.TP < processo.limiteTP) {
                decide = false;
                break;
            }
            
        }
        return decide;
    }
    static boolean operacaoEntradaSaida(Processo processo) {
        processo.NES += 1;
        System.out.println(processo.EP + " >>>>> BLOQUEADO");
        processo.EP = "BLOQUEADO";
        return true;
    }
}
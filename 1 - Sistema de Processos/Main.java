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
        // Criar uma aplicação em (Qualquer Linguagem),  para simular a 
        // mudança de estados dos processos num SO. Nesse sistema operacional,
        // estão em execução 10 processos que são colocados para a execução 
        // em ordem do seu PID (Identificador de processo que vai de 0 a 9)
        // com tempo de duração distintos. 
        for (int i : tempoExecucao) {
            processos[j] = new Processo(j, 0, 0, null, 0, 0, i);
            j++;
        }
        while(true) {
            for (Processo processo : processos) {
                int entradaSaida = 0;
                // Quando um processo realizar uma Troca de Contexto, 
                // Todos seus dados deverão ser impressos, além da informação 
                // para qual estado o processo está indo: Ex.: EXECUTANDO >>>
                // PRONTO ou EXECUTANDO >>>> BLOQUEADO. 
                System.out.println(processo.EP + " >>>>> EXECUTANDO");
                processo.EP = "EXECUTANDO";
                // Logo, sempre que um processo sofrer uma Troca
                // de Contexto, esses dados devem ser  guardados na
                // Tabela de Processos.
                tabelaProcessos.add(processo);

                if(processo.TP < processo.limiteTP) {
                    // Toda vez que o processo retornar para o estado de EXECUTANDO, 
                    // ele recebe do SO a quantidade completa de quantum (1000 ciclos).
                    processo.TP += 1000;
                    processo.CP = processo.TP + 1;
                } 
                // O SO definiu um Quantum de execução para cada processo de 1000 ciclos.
                for( int h = 0; h < 1000; h++) {

                    if(processo.TP < processo.limiteTP) {
                        // O parâmetro TP da nossa simulação vai armazenar
                        // o total de ciclos já executados pelo processo.
                        processo.TP +=1;
                        // O parâmetro CP vai sempre indicar qual é a próxima instrução
                        // que o processo vai executar, assim por questões de simplificação,
                        // o CP será definido como CP = TP + 1, o que simula processos 
                        // sequenciais sem laços de repetição ou chamadas de desvio de função.
                        processo.CP = processo.TP + 1;
                    } else {
                        System.out.println(processo.EP + " >>>>> PRONTO");
                        processo.EP = "PRONTO";
                        break;
                    }
                    // Em cada ciclo o processo tem 5% de chances de
                    // realizar uma operação de E/S, ficando então bloqueado.
                    if(random.nextInt(100) + 1 <= 5) { 
                        if(processo.EP == "BLOQUEADO") {

                            // Uma vez que ele realizar uma operação de E/S, 
                            // na sua próxima vez, ele terá 30% de chances para
                            // sair do estado de Bloqueado e ir para o estado de Pronto. 
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
                // Caso o quantum do processo termine sem ele realizar uma
                // operação de E/S (término dos 1000 ciclos), o processo 
                // sofrerá uma Troca de Contexto, modificando seu estado de
                // executando  para Pronto.
              if(entradaSaida != 0) {
                System.out.println(processo.EP + " >>>>> PRONTO");
                processo.EP = "PRONTO";
                tabelaProcessos.add(processo);
              }

              // Somente um processo por vez pode estar no estado de EXECUTANDO.
              if(processo.EP == "EXECUTANDO") {
                System.out.println(processo.EP + " >>>>> BLOQUEADO");

                processo.EP = "BLOQUEADO";
              }
            System.out.printf("%-5s", "PID");
            System.out.printf("%10s", "TP");
            System.out.printf("%10s", "CP");
            System.out.printf("%12s", "EP");
            System.out.printf("%5s", "NES");
            System.out.printf("%7s%n", "N_CPU");
            System.out.printf("%-5s", processo.PID);
            System.out.printf("%10s", processo.TP);
            System.out.printf("%10s", processo.CP);
            System.out.printf("%12s", processo.EP);
            System.out.printf("%5s", processo.NES);
            System.out.printf("%7s%n", processo.N_CPU);
            }
            if(verificador(processos)) {
                break;
            }
        }
        // Quando um processo termina sua execução ele deve imprimir todos os seus parâmetros.
        System.out.println();
        System.out.println();
        System.out.printf("%-5s", "PID");
        System.out.printf("%10s", "TP");
        System.out.printf("%10s", "CP");
        System.out.printf("%12s", "EP");
        System.out.printf("%5s", "NES");
        System.out.printf("%7s%n", "N_CPU");
        for (Processo processo : processos) {
            // System.out.println(processo.PID + "   |  " + processo.TP + "  | " + 
            // processo.CP + " | " + processo.EP + " | " + processo.NES + " | " +
            // processo.N_CPU + " | ");
            System.out.printf("%-5s", processo.PID);
            System.out.printf("%10s", processo.TP);
            System.out.printf("%10s", processo.CP);
            System.out.printf("%12s", processo.EP);
            System.out.printf("%5s", processo.NES);
            System.out.printf("%7s%n", processo.N_CPU);

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
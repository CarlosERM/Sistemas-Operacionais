import java.util.Random;
class Main {
    public static void main(String[] args) {
        Random gerador = new Random();
        int [] classes = new int[10];
        int substituto = -1;
        // --------------------|--------------|---------|----------------|---------------------|
        // Número de Página (N)| Instrução (I)| Dado (D)| Bit de Acesso R| Bit de Modificação M|
        // --------------------|--------------|---------|----------------|---------------------|
        int [][] molduraPagina = new int[10][5];
        int [][] paginaDisco = new int[100][5];

        // Preenchimento de Página em Disco.

        // --> A coluna N terá os números de 0 a 99, sequencialmente
        // ( linha 0 -> N = 0, linha 1 -> N = 1, linha 2 -> N = 2...)
        for(int l = 0; l < 100; l++) {
            paginaDisco[l][0] = l;
        }

        // --> A coluna I terá os números de 1 a 100, sequencialmente
        // ( linha 0 -> I = 1, linha 1 -> I = 2, linha 2 -> I = 3...)
        for(int l = 0; l < 100; l++) {
            paginaDisco[l][1] = l + 1;
        } 

        // --> A coluna D terá números de 1 a 50, sorteados ale aleatoriamente.
        for(int l = 0; l < 100; l++) {
            paginaDisco[l][2] = gerador.nextInt(50) + 1;
        }

        // --> A coluna R = 0.
        for(int l = 0; l < 100; l++) {
            paginaDisco[l][3] = 0;
        }

        // --> A coluna M = 0.
        for(int l = 0; l < 100; l++) {
            paginaDisco[l][4] = 0;
        }
        
        // --> Para cada linha da matriz 10x5, Sorteie um número de 0 a 99. 
        // Copie dos dados para a linha da matriz 10x5 a partir da matriz 100x5,
        // usando como índice para a linha o número que foi sorteado. Ou seja,
        // serão sorteadas 10 linhas da matriz 100x5 e copiadas para a matriz 10x5.

        for (int l = 0; l < 10; l++) {
            int n = gerador.nextInt(100);
            molduraPagina[l][0] = paginaDisco[n][0];
            molduraPagina[l][1] = paginaDisco[n][1];
            molduraPagina[l][2] = paginaDisco[n][2];
            molduraPagina[l][3] = paginaDisco[n][3];
            molduraPagina[l][4] = paginaDisco[n][4];
        }

        // Imprimir Tabela.
        System.out.println("PÁGINAS EM DISCO.");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%15s", "Número de Página (N)");
        System.out.printf("%15s", "Instrução (I)");
        System.out.printf("%11s", "Dado (D)");
        System.out.printf("%22s", "Bit de Acesso R");
        System.out.printf("%22s%n", "Bit de Modificação M");
        System.out.println("-------------------------------------------------------------------------------------------");
        
        for(int l = 0; l <100; l++) {
            System.out.printf("%15s", paginaDisco[l][0]);
            System.out.printf("%15s", paginaDisco[l][1]);
            System.out.printf("%11s", paginaDisco[l][2]);
            System.out.printf("%22s", paginaDisco[l][3]);
            System.out.printf("%22s%n", paginaDisco[l][4]);
        }

        System.out.println();
        System.out.println("MOLDURAS DE PÁGINAS.");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%15s", "Número de Página (N)");
        System.out.printf("%15s", "Instrução (I)");
        System.out.printf("%11s", "Dado (D)");
        System.out.printf("%22s", "Bit de Acesso R");
        System.out.printf("%22s%n", "Bit de Modificação M");
        System.out.println("-------------------------------------------------------------------------------------------");
        
        for(int l = 0; l < 10; l++) {
            System.out.printf("%15s", molduraPagina[l][0]);
            System.out.printf("%15s", molduraPagina[l][1]);
            System.out.printf("%11s", molduraPagina[l][2]);
            System.out.printf("%22s", molduraPagina[l][3]);
            System.out.printf("%22s%n", molduraPagina[l][4]);
        }

        // Posteriormente para a execução do simulador,  será sorteado um número de 1 a 100,
        // referente a instrução (campo I) que está sendo requisitada para a execução na CPU.
        // Será feito uma pesquisa no campo I da matriz 10x5 para verificar se o valor da 
        // instrução está carregado na memória RAM. 

        int n = gerador.nextInt(100) + 1;

        for(int l = 0; l < 10; l++) {
            // Será feito uma pesquisa no campo I da matriz 10x5 para 
            // verificar se o valor da instrução está carregado na memória RAM. 
            if(molduraPagina[l][1] == n) {
                // 1) O bit de acesso R vai receber o valor 1.
                molduraPagina[l][3] = 1;
                // 2) A página terá 30% de chance de sofrer uma modificação. Ou seja, 
                // caso a probabilidade seja atingida, duas ações serão realizadas:
                if((gerador.nextInt(100) + 1) < 30) {
                    // 2.1) O campo Dado (D) será atualizado da seguinte maneira: D = D + 1;
                    molduraPagina[l][2] = molduraPagina[l][2] + 1;
                    // 2.2) O campo Modificado será atualizado: M = 1;
                    molduraPagina[l][4] = 1;
                }
            } else {
                //NRU
                for(int z = 1; z  <= 500; z++) {
                    // if(z % 10 == 0) {
                    //     for (int a = 0; a < 10; a++) {
                    //         molduraPagina[a][3] = 0;
                    //     }
                    // }
                    for (int a = 0; a < 10; a++) {
            // --------------------|--------------|---------|----------------|---------------------|
            // Número de Página (N)| Instrução (I)| Dado (D)| Bit de Acesso R| Bit de Modificação M|
            // --------------------|--------------|---------|----------------|---------------------|
                        // Classe 0: Não Acessada (R=0) e não Modificada (M=0)
                        if(molduraPagina[a][3] == 0 && molduraPagina[a][4] == 0) {
                            classes[a] = 0;
                        }
                        // Classe 1: Não Acessada (R=0) e Modificada (M=1)
                        else if(molduraPagina[a][3] == 0 && molduraPagina[a][4] == 1) {
                            classes[a] = 1;
                        }
                        // Classe 2: Acessada (R=1) e não Modificada (M=0)
                        else if(molduraPagina[a][3] == 1 && molduraPagina[a][4] == 0) {
                            classes[a] = 2;
                        }
                        // Classe 3: Acessada (R=1) e Modificada (M=1)
                        else if(molduraPagina[a][3] == 1 && molduraPagina[a][4] == 1) {
                            classes[a] = 3;
                        }
                    }

                    for(int i = 0; i < 10; i++) {
                        if(substituto < classes[i]) {
                            substituto = i;
                        }
                    }
                    for (int y = 0; y < 100; y++) {
                        if(paginaDisco[y][1] == n) {
                            molduraPagina[substituto][0] = paginaDisco[y][0];
                            molduraPagina[substituto][1] = paginaDisco[y][1];
                            molduraPagina[substituto][2] = paginaDisco[y][2];
                            molduraPagina[substituto][3] = paginaDisco[y][3];
                        }
                    }

                }

            }
        }
        // Imprimir Tabela.
        System.out.println();
        System.out.println();
        System.out.println("Impressão final.");
        System.out.println();
        System.out.println();

        System.out.println("PÁGINAS EM DISCO.");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%15s", "Número de Página (N)");
        System.out.printf("%15s", "Instrução (I)");
        System.out.printf("%11s", "Dado (D)");
        System.out.printf("%22s", "Bit de Acesso R");
        System.out.printf("%22s%n", "Bit de Modificação M");
        System.out.println("-------------------------------------------------------------------------------------------");

        for(int l = 0; l <100; l++) {
            System.out.printf("%15s", paginaDisco[l][0]);
            System.out.printf("%15s", paginaDisco[l][1]);
            System.out.printf("%11s", paginaDisco[l][2]);
            System.out.printf("%22s", paginaDisco[l][3]);
            System.out.printf("%22s%n", paginaDisco[l][4]);
        }

        System.out.println();
        System.out.println("MOLDURAS DE PÁGINAS.");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%15s", "Número de Página (N)");
        System.out.printf("%15s", "Instrução (I)");
        System.out.printf("%11s", "Dado (D)");
        System.out.printf("%22s", "Bit de Acesso R");
        System.out.printf("%22s%n", "Bit de Modificação M");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(int l = 0; l < 10; l++) {
            System.out.printf("%15s", molduraPagina[l][0]);
            System.out.printf("%15s", molduraPagina[l][1]);
            System.out.printf("%11s", molduraPagina[l][2]);
            System.out.printf("%22s", molduraPagina[l][3]);
            System.out.printf("%22s%n", molduraPagina[l][4]);
        }

        for (int cs : classes) {
            System.out.println(cs);
        }
    }
}
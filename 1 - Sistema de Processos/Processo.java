public class Processo {
    // IDENTIFICADOR DE PROCESSO (PID)
    // TEMPO DE PROCESSAMENTO (TP)
    // CONTADOR DE PROGRAMA (CP)
    // ESTADO DO PROCESSO (EP)
    // NÚMERO DE VEZES QUE REALIZOU UMA OPERAÇÃO DE E/S (NES)
    // NÚMERO DE VEZES QUE USOU A CPU (N_CPU)
    int PID;
    int TP;
    int CP;
    String EP;
    int NES;
    int N_CPU;
    int limiteTP;
    Processo(int PID, int TP, int CP, String EP, int NES, int N_CPU, int limiteTP) {
        this.PID = PID;
        this.TP = TP;
        this.CP = CP;
        this.EP = EP;
        this.NES = NES;
        this.N_CPU = N_CPU;  
        this.limiteTP = limiteTP;
    }
}

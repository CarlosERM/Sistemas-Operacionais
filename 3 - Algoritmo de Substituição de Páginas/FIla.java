// Java program for array
// implementation of queue
 
// A class to represent a queue
class Fila {
    int frente, tras, tamanho;
    int capacidade;
    int array[][];
    int[] vazio = {-1};
 
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        frente = this.tamanho = 0;
        tras = capacidade - 1;
        array = new int[this.capacidade][];
    }

    // A fila está cheia quando o tamanho se torna igual a capacidade.
    boolean taCheio(Fila fila){
        return (fila.tamanho == fila.capacidade);
    }
 
    // A fila está vazia quando tamanho é 0.
    boolean taVazio(Fila fila) {
        return (fila.tamanho == 0);
    }
 
    // Um método que adiciona um item na fila.
    // Ele munda tras e tamanho.
    void adicionar(int[] item) {
        if (taCheio(this))
            return;
        this.tras = (this.tras + 1) % this.capacidade;
        this.array[this.tras] = item;
        this.tamanho = this.tamanho + 1;
        System.out.println(item[0] + " entrou na fila.");
    }
 
    // Método para remover um item da fila.
    // Muda frente e tamanho.
    int[] remover() {
        if (taVazio(this))
            return vazio;
 
        int[] item = this.array[this.frente];
        this.frente = (this.frente + 1) % this.capacidade;
        this.tamanho = this.tamanho - 1;
        return item;
    }
 
    // Método para pegar a frente da fila.
    int[] frente() {
        if (taVazio(this))
            return vazio;
 
        return this.array[this.frente];
    }
 
    // Método para pegar a parte de trás da fila.
    int[] tras() {
        if (taVazio(this))
            return vazio;
 
        return this.array[this.tras];
    }
}
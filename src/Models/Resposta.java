import java.io.Serializable;

class Resposta implements Serializable {

    private int valor;
    private int validez;

    Resposta(int valor, int validez) {
        this.valor = valor;
        this.validez = validez;
    }

    int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }
}

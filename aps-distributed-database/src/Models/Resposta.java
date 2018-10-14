package Models;

import java.io.Serializable;

public class Resposta implements Serializable {

	private static final long serialVersionUID = 1L;
    private int valor;
    private int validez;

    public Resposta(int valor, int validez) {
        this.valor = valor;
        this.validez = validez;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }
}

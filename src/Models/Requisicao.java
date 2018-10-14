package Models;

import java.io.Serializable;

public class Requisicao implements Serializable {

	private static final long serialVersionUID = 1L;
	private int operando1;
    private char operador;
    private int operando2;

    public Requisicao(int operando1, char operador, int operando2) {
        this.operando1 = operando1;
        this.operador = operador;
        this.operando2 = operando2;
    }

    public int getOperando1() {
        return operando1;
    }

    public void setOperando1(int operando1) {
        this.operando1 = operando1;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public int getOperando2() {
        return operando2;
    }

    public void setOperando2(int operando2) {
        this.operando2 = operando2;
    }
}

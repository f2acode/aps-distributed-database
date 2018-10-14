import java.io.*;
import java.net.*;

public class Cliente {

    private static Socket socket;

    private Cliente() {
        try {
            socket = new Socket("localhost", 9600);
        } // fase de conexao
        catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]){
        Requisicao requisicao = new Requisicao(24, '*', 6);
        new Cliente();
        Conexao.send(socket, requisicao);
        Resposta resposta = (Resposta) Conexao.receive(socket);                    // fase de requisicao
        if (resposta.getValidez() == 0) {
            System.out.println("Resposta Inválida");
        } else {
            System.out.println("Resposta Válida.\nValor do resultado: " + resposta.getValor());
        }
        try {
            socket.close();                               // fase de desconexao
        } catch (IOException e) {
            System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
        }
    }
}
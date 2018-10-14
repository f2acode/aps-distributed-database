package Client;

import Models.Request;
import Models.Response;
import Server.Connection;

import java.io.*;
import java.net.*;

public class Client {

    private static Socket socket;

    private Client() {
        try {
            socket = new Socket("localhost", 9600);
        }
        catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]){
        Request requisicao = new Request();
        new Client();
        Connection.send(socket, requisicao);
        Response response = (Response) Connection.receive(socket);
        
        if (response.getValidez() == 0) {
            System.out.println("Resposta Inválida");
        } else {
            System.out.println("Resposta Válida.\nValor do resultado: " + "response.getValor()");
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
        }
    }
}
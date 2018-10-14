package Server;

import Models.Requisicao;
import Models.Resposta;
import Services.PeopleService;

import java.net.*;

public class Servidor {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private Servidor() {

        try {
            server_socket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new Servidor();
        while(true) {
	        if (connect()) {
	            Requisicao requisicao = (Requisicao) Conexao.receive(client_socket);
	            PeopleService peopleService = new PeopleService();
	            
	            Conexao.send(client_socket, peopleService.calcular(requisicao));
	            try {
	                client_socket.close();
	                server_socket.close();
	            }
	            catch (Exception e) {
	                System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
	            }
	        }
        }
    }

    private static boolean connect() {
        boolean ret;
        try {
            client_socket = server_socket.accept();
            ret = true;
        } catch (Exception e) {
            System.out.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
package Server;

import Models.*;

import java.net.*;

public class Server {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private Server() {

        try {
            server_socket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new Server();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(client_socket);
                PeopleService peopleService = new PeopleService();
                Person person = new Person();

	            switch(request.getType()) {
	            	case GET:
                        Connection.send(client_socket, peopleService.read(request.getPerson().getId()));
                        break;
	            	case POST:
	            		Connection.send(client_socket, peopleService.create(request.getPerson()));
		            	break;
	            	case PUT:
	            		Connection.send(client_socket, peopleService.update(request.getPerson()));
		            	break;
	            	case DELETE:
		            	break;
	            }
	            
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
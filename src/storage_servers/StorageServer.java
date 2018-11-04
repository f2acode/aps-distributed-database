package storage_servers;

import java.net.*;

import helpers.Connection;

import models.*;

public class StorageServer {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private StorageServer() {
        try {
            server_socket = new ServerSocket(9601);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new StorageServer();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(client_socket);
	            StorageService storageService = new StorageService();
                Response response = null;
	            switch(request.getType()) {
	            	case GET:
	            		response = storageService.read(request.getPerson().getId());
                        break;
	            	case POST:
	            		response = storageService.create(request.getPerson());
		            	break;
	            	case PUT:
	            		response = storageService.update(request.getPerson());
		            	break;
	            	case DELETE:
	            		response = storageService.delete(request.getPerson().getId());
                        break;
                    default:
                    	response = new Response(null, Status.WRONG_TYPE);
	            }

                Connection.send(client_socket, response);

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
            System.out.println("Nao fez conexao " + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
package storage_servers;

import java.net.*;

import helpers.Connection;

import models.*;

public class StorageServer {

    private static ServerSocket controllerStorageServer_connectionSocket;
    private static Socket controllerStorageServer_socket;

    private StorageServer() {
        try {
            controllerStorageServer_connectionSocket = new ServerSocket(9601);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new StorageServer();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(controllerStorageServer_socket);
	            StorageService storageService = new StorageService();
                Response response;
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

                Connection.send(controllerStorageServer_socket, response);

                try {
	                controllerStorageServer_socket.close();
	                //controllerStorageServer_connectionSocket.close();
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
            controllerStorageServer_socket = controllerStorageServer_connectionSocket.accept();
            ret = true;
        } catch (Exception e) {
            System.out.println("Nao fez conexao " + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
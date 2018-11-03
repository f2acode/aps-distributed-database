package storage_servers;

import java.net.*;
import helpers.Connection;

import models.*;

public class StorageServer {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private StorageServer() {
        /*try {
            server_socket = new ServerSocket(9601);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }*/
    }

    public static void main(String args[]) {
    	FileHelper fileHelper = new FileHelper();
    	fileHelper.Write();
        /*new StorageServer();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(client_socket);
                
	            PeopleService peopleService = new PeopleService();
                Person person = null;

	            switch(request.getType()) {
	            	case GET:
                        person = peopleService.read(request.getPerson().getId());
                        break;
	            	case POST:
                        person = peopleService.create(request.getPerson());
		            	break;
	            	case PUT:
                        person = peopleService.update(request.getPerson());
		            	break;
	            	case DELETE:
                        peopleService.delete(request.getPerson().getId());
                        break;

                Connection.send(client_socket, person);

                try {
	                client_socket.close();
	                server_socket.close();
	            }
	            catch (Exception e) {
	                System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
	            }
	        }
        }*/
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
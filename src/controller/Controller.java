package controller;

import java.net.*;

import helpers.Connection;
import models.*;

public class Controller {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private Controller() {
        try {
            server_socket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new Controller();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(client_socket);
                PeopleService peopleService = new PeopleService();
                Response response = null;

	            switch(request.getType()) {
	            	case GET:
	            		response = peopleService.read(request.getPerson().getId());
                        break;
	            	case POST:
	            		response = peopleService.create(request.getPerson());
		            	break;
	            	case PUT:
	            		response = peopleService.update(request.getPerson());
		            	break;
	            	case DELETE:
	            		response = peopleService.delete(request.getPerson().getId());
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
            System.out.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
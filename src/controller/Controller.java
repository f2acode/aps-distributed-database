package controller;

import java.net.*;

import helpers.Connection;
import models.*;

public class Controller {

    private static ServerSocket clientController_connectionSocket;
    private static Socket clientController_socket;

    private Controller() {
        try {
            clientController_connectionSocket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new Controller();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(clientController_socket);
                PeopleService peopleService = new PeopleService();
                Response response;

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

                Connection.send(clientController_socket, response);

                try {
	                clientController_socket.close();
	                clientController_connectionSocket.close();
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
            clientController_socket = clientController_connectionSocket.accept();
            ret = true;
        } catch (Exception e) {
            System.out.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
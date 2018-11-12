package controller;

import java.io.*;
import java.net.*;

import helpers.Connection;
import models.*;

public class Controller {

    private static FileWriter fr;
    private static String rootPath;
    private static ServerSocket clientController_connectionSocket;
    private static Socket clientController_socket;

    private Controller() {
        rootPath = System.getProperty("user.home") + "/DistributedDatabases/";
        try {
            clientController_connectionSocket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) throws IOException {
        new Controller();
        while(true) {
	        if (connect()) {
	            Request request = (Request) Connection.receive(clientController_socket);
	            if (request.getType().equals(Type.POST)) {
                    request.getPerson().setId(getNextId(rootPath));
                }
	            PeopleService peopleService = new PeopleService(request.getPerson().getId());
                Response response;

	            switch(request.getType()) {
	            	case GET:
	            		response = peopleService.read(request.getPerson().getId());
                        break;
	            	case POST:
	            		response = peopleService.create1(request.getPerson());
                        if (!response.getStatus().equals(peopleService.create2(request.getPerson()).getStatus())) {
                            response = new Response(null, Status.INTERNAL_SERVER_ERROR);
                        }
		            	break;
	            	case PUT:
                        response = peopleService.update1(request.getPerson());
                        if (!response.getStatus().equals(peopleService.update2(request.getPerson()).getStatus())) {
                            response = new Response(null, Status.INTERNAL_SERVER_ERROR);
                        }
                        break;
	            	case DELETE:
	            		response = peopleService.delete1(request.getPerson().getId());
                        if (!response.getStatus().equals(peopleService.delete2(request.getPerson().getId()).getStatus())) {
                            response = new Response(null, Status.INTERNAL_SERVER_ERROR);
                        }
                        break;
                    default:
                    	response = new Response(null, Status.WRONG_TYPE);
	            }

                Connection.send(clientController_socket, response);
	        }
        }
    }

    private static long getNextId(String path) throws IOException {
        long nextId = 0L;
        String currentStrLine;
        BufferedReader br = null;

        File file = new File(path + "sequence.txt");

        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((currentStrLine = br.readLine()) != null) {
                    nextId = Integer.parseInt(currentStrLine) + 1;
                }
            } catch(Exception e) {

            } finally {
                br.close();
            }
        } else {
            return 0;
        }

        String data = String.valueOf(nextId);
        file.getParentFile().mkdir();

        if(file.exists()) {
            try {
                file.createNewFile();
                fr = new FileWriter(file);
                fr.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return 0;
        }

        return nextId;
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
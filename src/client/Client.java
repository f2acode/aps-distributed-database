package client;

import models.Request;
import models.Response;
import models.Status;
import models.Type;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import helpers.Connection;

public class Client {

    private static Socket socket;
    private static final String escolhaOperacao =
            "\t1 - Consultar um registro existente;\n" +
            "\t2 - Inserir um novo registro;\n" +
            "\t3 - Alterar um registro existente;\n" +
            "\t4 - Deletar um registro existente;\n" +
            "\t5 - Sair\n";


    private Client() {
        try {
            socket = new Socket("localhost", 9600);
        }
        catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]){
        System.out.println("Bem-vindo ao nosso serviço de gerenciamento de pessoas em banco de dados distribuido!\n" +
                "O que gostaria de fazer hoje?\n" +
                escolhaOperacao);

        Scanner in = new Scanner(System.in);

//        new Client();
        Type operacaoType;
        do {
            String operacao = in.next();
            operacaoType = null;
            switch (operacao) {
                case "1":
                    System.out.println("\n----- Você escolheu consultar registro! -----\n");
                    operacaoType = Type.GET;
                    break;
                case "2":
                    System.out.println("\n------ Você escolheu inserir registro! ------\n");
                    operacaoType = Type.POST;
                    break;
                case "3":
                    System.out.println("\n------ Você escolheu alterar registro! ------\n");
                    operacaoType = Type.PUT;
                    break;
                case "4":
                    System.out.println("\n------ Você escolheu deletar registro! ------\n");
                    operacaoType = Type.DELETE;
                    break;
                case "5":
                    System.out.println("\nObrigado por utilizar nossos serviços!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("\nTipo de requisição inválida, tente novamente!\n");
                    operacaoType = null;
            }
        } while (operacaoType == null);
//        Connection.send(socket, request);
//        Response response = (Response) Connection.receive(socket);
//
//        if (response.getStatus().equals(Status.WRONG_TYPE)) {
//            System.out.println("Tipo de requisição inválida!");
//        } else {
//            System.out.println("Resposta Válida.\nValor do resultado: " + "response.getValor()");
//        }
//        try {
//            socket.close();
//        } catch (IOException e) {
//            System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
//        }
        System.exit(0);
    }
}
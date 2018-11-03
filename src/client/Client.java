package client;

import models.Request;
import models.Response;
import models.Status;
import models.Type;
import models.Person;
import models.Gender;


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

        new Client();
        Person person;
        Request request;
        Type operacaoType;
        Status status = null;
        do {
            String operacao = in.next();
            operacaoType = null;
            switch (operacao) {
                case "1":
                    System.out.println("\n----- Você escolheu consultar registro! -----\n");
                    operacaoType = Type.GET;

                    String id;
                    do {
                        status = null;
                        System.out.println("Informe o ID do registro a ser consultado:\n");

                        id = in.next();
                        if (id.matches(".*\\w+.*")) {
                            status = Status.ID_WRONG_FORMAT;
                            System.out.println("Formato do ID inválido, tente novamente!\n");
                        }
                    } while (status != null);

                    person = new Person(Long.parseLong(id));
                    request = new Request(operacaoType, person);
                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
                    break;
                case "2":
                    System.out.println("\n------ Você escolheu inserir registro! ------\n");
                    operacaoType = Type.POST;

                    String nome;
                    String idade;
                    String genero;
                    Gender gender = null;
                    String endereco;
                    status = null;

                    System.out.println("Informe os dados da pessoa a ser cadastrada:\n");

                    do {
                        System.out.println("Nome: ");
                        nome = in.next();


                        if (nome.matches(".*\\d+.*")) {
                            status = Status.NOT_A_PERSON;
                            System.out.println("Formato de nome inválido, tente novamente!\n");
                        }
                    } while (status != null);

                    do {
                        System.out.println("\nIdade: ");
                        idade = in.next();

                        if (idade.matches(".*\\w+.*")) {
                            status = Status.NOT_A_PERSON;
                            System.out.println("Formato de idade inválida, tente novamente!\n");
                        }
                    } while (status != null);

                    do {
                        System.out.println("\nGênero: " +
                                "\n\t1 - Masculino" +
                                "\n\t2 - Feminino");
                        genero = in.next();

                        if (!(genero.equals("1") || genero.equals("2"))) {
                            status = Status.NOT_A_PERSON;
                            System.out.println("Formato de gênero inválido, tente novamente!\n");
                        } else {
                            if (genero.equals("1")) {
                                gender = Gender.MALE;
                            } else {
                                gender = Gender.FEMALE;
                            }
                        }
                    } while (status != null);

                    System.out.println("\nEndereço: ");
                    endereco = in.next();

                    request = new Request(operacaoType, new Person(nome, Integer.getInteger(idade), gender, endereco));
                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
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
                    status = Status.WRONG_TYPE;
            }
        } while (status != Status.VALID);

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
        }
        System.exit(0);
    }
}
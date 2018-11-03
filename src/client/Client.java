package client;

import models.Request;
import models.Status;
import models.Type;
import models.Person;
import models.Gender;


import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Person person;

        long id;
        String name;
        int age;
        Gender gender;
        String address;

        Request request;
        Type type;
        Status status = null;
        do {
            String operacao = in.next();
            Pattern pattern = null;
            Matcher matcher = null;
            switch (operacao) {
                case "1":
                    System.out.println("\n----- Você escolheu consultar registro! -----\n");
                    type = Type.GET;

                    //obter dados de entrada
                    id = getId(in, pattern, matcher, "Informe o ID do registro a ser consultado:\n");

                    person = new Person(id);
                    request = new Request(type, person);
//                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
                    break;
                case "2":
                    System.out.println("\n------ Você escolheu inserir registro! ------\n");
                    type = Type.POST;

                    //obter dados de entrada
                    System.out.println("Informe os dados da pessoa a ser cadastrada:\n");
                    name = getName(in, pattern, matcher);
                    age = getAge(in, pattern, matcher);
                    gender = getGender(in);
                    address = getAddress(in);

                    request = new Request(type, new Person(name, age, gender, address));
//                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
                    break;
                case "3":
                    System.out.println("\n------ Você escolheu alterar registro! ------\n");
                    type = Type.PUT;

                    //obter dados de entrada
                    System.out.println("Informe os dados da pessoa a ser alterada:\n");
                    id = getId(in, pattern, matcher, "ID :\n");
                    name = getName(in, pattern, matcher);
                    age = getAge(in, pattern, matcher);
                    gender = getGender(in);
                    address = getAddress(in);

                    request = new Request(type, new Person(id ,name, age, gender, address));
//                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
                    break;
                case "4":
                    System.out.println("\n------ Você escolheu deletar registro! ------\n");
                    type = Type.DELETE;

                    //obter dados de entrada
                    id = getId(in, pattern, matcher, "Informe o ID do registro a ser deletado:\n");

                    request = new Request(type, new Person(id));
//                    Connection.send(socket, request);
                    //                    Response response = (Response) Connection.receive(socket);
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

    private static long getId(Scanner in, Pattern pattern, Matcher matcher, String message) {
        Status status;
        String id;
        do {
            status = null;
            System.out.println(message);
            id = in.next();

            pattern = Pattern.compile("[a-zA-Z]+.*|.*[a-zA-Z]+");
            matcher = pattern.matcher(id);
            if (matcher.matches() || id.trim().isEmpty()) {
                status = Status.ID_WRONG_FORMAT;
                System.out.println("Formato do ID inválido, tente novamente!\n");
            }
        } while (status != null);
        return Long.parseLong(id);
    }

    private static String getName(Scanner in, Pattern pattern, Matcher matcher) {
        Status status;
        String nome;
        do {
            status = null;
            System.out.println("Nome: ");
            nome = in.next();

            pattern = Pattern.compile(".*\\d.*");
            matcher = pattern.matcher(nome);
            if (matcher.matches() || nome.trim().isEmpty()) {
                status = Status.NOT_A_PERSON;
                System.out.println("Formato de nome inválido, tente novamente!\n");
            }
        } while (status != null);
        return nome;
    }

    private static Integer getAge(Scanner in, Pattern pattern, Matcher matcher) {
        Status status;
        String idade;
        do {
            status = null;
            System.out.println("\nIdade: ");
            idade = in.next();

            pattern = Pattern.compile("[a-zA-Z]+.*|.*[a-zA-Z]+");
            matcher = pattern.matcher(idade);
            if (matcher.matches() || idade.trim().isEmpty()) {
                status = Status.NOT_A_PERSON;
                System.out.println("Formato de idade inválida, tente novamente!\n");
            }
        } while (status != null);
        return Integer.parseInt(idade);
    }

    private static Gender getGender(Scanner in) {
        Status status;
        String genero;
        do {
            status = null;
            System.out.println("\nGênero: " +
                    "\n\t1 - Masculino" +
                    "\n\t2 - Feminino");
            genero = in.next();

            if (!(genero.equals("1") || genero.equals("2")) || genero.trim().isEmpty()) {
                status = Status.NOT_A_PERSON;
                System.out.println("Formato de gênero inválido, tente novamente!\n");
            }
        } while (status != null);
        return (genero.equals("1")) ? Gender.MALE : Gender.FEMALE;

    }

    private static String getAddress(Scanner in) {
        System.out.println("\nEndereço: ");
        return in.next();
    }
}
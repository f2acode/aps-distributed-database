package Server;


import java.net.*;
import java.io.*;

public class Connection {

    public static void send(Socket socket, Object dados) {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(dados);
        } catch (Exception e) {
            System.out.println("Excecao no OutputStream");
        }
    }

    public static Object receive(Socket socket) {
        ObjectInputStream in;
        Object obj=null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            obj = in.readObject();
        } catch (Exception e) {
            System.out.println("Excecao no InputStream: " + e);
        }
        return obj;
    }
}
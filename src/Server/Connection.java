package Server;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Connection {

    public static void send(Socket socket, Object dados, Enum<?> type) {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(dados);
            out.writeObject(type);
        } catch (Exception e) {
            System.out.println("Excecao no OutputStream");
        }
    }

    public static List<Object> receive(Socket socket) {
        ObjectInputStream in;
        Object obj=null;
        Object type=null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            obj = in.readObject();
            type = in.readObject();
        } catch (Exception e) {
            System.out.println("Excecao no InputStream: " + e);
        }

        return Arrays.asList(obj, type);
    }
}
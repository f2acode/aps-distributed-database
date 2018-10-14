import java.net.*;

public class Servidor {

    private static ServerSocket server_socket;
    private static Socket client_socket;

    private Servidor() {

        try {
            server_socket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        new Servidor();
        if (connect()) {
            Requisicao requisicao = (Requisicao) Conexao.receive(client_socket);

            Conexao.send(client_socket, calcular(requisicao));
            try {
                client_socket.close();
                server_socket.close();
            } // desconexao // desconexao
            catch (Exception e) {
                System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
            }
        }
    }

    private static Resposta calcular(Requisicao requisicao) {
        int op1 = requisicao.getOperando1();
        int op2 = requisicao.getOperando2();
        switch (requisicao.getOperador()) {
            case '+':
                return new Resposta(op1 + op2, 1);
            case '-':
                return new Resposta(op1 - op2, 1);
            case '*':
                return new Resposta(op1 * op2, 1);
            case '/':
                if (op2 == 0) {
                    return new Resposta(0, 0);
                } else {
                    return new Resposta(op1 / op2, 1);
                }
            default:
                return new Resposta(0, 0);
        }
    }

    private static boolean connect() {
        boolean ret;
        try {
            client_socket = server_socket.accept();              // fase de conexão
            ret = true;
        } catch (Exception e) {
            System.out.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
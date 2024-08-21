import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class test {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2831);
        System.out.println("Server is listening on port 2831 ...");

        while(true){
            Socket client = server.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            ClientHandler1 clientHandler1 = new ClientHandler1(client);
            new Thread(clientHandler1).start();
        }
    }
}

class  ClientHandler1 implements Runnable{
    private Socket client;
    public ClientHandler1(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try{
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.write(65);
            dos.close();
            client.close();
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            throw new RuntimeException("Client error: " + e.getMessage(), e);
        }
    }
}


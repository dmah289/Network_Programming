package TCP;

import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        // Tạo 1 ServerSocket trên port 2831 (> 1024)
        ServerSocket server = new ServerSocket(2831);
        System.out.println("Server is listening on port 2831...");

        while (true) {
            // Chấp nhận kết nối từ client
            Socket client = server.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());

            // Tạo 1 luồng mới để xử lý kết nối với client
            ClientHandler clientHandler = new ClientHandler(client);
            new Thread(clientHandler).start();
        }
    }
}

// Thực thi Runnable để chạy trong 1 luồng mới
class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            OutputStream out = new DataOutputStream(client.getOutputStream());
            out.write(65); // Gửi ký tự 'A' tới máy khách
            out.close();
            client.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            throw new RuntimeException("Client handler error: " + e.getMessage(), e);
        }
    }
}

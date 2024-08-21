package TCP;

import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2831);   // Tạo 1 ServerSocket trên port 2831 (> 1024)
        System.out.println("Server is listening on port 2831...");

        while (true) {
            Socket client = server.accept();    // Chấp nhận kết nối từ client
            System.out.println(client);

            ClientHandler clientHandler = new ClientHandler(client);    // Tạo 1 luồng mới để xử lý kết nối với client
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
            // Ghi vào lồng ra 2 số nguyên
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeInt(28);
            dos.writeInt(31);

            // Không nên đóng outputstream sớm do input và output đều liên kết với 1 socket
            dos.flush();    // Có thể đẩy dữ liệu đi luôn mà không đóng output

            // Đọc từ input tổng trả về từ client
            DataInputStream dis = new DataInputStream(client.getInputStream());
            int sum = dis.readInt();
            System.out.println("Sum received: " + sum);

            dos.close();
            dis.close();
            client.close();

            System.out.println("Client disconnected.");
        } catch (IOException e) {
            throw new RuntimeException("Client handler error: " + e.getMessage(), e);
        }
    }
}

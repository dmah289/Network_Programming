package TCP;

import java.io.*;
import java.net.*;

public class TCP_Client {
    public static void main(String[] args) throws IOException {
        // Kết nối đến server trên port 2831
        Socket socket = new Socket("localhost", 2831);
        System.out.println("Connected to the server: " + socket);

        // Nhận hai số nguyên từ server
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int num1 = dis.readInt();
        int num2 = dis.readInt();
        System.out.println("Received numbers from server: " + num1 + ", " + num2);

        // Tính tổng và trả kết quả về server
        int sum = num1 + num2;
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(sum);
        dos.flush();

        // Đóng luồng và socket
        dos.close();
        dis.close();
        socket.close();
    }
}

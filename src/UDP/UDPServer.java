package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(1107);
        System.out.println("UDP Server is running...");
        while (true) {
            byte[] buffer = new byte[1024];

            // Receive packet
            DatagramPacket dpReceive = new DatagramPacket(buffer, buffer.length);
            server.receive(dpReceive);
            String receivedStr = new String(dpReceive.getData()).trim();
            System.out.println("Received string: " + receivedStr);

            // Send packet
            byte[] data = new byte[1024];
            data = new StringBuilder(receivedStr).reverse().toString().getBytes();
            DatagramPacket dpResponse = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
            server.send(dpResponse);
            System.out.println("Sent reverse string success!");
        }
    }
}

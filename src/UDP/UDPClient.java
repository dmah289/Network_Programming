package UDP;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        // No connection, just create an object for communication
        DatagramSocket client = new DatagramSocket();

        // Send packet. Can send without server
        byte[] data = new byte[1024];
        data = "Hello from UDP client".getBytes();
        DatagramPacket dpSend = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 1107);
        client.send(dpSend);
        System.out.println("Client send success");

        // Receive packet
        byte[] buffer = new byte[1024];
        DatagramPacket dpResponse = new DatagramPacket(buffer, buffer.length);
        client.receive(dpResponse);
        String reversedStr = new String(dpResponse.getData()).trim();
        System.out.println("Reversed string: " + reversedStr);
    }
}

package NIC_Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Multicast_Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket server = new DatagramSocket();
        System.out.println("Server starts sending multicast ...");
        int i = 0;

        while(true){
            String strSend = "Datapacket from server: " + i;
            byte[] buf = strSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("224.2.2.3"), 2831);
            server.send(sendPacket);
            System.out.println("Message : " + i++);
            Thread.sleep(1000);
        }
    }
}

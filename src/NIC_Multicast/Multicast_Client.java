package NIC_Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Multicast_Client {
    public static void main(String[] args) throws IOException {
        MulticastSocket client = new MulticastSocket(2831);
        client.joinGroup(InetAddress.getByName("224.2.2.3"));
        while(true){
            byte[] buf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            client.receive(receivePacket);
            System.out.println(new String(receivePacket.getData()).trim());
        }
    }
}

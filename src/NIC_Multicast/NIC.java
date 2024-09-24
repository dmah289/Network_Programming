package NIC_Multicast;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NIC {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
        while(nifs.hasMoreElements()){
            NetworkInterface nif = nifs.nextElement();
            if(nif.isUp()) System.out.println(nif);
        }
    }
}

package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CalClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Calculator cal = (Calculator) Naming.lookup("rmi://localhost/cal");
        int sum = cal.add(10, 20);
        System.out.println("sum (from server): " + sum);
    }
}

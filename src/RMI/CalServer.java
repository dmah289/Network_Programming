package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        CalculatorImpl cal = new CalculatorImpl();
        Naming.rebind("cal", cal);
        System.out.println("RMI server is listening...");
    }
}

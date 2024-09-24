package RMI;

import java.rmi.*;

public interface Calculator extends Remote {
    public int add(int a, int b) throws RemoteException;
}


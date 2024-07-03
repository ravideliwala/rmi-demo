package ravi.rmi.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    void startGeneratingNumbers() throws RemoteException;
}

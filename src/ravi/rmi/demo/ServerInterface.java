package ravi.rmi.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
    void registerClient(ClientInterface client) throws RemoteException;
    void addNumber(int number) throws RemoteException;
    boolean shouldGenerateNumbers() throws RemoteException;
    int getTotal() throws RemoteException;
    List<Integer> getNumList() throws RemoteException;
}

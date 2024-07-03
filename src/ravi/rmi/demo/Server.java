package ravi.rmi.demo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface {
    private static final long serialVersionUID = 1L;
    
    // Thread-safe list to store integers
    private List<Integer> numList;
    // Variable to store the total sum
    private int total;
    // List of connected clients
    private List<ClientInterface> clients;

    public Server() throws RemoteException {
        numList = new CopyOnWriteArrayList<>();
        total = 0;
        clients = new CopyOnWriteArrayList<>();
    }

    // Register a client and notify all clients if we have five or more connected
    public synchronized void registerClient(ClientInterface client) throws RemoteException {
        clients.add(client);
        if (clients.size() >= 5) {
            notifyClients();
        }
    }

    // Notify all registered clients to start generating numbers
    private void notifyClients() throws RemoteException {
        for (ClientInterface client : clients) {
            client.startGeneratingNumbers();
        }
    }

    // Add a generated number to numList and update the total sum
    public synchronized void addNumber(int number) throws RemoteException {
        numList.add(number);
        total += number;
    }

    // Check if the clients should continue generating numbers
    public synchronized boolean shouldGenerateNumbers() throws RemoteException {
        return total < 1_000_000;
    }

    // Get the current total sum
    public synchronized int getTotal() throws RemoteException {
        return total;
    }

    // Get the current list of generated numbers
    public synchronized List<Integer> getNumList() throws RemoteException {
        return numList;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            java.rmi.registry.LocateRegistry.createRegistry(1099).rebind("Server", server);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ravi.rmi.demo;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Client extends UnicastRemoteObject implements ClientInterface {
    private static final long serialVersionUID = 1L;
    private ServerInterface server;
    private int generatedNumbersCount;
    private Random random;

    protected Client(ServerInterface server) throws RemoteException {
        this.server = server;
        this.generatedNumbersCount = 0;
        this.random = new Random();
    }

    // Start generating numbers and sending them to the server
    public void startGeneratingNumbers() throws RemoteException {
        new Thread(() -> {
            try {
                while (server.shouldGenerateNumbers()) {
                    int number = random.nextInt(13); // Generate a number between 0 and 12
                    server.addNumber(number); // Send the number to the server
                    generatedNumbersCount++;
                    Thread.sleep(10); // Wait for 10ms before generating the next number
                }
                // Print out the total and numList once the generation stops
                System.out.println("Total: " + server.getTotal());
                System.out.println("NumList: " + server.getNumList());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            // Lookup the server and register the client
            ServerInterface server = (ServerInterface) Naming.lookup("//localhost/Server");
            Client client = new Client(server);
            server.registerClient(client);
            System.out.println("Client registered");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

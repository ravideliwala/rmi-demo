# Random Integer Sum Client-Server Program

## Overview

This project implements a client-server program to record and sum a series of randomly generated integers. The program uses Java RMI (Remote Method Invocation) to facilitate communication between the server and multiple clients. The main features include:

- Client-server architecture
- Random integer generation and aggregation
- Real-time data synchronization across clients and server
- Automatic termination when a total sum threshold is reached

## Requirements

- Java Development Kit (JDK) 8 or higher
- Network setup allowing communication between clients and server

## Project Structure

- `ServerInterface.java`: Defines the remote methods that the server exposes.
- `ClientInterface.java`: Defines the remote methods that the clients expose.
- `Server.java`: Implements the server functionality, including managing the list of integers (`numList`) and the total sum (`total`).
- `Client.java`: Implements the client functionality, including generating random integers and sending them to the server.

## Setup and Usage

### 1. Compile the Code

Ensure you are in the project directory containing the `.java` files. Compile the server and client classes:

```bash
javac ServerInterface.java ClientInterface.java Server.java Client.java
```

### 2. Start the RMI Registry

Start the RMI registry on the default port (1099):

```bash
rmiregistry
```

### 3. Run the Server

In a new terminal, start the server:

```bash
java Server
```

### 4. Run the Clients

In separate terminals (at least five), start the clients:

```bash
java Client
```

### Example Output

Once the total sum reaches or exceeds 1 million, clients will print:

```bash
Total: 1000000 (or more)
NumList: [list of generated numbers]
```
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static ServerSocket server;

    public Server() throws IOException {
        server = new ServerSocket(6501);
    }

    @Override
    public void run() {
        while (true)
        {
            Socket client;
            try
            {
                client = server.accept();
                System.out.println("new client accepted!");
                ClientHandler clientHandler = new ClientHandler(client);
                Thread ch = new Thread(clientHandler);
                ch.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        try
        {
            Server sv = new Server();
            Thread t = new Thread(sv);
            t.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

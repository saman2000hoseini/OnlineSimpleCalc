package Client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private InputStreamReader reader;
    private PrintWriter writer;

    public void sendReq(String input) {
        try {
            writer.println(input);
            writer.flush();
        } catch (Exception e) {

        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                int res = reader.read();
                System.out.println(res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Client() {
        try {
            Socket client = new Socket("localhost", 6501);
            reader = new InputStreamReader(client.getInputStream());
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

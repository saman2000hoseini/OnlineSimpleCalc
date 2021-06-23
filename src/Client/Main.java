package Client;

import java.util.Scanner;

public class Main {
    private static Client client;

    public static void getInputs() {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        client.sendReq(input);
    }


    public static void main(String[] args) {
        client = new Client();
        Thread td = new Thread(client);
        td.start();
        while (true) {
            getInputs();
        }
    }
}

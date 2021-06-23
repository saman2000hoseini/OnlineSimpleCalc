package Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    BufferedReader reader =
            new BufferedReader(new FileReader("src/main/resources/input.txt"));
    private BufferedReader bufferedReader = new BufferedReader(reader);
    private PrintWriter writer;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    public static int sum(char tmp, int temp)
    {
        switch (tmp)
        {
            case '+':
                return +temp;
            case '-':
                return -temp;
            default:
                return 0;
        }
    }

    public static int calcRes(String input)
    {
        int res=0;
        int temp=0;
        char tmp='+';
        for (int i = 0; i <input.length(); i++)
        {
            char c = input.charAt(i);
            if (c=='+'||c=='-')
            {

                res += sum(tmp,temp);
                temp=0;
                tmp = c;
            }
            else
            {
                temp*=10;
                temp+=Character.getNumericValue(input.charAt(i));
            }
        }
        res+=sum(tmp,temp);
        return res;
    }


    @Override
    public void run() {
        while (true)
        {
            try
            {
                String ll = bufferedReader.readLine();
                System.out.println(ll);
                writer.write(calcRes(ll));
                writer.flush();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

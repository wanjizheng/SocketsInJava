import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) throws IOException {
        Socket clientSoc = new Socket("127.0.0.1", 6666);
        PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
        while (!clientSoc.isClosed()) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Ready for a chat.");
            String input = userInput.nextLine();
            if (!input.isEmpty()) {
                out.println(input);
                String answerMessage = in.readLine();
                if (null == answerMessage) {
                    in.close();
                    out.close();
                    clientSoc.close();
                } else
                    System.out.println(answerMessage);
            }
        }
        System.out.println("Application terminated.");
    }
}

import java.io.*;
import java.net.*;
import java.text.*;
import java.time.*;

public class ServerSide {
    public static void main(String[] args) throws IOException, ParseException {
        ServerSocket serverSoc = new ServerSocket(6666);
        Socket clientSoc = serverSoc.accept();
        PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
        String incommingMessage = "";
        String answerMessage = "";
        LocalDate birthDay = LocalDate.of(2021, 8, 8);
        while ((incommingMessage = in.readLine()) != null) {
            if ("What is your name?".equals(incommingMessage)) {
                answerMessage = "My name is Steve.";
            } else if ("What is your birthday?".equals(incommingMessage)) {
                answerMessage = birthDay.toString();
            } else if ("How old are you?".equals(incommingMessage)) {
                answerMessage = "Only " + String.valueOf(Period.between(birthDay, LocalDate.now()).getDays()) + " days.";
            } else if ("Let's finish this conversation.".equals(incommingMessage)) {
                in.close();
                out.close();
                clientSoc.close();
                serverSoc.close();
                break;
            } else {
                answerMessage = "Sorry, I don’t really understand that.";
            }
            out.println(answerMessage);
        }
    }
}

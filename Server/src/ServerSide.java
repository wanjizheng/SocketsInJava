import java.io.*;
import java.net.*;
import java.text.*;
import java.time.*;

public class ServerSide {
    public static void main(String[] args) throws IOException, ParseException {
        ServerSocket serverSoc = new ServerSocket(6666); //Initial the ServerSocket with the port number 6666
        Socket clientSoc = serverSoc.accept(); //Call the accept() method to stat listening, this will put the application on hold.
        System.out.println("A new client just connected.");
        PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true); //Declare the PrintWriter which will be used to send message to the client.
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream())); //Declare the BufferedReader which will be used to receive message from the client.
        String incommingMessage = ""; //A String type variable to store the message get from the client.
        String answerMessage = ""; //The message we'd like to send back to the client.
        LocalDate birthDay = LocalDate.of(2021, 8, 8);
        while ((incommingMessage = in.readLine()) != null) {
            if ("What is your name?".equals(incommingMessage)) {
                answerMessage = "My name is Steve.";
            } else if ("What is your birthday?".equals(incommingMessage)) {
                answerMessage = birthDay.toString();
            } else if ("How old are you?".equals(incommingMessage)) {
                answerMessage = "Only " + Period.between(birthDay, LocalDate.now()).getDays() + " days.";
            } else if ("Finish this conversation.".equals(incommingMessage)) {
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
        System.out.println("Application terminated.");
    }
}

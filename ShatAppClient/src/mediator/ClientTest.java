package mediator;

import model.Message;

import java.io.IOException;
import java.util.Scanner;

public class ClientTest
{
  public static void main(String[] args) throws IOException
  {
    Scanner input = new Scanner(System.in);
    boolean running = true;
    MessageClient messageClient = new MessageClient("Electimore");
    messageClient.connect("6.tcp.eu.ngrok.io",12671);

    while(running){
      System.out.print("Your message: ");
      String chat = input.nextLine();
      messageClient.sendMessage(chat);
    }
  }
}

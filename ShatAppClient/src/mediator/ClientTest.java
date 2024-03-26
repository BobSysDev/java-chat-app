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
    messageClient.connect("10.154.196.36",5678);

    while(running){
      System.out.print("Your message: ");
      String chat = input.nextLine();
      messageClient.sendMessage(chat);
    }
  }
}

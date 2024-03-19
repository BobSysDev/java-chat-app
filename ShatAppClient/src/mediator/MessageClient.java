package mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import com.google.gson.Gson;

public class MessageClient
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private Scanner input;
  private boolean running;
  private String receivedString;

  public MessageClient(String host, int port) throws IOException {
    try{
      this.socket = new Socket(host, port);
      System.out.println(socket.hashCode());
      System.out.println("DEBUG: Connected to server: " + host + " at port " + port);
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.gson = new Gson();
      this.input = new Scanner(System.in);
      this.running = true;
      Thread t = new Thread(new MessageClientReader(this, in));
      t.setDaemon(true);
      t.start();
      execute();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  private void execute(){
    System.out.println("W");
    while(running){
      String chat = input.nextLine();
      out.println(chat);
    }
  }
}

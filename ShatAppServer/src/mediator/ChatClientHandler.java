package mediator;
import com.google.gson.Gson;
import model.*;

import java.io.*;
import java.beans.*;
import java.net.*;

public class ChatClientHandler implements Runnable{
  private BufferedReader in;
  private PrintWriter out;
  private String ip;
  private ChatModel chatModel;
  private ChatServer server;

  public ChatClientHandler(Socket socket, ChatModel chatModel, ChatServer server){
    try{
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
    }
    catch(IOException e){
      e.printStackTrace();
    }

    this.server = server;
    this.chatModel = chatModel;
    this.ip = socket.getInetAddress().getHostAddress();
  }

  @Override public void run()
  {
    boolean running = true;
    Gson gson = new Gson();

    while(running){
      try{
        String incoming = in.readLine();
        if(incoming.equals("/online")){
          //server.getActiveClients()
          out.println(69);
          System.out.println("Replying with number of connected users");
        }
        else{
          Message message = gson.fromJson(incoming, Message.class);
          chatModel.addMessageLog(message, ip);
          System.out.println("Received a message from [" + message.getSender() + "]. Broadcasting...");
        }
      }
      catch (IOException e){
        System.out.println("There was an error while receiving the message.");
      }
    }
  }
}

package mediator;
import com.google.gson.Gson;
import model.*;

import java.io.*;
import java.beans.*;
import java.net.*;
import java.util.ArrayList;

public class ChatClientHandler implements Runnable, PropertyChangeListener{
  private BufferedReader in;
  private PrintWriter out;
  private String ip;
  private ChatModel chatModel;
  private ChatServer server;


  public ChatClientHandler(Socket socket, ChatModel chatModel, ChatServer server){
    try{
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.ip = socket.getInetAddress().getHostAddress();
      this.server = server;
    }
    catch(IOException e){
      e.printStackTrace();
    }

    this.chatModel = chatModel;
    this.chatModel.addListener(this);

  }

  @Override public void run()
  {
    boolean running = true;
    Gson gson = new Gson();
    while(running){
      try{
        String incoming = in.readLine();
        if(incoming.equals("/online")){
          out.println(server.getHandlersSize());
        }
        else{
          Message message = gson.fromJson(incoming, Message.class);
          message.setTimestamp(TimestampManipulation.getCurrentTimestamp());
          chatModel.addMessageLog(message, ip);
          System.out.println(ip+"> "+message.toString());
          //System.out.println("Received a message from [" + message.getSender() + "]. Broadcasting...");
        }
      }
      catch (IOException e){
        System.out.println("There was an error while receiving the message.");
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Message m = (Message)evt.getNewValue();
    out.println(m.toString());
  }
}

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
  private Socket socket;
  private Gson gson;
  private HeartbeatListener heartbeatListener;


  public ChatClientHandler(Socket socket, ChatModel chatModel, ChatServer server){
    try{
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.ip = socket.getInetAddress().getHostAddress();
      this.server = server;
      this.socket = socket;
      this.gson = new Gson();
      heartbeatListener = new HeartbeatListener(this);
      Thread heartbeatListenerThread = new Thread(heartbeatListener, "HBListener");
      heartbeatListenerThread.setDaemon(true);
      heartbeatListenerThread.start();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    this.chatModel = chatModel;
    this.chatModel.addListener(this);

  }

  public String getIp(){
    return ip;
  }
  @Override public void run()
  {
//    boolean running = true;

    while(true){
      if (socket.isClosed()){
        server.userDisconnected(this);
        break;
      }
      try{
        String incoming = in.readLine();
        if(incoming == null || incoming.isEmpty()){
          out.println("Received an empty message. Ignoring...");
        }
        else if (incoming.equals("/online")){
          server.handlersSize();
          out.println("/online="+String.valueOf(chatModel.getConnectedUsers()));
        }
        else if (incoming.equals("/disconnect")){
          server.userDisconnected(this);
          socket.close();
          System.out.println("server-cHandler disc user");
          break;
        }
        else if (incoming.equals("heartbeat")){
          heartbeatListener.registerBeat();
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
//        System.out.println("There was an error while receiving the message.");
        System.out.println(e);
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("ADD")){
      Message m = (Message)evt.getNewValue();
      String broadcasted = gson.toJson(m);
      out.println(broadcasted);

    }
  }

  public void closeSocket(){
    try
    {
      socket.close();
//      server.userDisconnected(this);
      Thread.currentThread().interrupt();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}

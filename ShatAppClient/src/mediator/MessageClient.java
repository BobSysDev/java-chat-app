package mediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import com.google.gson.Gson;
import model.ChatModel;
import model.ChatModelManager;
import model.Message;
import model.TimestampManipulation;

public class MessageClient implements PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private Scanner input;
  private boolean running;
  private String receivedString;
  private ChatModel model;

  private String username;

  public MessageClient(ChatModel model) throws IOException {

    socket = null;
    in = null;
    out = null;
    this.gson = new Gson();
    this.input = new Scanner(System.in);
    this.running = true;

    this.model = model;
    this.username = this.model.getUsername();
    this.model.addListener(this);
  }

//  //public void setUsername(String username)
//  {
//    this.username = username;
//  }

  public void connect(String serverIp, int port){
    try{
      if(!model.isRunning()){
        System.out.println("Attempting to connect...");
        socket = new Socket(serverIp, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Connected to " + serverIp + ":" + port);
        Thread messageReader = new Thread(new MessageClientReader(this, in,model), "messageReader");
        messageReader.setDaemon(true);
        messageReader.start();
        running = true;
        model.setRunning(true);
        sendWelcomeMessage();

        Thread heartbeatThread = new Thread(new Heartbeat(this), "heartbeat");
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
      }

    }
    catch(ConnectException e){
      System.out.println("Error: Could not establish connection with the server. Check input and try again...");
    }

    catch(IOException e){
      e.printStackTrace();
    }
  }

  public void dropConnection(){
    try
    {
      out.println("/disconnect");
      socket.close();
      System.out.println("The server is not responding. Try reconnecting...");
      running = false;
      model.setRunning(false);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void sendMessage(Message message){
    String payload = gson.toJson(message);
    out.println(payload);
  }

  private void sendWelcomeMessage(){
    //System.out.println("Now you can chat with others!");
    Message connected = new Message("<has connected!>", model.getUsername());
    String jsonConnect = gson.toJson(connected);
    out.println(jsonConnect);
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()){
      case "SEND":
        Message m = (Message)evt.getNewValue();
        sendMessage(m);
        //System.out.println("send");
        break;
      case "CONNECT":
        connect(model.getServerIP(), model.getPort());
        System.out.println("connect");
        break;
      case "DISCONNECT":
        dropConnection();
        System.out.println("disconnected");
        break;
      case "REFRESH":
        out.println("/online");
        break;

    }
  }
    
  public boolean isRunning(){
    return running;
  }

  public void sendHeartbeat(){
    out.println("heartbeat");
  }
}

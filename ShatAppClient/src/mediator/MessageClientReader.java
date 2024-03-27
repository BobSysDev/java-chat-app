package mediator;
import com.google.gson.Gson;
import model.ChatModel;
import model.Message;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class MessageClientReader implements Runnable
{
  private MessageClient messageClient;
  private BufferedReader in;
  private ChatModel model;
  private Gson gson;

  public MessageClientReader(MessageClient messageClient, BufferedReader in, ChatModel model){
    this.messageClient = messageClient;
    this.in = in;
    this.model = model;
    this.gson = new Gson();
  }

  @Override public void run()
  {
    while(true){
      try
      {
        String serverReply = in.readLine();
        Message m = gson.fromJson(serverReply,Message.class);
        System.out.println("Server> "+m.toString());
        model.addToListMessage(m);
        //messageClient.receive(serverReply);
      }
      catch (SocketException e){
//        messageClient.dropConnection();
        System.out.println("Error: socket exception!");
        break;
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}

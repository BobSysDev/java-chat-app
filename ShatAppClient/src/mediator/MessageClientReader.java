package mediator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class MessageClientReader implements Runnable
{
  private MessageClient messageClient;
  private BufferedReader in;

  public MessageClientReader(MessageClient messageClient, BufferedReader in){
    this.messageClient = messageClient;
    this.in = in;
  }

  @Override public void run()
  {
    while(true){
      try
      {
        String serverReply = in.readLine();
        System.out.println("Server> "+serverReply);
        //messageClient.receive(serverReply);
      }
      catch (SocketException e){
        messageClient.dropConnection();
        break;
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}

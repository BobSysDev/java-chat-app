package mediator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

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

  }
}

package mediator;

import java.io.IOException;

public class ClientTest
{
  public static void main(String[] args) throws IOException
  {
    MessageClient messageClient = new MessageClient("10.154.196.72",5678);
  }
}

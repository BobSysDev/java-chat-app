package mediator;

import java.io.IOException;

public class ClientTest
{
  public static void main(String[] args) throws IOException
  {
    MessageClient messageClient = new MessageClient("localhost",5678);
  }
}

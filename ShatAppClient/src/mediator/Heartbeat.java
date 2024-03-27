package mediator;

public class Heartbeat implements Runnable
{
  private static final int INTERVAL = 3000;
  private MessageClient client;

  public Heartbeat(MessageClient client){
    this.client = client;
  }

  @Override public void run()
  {
    while (client.isRunning())
    {
      client.sendHeartbeat();
//      System.out.println("Pulse sent!");
      try
      {
        Thread.sleep(INTERVAL);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}

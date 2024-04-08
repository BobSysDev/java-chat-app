//package mediator;
//
//import model.TimestampManipulation;
//
//public class HeartbeatListener implements Runnable
//{
//  private ChatClientHandler clientHandler;
//  private long lastBeatTimestamp;
//  private static final int MAX_INTERVAL = 6;
//
//  public HeartbeatListener(ChatClientHandler clientHandler){
//    lastBeatTimestamp = TimestampManipulation.getCurrentTimestamp();
//    this.clientHandler = clientHandler;
//  }
//
//  @Override public void run()
//  {
//    while(true){
//      try
//      {
//        Thread.sleep(MAX_INTERVAL*1000);
//      }
//      catch (InterruptedException e)
//      {
//        throw new RuntimeException(e);
//      }
//
//      if(TimestampManipulation.getCurrentTimestamp() - lastBeatTimestamp >= MAX_INTERVAL){
////        System.out.println("One of the clients timed out. Dropping connection...");
//        clientHandler.closeSocket();
//        break;
//      }
//    }
//  }
//
//  public synchronized void registerBeat(){
//    lastBeatTimestamp = TimestampManipulation.getCurrentTimestamp();
////    System.out.println("Pulse received!");
//  }
//}

package external;

import model.Message;
import model.MessageLog;
import model.TimestampManipulation;

import java.io.IOException;

public class MessageLogTest
{


  public static void main(String[] args) throws InterruptedException

  {
    Message m = new Message("nazdar","Marius", TimestampManipulation.getCurrentTimestamp());
    Thread.sleep(2000);
    Message m2 = new Message("sdf","Olek", TimestampManipulation.getCurrentTimestamp());
    Message m3 = new Message("hello","Sam", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"127.0.0.1");
    log.addLog(m3,"127.0.0.1");
    Thread.sleep(3000);
    m3 = new Message("helo wrld","Sam", TimestampManipulation.getCurrentTimestamp());
    log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"192.168.0.31");
    log.addLog(m3,"123.45.6.78");
  }
}

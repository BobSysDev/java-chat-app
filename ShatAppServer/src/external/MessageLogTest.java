package external;

import model.Message;
import model.MessageLog;
import model.TimestampManipulation;

public class MessageLogTest
{
  public static void main(String[] args) throws InterruptedException
  {
    Message m = new Message("nazdar","Marius", TimestampManipulation.getCurrentTimestamp());
    Thread.sleep(2000);
    Message m2 = new Message("sdf","Olek", TimestampManipulation.getCurrentTimestamp());
    Thread.sleep(3000);
    Message m3 = new Message("helo wrld","Sam", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"192.168.0.31");
    log.addLog(m3,"123.45.6.78");
  }
}

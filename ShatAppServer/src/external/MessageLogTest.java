package external;

import model.Message;
import model.MessageLog;
import model.TimestampManipulation;

public class MessageLogTest
{
  public static void main(String[] args)
  {
    Message m = new Message("nazdar","Marius", TimestampManipulation.getCurrentTimestamp());
    Message m2 = new Message("sdf","Olek", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"127.0.0.1");
  }
}

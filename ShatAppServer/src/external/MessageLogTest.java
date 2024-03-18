package external;

import model.Message;
import model.MessageLog;
import model.TimestampManipulation;

import java.io.IOException;

public class MessageLogTest
{
  public static void main(String[] args) throws IOException
  {
    Message m = new Message("nazdar","Marius", TimestampManipulation.getCurrentTimestamp());
    Message m2 = new Message("sdf","Olek", TimestampManipulation.getCurrentTimestamp());
    Message m3 = new Message("hello","Sam", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"127.0.0.1");
    log.addLog(m3,"127.0.0.1");
  }
}

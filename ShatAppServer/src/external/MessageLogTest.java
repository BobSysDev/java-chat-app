package external;

import model.Message;
import model.MessageLog;
import model.TimestampManipulation;

import java.io.IOException;

public class MessageLogTest
{
<<<<<<< HEAD
  public static void main(String[] args) throws IOException
=======
  public static void main(String[] args) throws InterruptedException
>>>>>>> 27498c5ed155a4e93411cbb261be9b96a0d829e5
  {
    Message m = new Message("nazdar","Marius", TimestampManipulation.getCurrentTimestamp());
    Thread.sleep(2000);
    Message m2 = new Message("sdf","Olek", TimestampManipulation.getCurrentTimestamp());
<<<<<<< HEAD
    Message m3 = new Message("hello","Sam", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"127.0.0.1");
    log.addLog(m3,"127.0.0.1");
=======
    Thread.sleep(3000);
    Message m3 = new Message("helo wrld","Sam", TimestampManipulation.getCurrentTimestamp());
    MessageLog log = MessageLog.getInstance();
    log.addLog(m,"127.0.0.1");
    log.addLog(m2,"192.168.0.31");
    log.addLog(m3,"123.45.6.78");
>>>>>>> 27498c5ed155a4e93411cbb261be9b96a0d829e5
  }
}

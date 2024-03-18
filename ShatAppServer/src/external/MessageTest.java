package external;

import model.Message;
import model.TimestampManipulation;

public class MessageTest
{
  public static void main(String[] args)
  {
    Message message = new Message("Hello, World!", "Alice");
    System.out.println(message.getContent());
    System.out.println(message.getSender());
    message.setTimestamp(TimestampManipulation.getCurrentTimestamp());
    System.out.println(message);
    System.out.println(TimestampManipulation.convertTimestampToDateTimeFull(message.getTimestamp()));
    System.out.println(TimestampManipulation.convertTimestampToDateTimeShort(message.getTimestamp()));
    System.out.println(TimestampManipulation.convertTimestampToTime(message.getTimestamp()));
    System.out.println(TimestampManipulation.convertTimestampToDate(message.getTimestamp()));
    System.out.println(TimestampManipulation.convertTimestampCustom(message.getTimestamp(), "MM-dd"));

  }
}

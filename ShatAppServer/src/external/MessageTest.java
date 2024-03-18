package external;

import model.Message;

public class MessageTest
{
  public static void main(String[] args)
  {
    Message message = new Message("Hello, World!", "Alice");
    System.out.println(message.getContent());
    System.out.println(message.getSender());
    message.setTimestamp(Message.getCurrentTimestamp());
    System.out.println(message);
    System.out.println(Message.timestampToTimeConvert(message.getTimestamp()));
  }
}

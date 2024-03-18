package model;

public class Message
{
  private String content;
  private String sender;
  private long timestamp;

  public Message(String content, String sender, long timestamp)
  {
    this.content = content;
    this.sender = sender;
    this.timestamp = timestamp;
  }

  public Message(String message, String sender)
  {
    this.content = message;
    this.sender = sender;
    this.timestamp = -1;
  }

  public String getContent()
  {
    return content;
  }

  public String getSender(){
    return sender;
  }

  public long getTimestamp(){
    return timestamp;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setSender(String sender){
    this.sender = sender;
  }

  public void setTimestamp(long timestamp)
  {
    this.timestamp = timestamp;
  }

  public static String timestampToTimeConvert(long timestamp){
    return new java.util.Date((long) timestamp * 1000).toString();
  }

  public static long getCurrentTimestamp(){
    return System.currentTimeMillis() / 1000L;
  }

  @Override public String toString(){
    if(timestamp == -1){
      return "[" + sender + "]: " + content;
    }
    return "[" + timestamp + " | " + sender + "]: " + content;
  }
}

package model;

public class Message
{
    private String message;
    private String sender;
    private String timestamp;

    public Message(String message, String sender, String timestamp)
    {
        this.message = message;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public Message(String message, String sender)
    {
        this.message = message;
        this.sender = sender;
        this.timestamp = null;
    }

    public String getMessage()
    {
        return message;
    }

    public String getSender(){
        return sender;
    }

   public String getTimestamp(){
        return timestamp;
   }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    @Override public String toString(){
        return "["
    }
}

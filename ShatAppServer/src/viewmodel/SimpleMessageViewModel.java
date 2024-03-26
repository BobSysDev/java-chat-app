package viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Message;
public class SimpleMessageViewModel
{
  private StringProperty content;
  private StringProperty sender;
  private LongProperty timestamp;

  public SimpleMessageViewModel(Message message){
    this.content = new SimpleStringProperty(message.getContent());
    this.sender = new SimpleStringProperty(message.getSender());
    this.timestamp = new SimpleLongProperty(message.getTimestamp());
  }

  public StringProperty getContent(){
    return content;
  }
  public StringProperty getSender(){
    return sender;
  }
  public LongProperty getTimestamp(){
    return timestamp;
  }


}

package viewmodel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;
import model.Message;
import model.TimestampManipulation;

public class ChatViewModel implements PropertyChangeListener{

  private ChatModel model;
  //private ChatModel model2;
  private static ObservableList<String> messages;
  private StringProperty onlineCountLabel;


  public ChatViewModel(ChatModel model){
    this.model = model;
    messages = FXCollections.observableArrayList();
    this.model.getConnectedUsers();
    this.onlineCountLabel = new SimpleStringProperty(String.valueOf(model.getConnectedUsers()));
    model.addListener(this);
    //loadFromModel();
  }

  public StringProperty getOnlineCountLabel(){
    return onlineCountLabel;
  }
  public void refresh()
  {
   this.onlineCountLabel.set(String.valueOf(model.getConnectedUsers()));
  }

  public void loadFromModel(){
    if(!messages.isEmpty()){
      messages.clear();
    }
    for (int i = 0; i < model.getAllMessages().size(); i++) {
      messages.add("{"+ TimestampManipulation.convertTimestampToDateTimeShort(model.getAllMessages().get(i).getTimestamp()) +
          "} "+model.getAllMessages().get(i).getSender()+"> "+
          model.getAllMessages().get(i).getContent());
    }
  }

  public void addMessage(Message m){
    messages.add("{"+ TimestampManipulation.convertTimestampToDateTimeShort(m.getTimestamp()) +
        "} "+m.getSender()+": "+
        m.getContent());
  }

  public static ObservableList<String> getMessages() {
    return messages;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("ADD")){
      Message m = (Message) evt.getNewValue();
      String messageString = "{"+TimestampManipulation.convertTimestampToDateTimeShort(m.getTimestamp())+"} "
          +m.getSender()+": "+m.getContent();
      Platform.runLater(()->messages.add(0,messageString));
      //loadFromModel();
      System.out.println("ChatViewModel->loaded from model (event)");
      //addMessage(m);
    }
  }
}

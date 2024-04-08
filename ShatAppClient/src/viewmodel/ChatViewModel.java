package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;
import model.Message;
import model.TimestampManipulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

//  public void loadFromModel(){
//    if(!messages.isEmpty()){
//      messages.clear();
//    }
//    for (int i = 0; i < model.getMessages().size(); i++) {
//      messages.add(getMessages().get(i).toString());
//    }
//  }

//  public void addMessage(Message m){
//    messages.add("{"+ TimestampManipulation.convertTimestampToDateTimeShort(m.getTimestamp()) +
//        "} "+m.getSender()+": "+
//        m.getContent());
//  }

  public static ObservableList<String> getMessages() {
    return messages;
  }

  public void send(String content){
    model.setCurrentMessage(content);
  }

  public String getIp(){
    return model.getServerIP();
  }

  public int getPort(){
    return model.getPort();
  }

  public void setUsername(String username){
    model.setUsername(username);
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()){
      case "NEW":
        Message m = (Message) evt.getNewValue();
        String messageString = "{"+TimestampManipulation.convertTimestampToDateTimeShort(m.getTimestamp())+"} "
            +m.getSender()+": "+m.getContent();
        Platform.runLater(()->messages.add(0,messageString));
        System.out.println("ChatViewModel->loaded from model (event)");
        break;
    }
  }
}


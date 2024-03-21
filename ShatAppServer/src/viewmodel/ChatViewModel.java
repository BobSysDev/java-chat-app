package viewmodel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;
import model.Message;

public class ChatViewModel implements PropertyChangeListener{

  private ChatModel model;
  //private ChatModel model2;
  private static ObservableList<String> messages;
  private StringProperty onlineCountLabel;


  public ChatViewModel(ChatModel model){
    this.model = model;
    //this.model2 = model2;
    this.messages = FXCollections.observableArrayList();
    this.model.getNumberOfConnectedUsers();
    //this.onlineCountLabel = new SimpleStringProperty(String.valueOf(model2.getNumberOfConnectedUsers()));
    model.addListener(this);
    loadFromModel();
  }

  public StringProperty getOnlineCountLabel(){
    return onlineCountLabel;
  }
  public void refresh()
  {
   //this.onlineCountLabel.set(String.valueOf(model2.getNumberOfConnectedUsers()));
  }

  public void loadFromModel(){
    messages.clear();
    for (int i = 0; i < model.getAllMessages().size(); i++) {
      messages.add("{"+model.getAllMessages().get(i).getTimestamp() +
          "} "+model.getAllMessages().get(i).getSender()+"> "+
          model.getAllMessages().get(i).getContent());
      //System.out.println(model.getAllVinyls().get(i).toString());
    }
  }

  public ObservableList<String> getMessages(){
    return messages;
  }

//  public void send(String content){
//    String ip = model2.
//    Message m = new Message(content,)
//    model.addMessageLog(m,);
//  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("ADD")){
      loadFromModel();
    }
  }
}

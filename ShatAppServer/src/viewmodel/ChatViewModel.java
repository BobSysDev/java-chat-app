package viewmodel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;

public class ChatViewModel implements PropertyChangeListener{

  private ChatModel model;
  private ChatModel model2;
  private static ObservableList<SimpleMessageViewModel> messages;
  private IntegerProperty onlineCountLabel;


  public ChatViewModel(ChatModel model, ChatModel model2){
    this.model = model;
    this.model2 = model2;
    messages = FXCollections.observableArrayList();
    this.model.getNumberOfConnectedUsers();
    this.onlineCountLabel = new SimpleIntegerProperty(0);
    model.addListener(this);
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    this.onlineCountLabel.set(model2.getNumberOfConnectedUsers());
  }
}

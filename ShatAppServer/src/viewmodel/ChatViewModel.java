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
  private static ObservableList<SimpleMessageViewModel> messages;
  private IntegerProperty onlineCountLabel;


  public ChatViewModel(ChatModel model){
    this.model = model;
    messages = FXCollections.observableArrayList();
    this.onlineCountLabel = new SimpleIntegerProperty(model.getNumberOfConnectedUsers());
    model.addListener(this);
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}

package viewmodel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;

public class ChatViewModel implements PropertyChangeListener{

  private ChatModel model;
  private static ObservableList<SimpleMessageViewModel> messages;


  public ChatViewModel(ChatModel model){
    this.model = model;
    messages = FXCollections.observableArrayList();
    model.addListener(this);
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}

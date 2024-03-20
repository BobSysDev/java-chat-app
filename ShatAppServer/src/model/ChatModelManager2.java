package model;

import mediator.ChatServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ChatModelManager2 implements ChatModel
{
  private PropertyChangeSupport propertyChangeSupport;
  private ChatServer chatServer;

  public ChatModelManager2() throws IOException
  {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.chatServer = ChatServer.getInstance();
  }

  @Override public ArrayList<Message> getAllMessages()
  {
    return null;
  }

  @Override public void addMessageLog(Message message, String ip)
  {

  }

  @Override public int getNumberOfConnectedUsers()
  {
    return chatServer.getHandlersSize();
  }

  @Override public String getIp()
  {
    chatServer.
  }

  public void addListener(java.beans.PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  public void removeListener(java.beans.PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}

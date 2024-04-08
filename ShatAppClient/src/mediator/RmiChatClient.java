package mediator;

import model.ChatModel;
import model.Message;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class RmiChatClient implements RemoteListener<Message, Message>,
    PropertyChangeListener
{
  private ChatRemoteModel server;
  private ChatModel model;

  public RmiChatClient(ChatModel model){
    this.model = model;
    model.addListener(this);
  }

  public void start(String host)
      throws RemoteException, ServerNotActiveException, MalformedURLException,
      NotBoundException
  {
    server = (ChatRemoteModel) Naming.lookup("rmi://"+host+":1099/Chat");
    UnicastRemoteObject.exportObject(this, 0);
    server.addListener(this);

    server.join();//only for counting online  users
    String sender = model.getUsername();
    server.send(new Message("-->has joined the chat!<--",sender));
  }

  public void disconnect() throws RemoteException
  {
    server.disconnect();
  }

  @Override public void propertyChange(ObserverEvent<Message, Message> event)
      throws RemoteException
  {
    switch (event.getPropertyName()){
      case "BROADCAST" :
        Message m = event.getValue2();
        model.addToListMessage(m);
        break;
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()){
      case "REFRESH":
        try {
          model.setConnectedUsers(server.getOnlineUsers());
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
        break;
      case "SEND":
        Message m = (Message)evt.getNewValue();
        try {
          server.send(m);
        }
        catch (Exception e) {
          throw new RuntimeException(e);
        }
        break;
      case "CONNECT":
        try {
//          System.out.println("connect event");
          start(model.getServerIP());
        }
        catch (Exception e) {
          throw new RuntimeException(e);
        }
        break;
    }
  }
}

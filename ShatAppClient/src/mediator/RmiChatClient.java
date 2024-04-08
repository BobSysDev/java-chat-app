package mediator;

import model.ChatModel;
import model.Message;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class RmiChatClient implements RemoteListener<Message, Message>,
    PropertyChangeListener
{
  private ChatRemoteModel server;
  private ChatModel model;

  public RmiChatClient(String host, int port){
    try{
      server = (ChatRemoteModel) Naming.lookup("rmi://"+host+":"+port+"/Chat");
      UnicastRemoteObject.exportObject(this, 0);
      server.addListener(this);
      model.addListener(this);
    }
    catch (Exception e){e.printStackTrace();}
  }

  public void start() throws RemoteException, ServerNotActiveException
  {
    server.join();
    String sender = model.getUsername();
    server.send(new Message(sender+" has joined the chat!",sender));
  }

  public void disconnect(){
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
        model.setConnectedUsers(server.getOnlineUsers());
        break;
      case "SEND":
        Message m = (Message)evt.getNewValue();
        try {
          server.send(m);
        }
        catch (ServerNotActiveException e) {
          throw new RuntimeException(e);
        }
    }
  }
}

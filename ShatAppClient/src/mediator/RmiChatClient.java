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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
    Registry registry = LocateRegistry.getRegistry(host, 1099);
    server = (ChatRemoteModel) registry.lookup("Chat");
    UnicastRemoteObject.exportObject(this, 0);
    server.addListener(this);

    server.join();//only for counting online  users
    String sender = model.getUsername();
    server.send(new Message("-->has joined the chat!<--",sender));
  }

  @Override public void propertyChange(ObserverEvent<Message, Message> event)
      throws RemoteException
  {
    switch (event.getPropertyName()){
      case "BROADCAST" :
        System.out.println("Broadcasting from server(event)");
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
        if(m.getContent() == null || m.getContent().isEmpty()){
          break;
        }
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
      case "WELCOME":
        try {
          server.send(new Message("-->has changed their username<--",model.getUsername()));
        }
        catch (Exception e) {
          throw new RuntimeException(e);
        }
        break;
      case "DISCONNECT":
        try {
          server.send(new Message("-->has disconnected! (lame)<--",model.getUsername()));
          server.disconnect();
        }
        catch (RemoteException e) {
          throw new RuntimeException(e);
        }
        catch (ServerNotActiveException e)
        {
          throw new RuntimeException(e);
        }
        break;


    }
  }
}

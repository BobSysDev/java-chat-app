package mediator;
import model.ChatModel;
import model.Message;
import model.TimestampManipulation;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import javax.swing.plaf.basic.BasicListUI;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class RmiChatServer implements ChatRemoteModel
{
  private ChatModel model;
  private PropertyChangeHandler<Message, Message> property;


  public RmiChatServer(ChatModel model) throws MalformedURLException, RemoteException{
    this.model = model;
    this.property = new PropertyChangeHandler<>(this, true);
    startRegistry();
//    startServer();
  }

  public void startRegistry()throws RemoteException {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      UnicastRemoteObject.exportObject(this, 0);
      reg.bind("Chat", this);
      System.out.println("Reg. started.");
      System.out.println(Inet4Address.getLocalHost().getHostAddress() + ":1099");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
    catch (AlreadyBoundException | UnknownHostException e)
    {
      throw new RuntimeException(e);
    }
  }

//  public void startServer() throws RemoteException, MalformedURLException
//  {
//    UnicastRemoteObject.exportObject(this, 0);
//    Naming.rebind("Chat", this);
//  }


  public void send(Message m) throws ServerNotActiveException
  {
    String ip = RemoteServer.getClientHost();
    m.setTimestamp(TimestampManipulation.getCurrentTimestamp());
    model.addMessageLog(m,ip);
    property.firePropertyChange("BROADCAST",null, m);
  }

  @Override public void join(){
    model.setConnectedUsers(model.getConnectedUsers()+1);
  }

  @Override public void disconnect()
  {
    model.setConnectedUsers(model.getConnectedUsers()-1);
  }

  @Override public int getOnlineUsers()
  {
    return model.getConnectedUsers();
  }

  @Override public boolean addListener(
      GeneralListener<Message, Message> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Message, Message> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}

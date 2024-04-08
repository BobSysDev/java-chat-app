package mediator;

import model.Message;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface ChatRemoteModel extends Remote, RemoteSubject<Message, Message>
{
  void send(Message m) throws ServerNotActiveException, RemoteException;
  int getOnlineUsers() throws RemoteException;
  void join() throws RemoteException;
  void disconnect() throws RemoteException;
}

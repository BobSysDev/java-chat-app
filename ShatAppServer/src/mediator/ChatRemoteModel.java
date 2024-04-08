package mediator;

import model.Message;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.server.ServerNotActiveException;

public interface ChatRemoteModel extends Remote, RemoteSubject<Message, Message>
{
  void send(Message m) throws ServerNotActiveException;
  int getOnlineUsers();
  void join();
}

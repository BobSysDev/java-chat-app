package model;

import mediator.ChatServer;
import utility.UnnamedPropertyChangeSubject;
public interface ChatModel extends UnnamedPropertyChangeSubject{

//    String getMessage();
//    String getSender();
//    String getTimestamp();

    void addMessageLog(Message message, String ip);
    int getNumberOfConnectedUsers();
}

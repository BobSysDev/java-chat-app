package model;

import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface ChatModel extends UnnamedPropertyChangeSubject
{
        //ArrayList<Message> getAllMessages();
        //void addMessageLog(Message message, String ip);
        int getConnectedUsers();
        void setConnectedUsers(int n);

        String getIp();

}
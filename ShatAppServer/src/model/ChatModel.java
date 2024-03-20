package model;

import utility.UnnamedPropertyChangeSubject;



public interface ChatModel extends UnnamedPropertyChangeSubject
{
        void addMessageLog(Message message, String ip);
        int getNumberOfConnectedUsers();

}
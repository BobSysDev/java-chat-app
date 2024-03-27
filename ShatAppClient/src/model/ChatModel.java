package model;

import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;
import java.util.List;

public interface ChatModel extends UnnamedPropertyChangeSubject
{
        //ArrayList<Message> getAllMessages();
        //void addMessageLog(Message message, String ip);
        String getIp();

        void connect();
        void disconnect();
        ArrayList<Message> getMessages();
        Message getCurrentMessage();
        void setCurrentMessage(String content);

        void addToListMessage(Message message);

        String getUsername();
        void setUsername(String username);

        String getServerIP();
        void setServerIP(String serverIP);

        int getPort();
        void setPort(int port);

        int getConnectedUsers();
        void setConnectedUsers(int n);

}
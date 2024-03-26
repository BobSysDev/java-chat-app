package model;

import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface ChatModel extends UnnamedPropertyChangeSubject
{
        ArrayList<Message> getAllMessages();
        void addMessageLog(Message message, String ip);
        int getConnectedUsers();
        void setConnectedUsers(int n);

        ArrayList<Message> getMessages();

        Message getCurrentMessage();
        void setCurrentMessage(Message message);
        void addToListMessage(Message message);

        String getUsername();
        void setUsername(String username);

        String getServerIP();
        void setServerIP(String serverIP);

        int getPort();
        void setPort(int port);


        String getIp();
}

package model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatModelManager implements ChatModel{

    private PropertyChangeSupport propertyChangeSupport;
    //private MessageLog messageLog;
    private int connectedUsers;


    public ChatModelManager() {
        //this.messageLog = MessageLog.getInstance();
        this.propertyChangeSupport = new PropertyChangeSupport(this);

    }

    public void addListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

//    @Override public ArrayList<Message> getAllMessages()
//    {
//        return messageLog.getLogs();
//    }

//    @Override
//    public void addMessageLog(Message message, String ip) {
//       messageLog.addLog(message, ip);
//       propertyChangeSupport.firePropertyChange("ADD", ip, message);
//    }

    @Override public int getConnectedUsers()
    {
        return connectedUsers;
    }

    @Override public void  setConnectedUsers(int n)
    {
        this.connectedUsers = n;
    }


    @Override public String getIp()
    {
        return null;
    }

}

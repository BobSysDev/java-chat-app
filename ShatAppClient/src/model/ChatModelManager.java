package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatModelManager implements ChatModel, UnnamedPropertyChangeSubject {

    private PropertyChangeSupport propertyChangeSupport;
    private ArrayList<Message> messages;
    private Message currentMessage;
    private String username;
    private String serverIP;
    private int port;
    private int connectedUsers;

    //private MessageLog messageLog;



    public ChatModelManager() {
        //this.messageLog = MessageLog.getInstance();
        this.messages = new ArrayList<>();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void addToListMessage(Message message){
        this.messages.add(message);
    }

    @Override
    public ArrayList<Message> getMessages(){
        return this.messages;
    }

    @Override
    public Message getCurrentMessage(){
        return this.currentMessage;
    }

    @Override
    public void setCurrentMessage(String content){
        Message m = new Message(content,getUsername());
        this.currentMessage = m;
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public int getPort(){
        return this.port;
    }

    @Override
    public void setPort(int port){
        this.port = port;
    }

    @Override
    public String getServerIP(){
        return this.serverIP;
    }
    
    @Override
    public void setServerIP(String serverIP){
        this.serverIP = serverIP;
    }

    @Override public int getConnectedUsers()
    {
        return connectedUsers;
    }

    @Override public void  setConnectedUsers(int n)
    {
        this.connectedUsers = n;
    }

    @Override
    public String getIp() {
        return null;
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



}

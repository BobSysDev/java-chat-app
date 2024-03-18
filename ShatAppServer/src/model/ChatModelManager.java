package model;

import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel{

    private PropertyChangeSupport propertyChangeSupport;
    private MessageLog messageLog;

    public ChatModelManager() {
        this.messageLog = MessageLog.getInstance();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void addMessageLog(Message message, String ip) {
       messageLog.addLog(message, ip);
<<<<<<< HEAD
       propertyChangeSupport.firePropertyChange("log", ip, message);
=======
>>>>>>> 27498c5ed155a4e93411cbb261be9b96a0d829e5
       propertyChangeSupport.firePropertyChange("log", "", message);
    }
}

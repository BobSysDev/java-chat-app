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
       messageLog.addLog(message);
       propertyChangeSupport.firePropertyChange("log", "", message);
       propertyChangeSupport.firePropertyChange("log", "", ip);
    }
}

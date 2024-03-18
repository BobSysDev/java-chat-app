package model;

import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel{

    private PropertyChangeSupport propertyChangeSupport;

    public ChatModelManager() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }


    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getSender() {
        return null;
    }

    @Override
    public String getTimestamp() {
        return null;
    }

    public void addListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

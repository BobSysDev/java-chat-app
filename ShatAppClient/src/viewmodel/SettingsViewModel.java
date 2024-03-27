package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.MessageClient;

import java.io.IOException;

public class SettingsViewModel {

    private model.ChatModel chatModel;
    private StringProperty username;
    private StringProperty ip;
    private IntegerProperty port;

    private MessageClient messageClient;

    public SettingsViewModel(model.ChatModel chatModel) {
        this.chatModel = chatModel;
        this.username = new SimpleStringProperty();
        this.ip = new SimpleStringProperty();
        this.port= new SimpleIntegerProperty();
        messageClient = null;
    }

    public StringProperty getUsernameProperty() {
        return username;
    }

    public StringProperty getIpProperty() {
        return ip;
    }

    public IntegerProperty getPort() {
        return port;
    }


    public void setUsername(String username) {
        this.username.set(username);
        chatModel.setUsername(username);
    }

    public void setIp(String ip) {
        this.ip.set(ip);
        chatModel.setServerIP(ip);
    }

    public void setPort(int port) {
        this.port.set(port);
        chatModel.setPort(port);
    }

    public String getUsername() {
        return username.get();
    }

    public String getIp(){
        return ip.get();
    }

    public void connect() throws IOException
    {
        chatModel.disconnect();
        if (messageClient == null){
            messageClient = new MessageClient(chatModel);
        }
        chatModel.connect();
    }


}

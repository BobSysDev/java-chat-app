package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SettingsViewModel {

    private model.ChatModel chatModel;
    private StringProperty username;
    private StringProperty ip;
    private IntegerProperty port;

    public SettingsViewModel(model.ChatModel chatModel) {
        this.chatModel = chatModel;
        this.username = new SimpleStringProperty();
        this.ip = new SimpleStringProperty();
        this.port= new SimpleIntegerProperty();
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
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public void setPort(int port) {
        this.port.set(port);
    }

    public String getUsername() {
        return username.get();
    }

    public String getIp(){
        return ip.get();
    }


}

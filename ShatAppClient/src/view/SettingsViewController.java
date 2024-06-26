package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.IntStringConverter;
import viewmodel.SettingsViewModel;

import java.io.IOException;

public class SettingsViewController
{
    private ViewHandler view;
    private Region root;
    private SettingsViewModel viewModel;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;


    public void init(ViewHandler view, SettingsViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;

        usernameTextField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        ipTextField.textProperty().bindBidirectional(viewModel.getIpProperty());

        IntStringConverter converter = new IntStringConverter();
        Bindings.bindBidirectional(portTextField.textProperty(),viewModel.getPort(), converter);
        portTextField.setDisable(true);
        portTextField.setText("1099");
    }

    @FXML
    public void applyButtonPressed() throws IOException
    {
        String username = usernameTextField.getText();
        String ip = ipTextField.getText();
        int port = Integer.parseInt(portTextField.getText());

        if(ip.equals(viewModel.getModelIp())&&port==viewModel.getModelPort()){
            viewModel.setUsername(username);
            viewModel.usernameChange();
        }
        else{
            viewModel.setUsername(username);
            viewModel.setIp(ip);
            viewModel.setPort(port);
            viewModel.connect();

        }
        view.openView("chat");
    }

    @FXML
    public void cancelButtonPressed() {
        view.openView("chat");
    }

}
package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.IntStringConverter;
import viewmodel.SettingsViewModel;

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
    }

    @FXML
    public void applyButtonPressed() {
        String username = viewModel.getUsername();
        String ip = viewModel.getIp();
        int port = viewModel.getPort().get();

        viewModel.setUsername(username);
        viewModel.setIp(ip);
        viewModel.setPort(port);

        view.openView("chat");
    }

    @FXML
    public void cancelButtonPressed() {
        view.openView("chat");
    }

}
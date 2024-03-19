package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.event.ActionEvent;

import viewmodel.ChatViewModel;

public class SettingsViewController {
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;


    }

    @FXML
    public void applyButtonPressed() {

        view.openView("chat");
    }

    @FXML
    public void cancelButtonPressed() {
        view.openView("chat");
    }

}
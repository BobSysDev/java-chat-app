package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class SettingsViewController {
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML private TextField usernameTextField;
    @FXML private TextField ipTextField;
    @FXML private TextField portTextField;
    @FXML private Button applyButton;
    @FXML private Button cancelButton;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;
    }
    @FXML public void applyButtonPressed() {}
    @FXML public void cancelButtonPressed() {}

}
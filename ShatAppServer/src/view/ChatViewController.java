package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController {
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML private ListView<String> chatList;
    @FXML private Label onlineCountLabel;
    @FXML private Button refreshButton;
    @FXML private Button settingsButton;
    @FXML private Button logButton;
    @FXML private TextField messageTextField;
    @FXML private Button sendButton;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;
    }

    @FXML public void refreshButtonPressed() {}
    @FXML public void settingsButtonPressed() {}
    @FXML public void logButtonPressed() {}
    @FXML public void sendButtonPressed() {}
    @FXML public void sendButtonOnEnterPressed() {}
}
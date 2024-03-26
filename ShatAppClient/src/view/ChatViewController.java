package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController
{
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML private ListView<String> chatList;
    @FXML private Label onlineCountLabel;
    // @FXML private TextField messageTextField;
    //@FXML private Button sendButton;
    //@FXML private Button settingsButton;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;
        //this.messageTextField.setVisible(false);
        //this.sendButton.setVisible(false);
        //this.settingsButton.setVisible(false);
        this.chatList.setItems(viewModel.getMessages());
        this.onlineCountLabel.textProperty().bind(viewModel.getOnlineCountLabel());
    }
    public void reset()
    {

    }


    @FXML public void refreshButtonPressed() {
        //view.openView("chat");
        viewModel.refresh();
    }
    @FXML public void settingsButtonPressed() {
        view.openView("settings");
    }
    @FXML public void logButtonPressed() {}
    @FXML public void sendButtonPressed() {
        String content = messageTextField.getText();
        viewModel.viewModelSend(content);
    }
    @FXML public void sendButtonOnEnterPressed() {}

    public Region getRoot()
    {
        return root;
    }
}
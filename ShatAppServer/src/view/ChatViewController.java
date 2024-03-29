package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChatViewController {
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML private ListView<String> chatList;
    @FXML private Label onlineCountLabel;
    @FXML private TextField messageTextField;
    @FXML private Button sendButton;
    @FXML private Button settingsButton;
    @FXML private Button logButton;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;
        this.messageTextField.setVisible(false);
        this.sendButton.setVisible(false);
        this.settingsButton.setVisible(false);
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
//        view.openView("settings");
    }
    @FXML public void logButtonPressed() {
      try
      {
        Desktop.getDesktop().open(new File(".\\Logs"));
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
    @FXML public void sendButtonPressed() {
//        String content = messageTextField.getText();

    }
    @FXML public void sendButtonOnEnterPressed() {}

    public Region getRoot()
    {
        return root;
    }
}
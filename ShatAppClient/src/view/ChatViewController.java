package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.WindowEvent;
import viewmodel.ChatViewModel;

public class ChatViewController
{
    private ViewHandler view;
    private Region root;
    private ChatViewModel viewModel;

    @FXML private ListView<String> chatList;
    @FXML private Label onlineCountLabel;
    @FXML private TextField messageTextField;
    @FXML private Button sendButton;
    @FXML private Button settingsButton;

    public void init(ViewHandler view, ChatViewModel viewModel, Region root) {
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;
        //this.messageTextField.setVisible(false);
        //this.sendButton.setVisible(false);
        //this.settingsButton.setVisible(false);
        this.chatList.setItems(viewModel.getMessages());
        this.onlineCountLabel.textProperty().bind(viewModel.getOnlineCountLabel());

        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                sendButtonPressed();
                ev.consume();
            }
        });


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
        viewModel.send(content);
        messageTextField.clear();
    }
    @FXML public void sendButtonOnEnterPressed() {
        sendButtonPressed();
    }

//    @FXML
//    public void exitApplication(ActionEvent event) {
//        System.out.println("lol");
//        Platform.exit();
//    }

    public Region getRoot()
    {
        return root;
    }
}
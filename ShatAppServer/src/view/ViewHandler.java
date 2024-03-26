package view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
    ViewModelFactory factory;
    private Stage primaryStage;
    private Scene currentScene;

    private ChatViewController chatViewController;
   // SettingsViewController settingsViewController;

    public ViewHandler(ViewModelFactory factory){
        this.factory = factory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("chat");
    }

    public void openView(String id){
        Region root = null;
        switch (id){
            case "chat" -> root = loadChatView("ChatView.fxml");
            //case "settings" -> root = loadSettingsView("SettingsView.fxml");
        }
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData()!=null){
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();

    }

    private Region loadChatView(String fxmlFile){
        Region root = null;
        if (chatViewController==null){
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                chatViewController = loader.getController();
                chatViewController.init(this, factory.getChatViewModel(), root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            chatViewController.reset();
        }
        return chatViewController.getRoot();
    }



}

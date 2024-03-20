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

    ChatViewController chatViewController;
    SettingsViewController settingsViewController;

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
            case "settings" -> root = loadSettingsView("SettingsView.fxml");
        }
        currentScene.setRoot(root);
        primaryStage.setScene(currentScene);
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.show();

    }

    public Region loadChatView(String fxmlFile){
        Region root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            root = loader.load();
            chatViewController = loader.getController();
            // chatViewController.init(this, factory.getChatViewModel(), root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    public Region loadSettingsView(String fxmlFile){
        Region root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            root = loader.load();
            settingsViewController = loader.getController();
            // settingsViewController.init(this, factory.getSettingsViewModel(), root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }



}

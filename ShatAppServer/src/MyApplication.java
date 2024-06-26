import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import mediator.RmiChatServer;
import model.ChatModel;
import model.ChatModelManager;


import view.ViewHandler;
import viewmodel.ChatViewModel;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
    ChatModel model = new ChatModelManager();

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

    RmiChatServer server = new RmiChatServer(model);

    EventHandler<WindowEvent> closeEventHandler = event -> {
      System.out.println("Closing the window...");
      Platform.exit();
      System.exit(0);
    };
    primaryStage.setOnCloseRequest(closeEventHandler);

  }
}
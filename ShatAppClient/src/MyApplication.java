import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import mediator.RmiChatClient;
import model.ChatModel;
import model.ChatModelManager;
import model.Message;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    ChatModel model = new ChatModelManager();

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

    System.out.println(model.getServerIP());

    RmiChatClient client = new RmiChatClient(model);

    EventHandler<WindowEvent> closeEventHandler = event -> {
      try
      {
        model.disconnect();
      }
      catch (Exception e){
        System.out.println("There is no connection to the server...");
      }
      finally
      {
        System.out.println("Closing the window...");
        Platform.exit();
        System.exit(0);
      }
    };
    primaryStage.setOnCloseRequest(closeEventHandler);

  }
}
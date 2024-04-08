import javafx.application.Application;
import javafx.stage.Stage;
import mediator.MessageClient;
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
      throws RemoteException, ServerNotActiveException
  {
    ChatModel model = new ChatModelManager();

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

    RmiChatClient client = new RmiChatClient(model.getServerIP(),model.getPort());
    client.start();
    Runtime.getRuntime().addShutdownHook(new Thread(
        () -> client.disconnect(), "Shutdown-thread"));

    //MessageClient messageClient = new MessageClient(model);
//    messageClient.connect(model.getServerIP(),model.getPort());



//    ChatServer chatServer = new ChatServer(model,5678);
//    Thread thread = new Thread(chatServer);
//    thread.setDaemon(true);
//    thread.start();

  }
}
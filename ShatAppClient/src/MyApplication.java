import javafx.application.Application;
import javafx.stage.Stage;
import mediator.MessageClient;
import model.ChatModel;
import model.ChatModelManager;
import model.Message;
import view.ViewHandler;
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

    MessageClient messageClient = new MessageClient(model);
//    messageClient.connect(model.getServerIP(),model.getPort());



//    ChatServer chatServer = new ChatServer(model,5678);
//    Thread thread = new Thread(chatServer);
//    thread.setDaemon(true);
//    thread.start();

  }
}
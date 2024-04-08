import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
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

//    ChatServer chatServer = new ChatServer(model,5678);
//    Thread thread = new Thread(chatServer);
//    thread.setDaemon(true);
//    thread.start();

    RmiChatServer server = new RmiChatServer(model);

  }
}
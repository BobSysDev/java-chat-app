import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.ChatModel;
import model.ChatModelManager;
import model.ChatModelManager2;

import view.ViewHandler;
import viewmodel.ChatViewModel;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
   // ChatModel chatModel = new ChatModelManager();


    ChatModel model = new ChatModelManager();
//    ChatModel model2 = new ChatModelManager2();


    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

    ChatServer chatServer = ChatServer.getInstance();
  }
}
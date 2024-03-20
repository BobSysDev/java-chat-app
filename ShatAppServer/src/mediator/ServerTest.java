package mediator;

import model.ChatModel;
import model.ChatModelManager;

import java.io.IOException;

public class ServerTest
{
  public static void main(String[] args) throws IOException
  {
    ChatModel chatModel = new ChatModelManager();
    ChatServer chatServer = ChatServer.getInstance();

  }
}

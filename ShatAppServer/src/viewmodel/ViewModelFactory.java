package viewmodel;
import model.ChatModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;



  public ViewModelFactory(ChatModel model){
    this.chatViewModel = new ChatViewModel(model);

  }

  public ChatViewModel getChatViewModel(){
    return chatViewModel;
  }


}
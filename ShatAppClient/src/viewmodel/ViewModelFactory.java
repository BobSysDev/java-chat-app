package viewmodel;
import model.ChatModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private SettingsViewModel settingsViewModel;




  public ViewModelFactory(ChatModel model){
    this.chatViewModel = new ChatViewModel(model);

  }

  public ChatViewModel getChatViewModel(){
    return chatViewModel;
  }
  public SettingsViewModel getSettingsViewModel(){
    return settingsViewModel;
  }


}
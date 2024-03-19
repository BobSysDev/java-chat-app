
package mediator;

import model.ChatModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer
{
    private ServerSocket chatSocket;
    private ChatModel chatModel;

    public ChatServer(ChatModel chatModel, int port) throws IOException
    {
        this.chatModel = chatModel;
        this.chatSocket = new ServerSocket(port);
        execute();
    }

    private void execute(){
        System.out.println("Starting server...");
        while(true){
            System.out.println("Waiting for a client...");
            Socket socket = null;
            try{
                socket = chatSocket.accept();
                Thread clientThread = new Thread(new ChatClientHandler(socket,chatModel,this));
                clientThread.start();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

}



package mediator;

import model.ChatModel;
import model.ChatModelManager;
import model.Message;
import model.MessageLog;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable{
    private ServerSocket chatSocket;
    private ChatModel chatModel;
    private ArrayList<ChatClientHandler> handlers;





    public ChatServer(ChatModel chatModel, int port)  throws IOException
    {
        this.chatModel = chatModel;
        this.chatSocket = new ServerSocket(port);
        this.handlers = new ArrayList<>();

        System.out.println(
            "Starting Server " + InetAddress.getLocalHost().getHostAddress()
                + " at port " + port + "...");
        }

    private void execute(){
        while(true){
            System.out.println("Waiting for a client...");
            Socket socket = null;
            try{
                socket = chatSocket.accept();
                ChatClientHandler handler = new ChatClientHandler(socket,chatModel,this);
                handlers.add(handler);
                handlersSize();

                Thread clientThread = new Thread(handler);
                clientThread.setDaemon(true);
                clientThread.start();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public void handlersSize(){
        chatModel.setConnectedUsers(handlers.size());
    }

    public void userDisconnected(ChatClientHandler user){
        handlers.remove(user);
    }

    @Override public void run()
    {
        execute();
    }

}


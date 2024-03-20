
package mediator;

import model.ChatModel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer
{
    private ServerSocket chatSocket;
    private ChatModel chatModel;
    private ArrayList<ChatClientHandler> handlers;

    public ChatServer(ChatModel chatModel, int port) throws IOException
    {
        this.chatModel = chatModel;
        this.chatSocket = new ServerSocket(port);
        this.handlers = new ArrayList<>();
        System.out.println(
            "Starting Server " + InetAddress.getLocalHost().getHostAddress()
                + " at port " + port + "...");

        execute();

    }

    private void execute(){
        while(true){
            System.out.println("Waiting for a client...");
            Socket socket = null;
            try{
                socket = chatSocket.accept();
                System.out.println( socket.hashCode()+" connected!");
                ChatClientHandler handler = new ChatClientHandler(socket,chatModel,this);
                handlers.add(handler);
                Thread clientThread = new Thread(handler);
                clientThread.start();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public int getHandlersSize(){
        return handlers.size();
    }

}


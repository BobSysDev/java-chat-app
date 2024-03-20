package model;

import utility.UnnamedPropertyChangeSubject;



public abstract class ChatModel implements UnnamedPropertyChangeSubject
{
        public void addMessageLog(Message message, String ip){}
        public int getNumberOfConnectedUsers(){
            return 0;
        }

}
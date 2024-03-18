package model;

import java.util.ArrayList;

public class MessageLog
{
  private ArrayList<Message> logs;
  private static MessageLog instance;
  private static Object lock = new Object();

  private MessageLog(){
    this.logs = new ArrayList<>();
  }

  public static MessageLog getInstance(){
    if(instance==null){
      synchronized (lock){
        if (instance==null){
          instance = new MessageLog();
        }
      }
    }
    return instance;
  }

  public void addLog(String content, String sender, Message.get)
}

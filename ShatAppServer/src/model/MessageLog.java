package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

  public void addLog(String content, String sender){
    long timestamp =  Message.getCurrentTimestamp();
    Message message = new Message(content, sender, timestamp);
    this.logs.add(message);
    addToFile(message);
  }

  private void addToFile(Message message){
    if(message==null){
      return;
    }
    BufferedWriter out = null;
    try{
      String filename = "Log-"+message.convertTimestampToDate()+".log";
      out = new BufferedWriter(new FileWriter(filename, true));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        out.close();
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}

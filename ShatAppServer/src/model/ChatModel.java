package model;

import utility.UnnamedPropertyChangeSubject;
public interface ChatModel extends UnnamedPropertyChangeSubject{

    String getMessage();
    String getSender();
    String getTimestamp();



}

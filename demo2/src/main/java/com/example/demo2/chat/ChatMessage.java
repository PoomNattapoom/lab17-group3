package com.example.demo2.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {
  private String content;
  private String timestamp;
  private String sender;
  private MessageType type;
  private int personCount;
  private static int count;

  public static int getAddedPersonCount(){
    return ++count;
  }

  public static int getRemovedPersonCount(){
    return --count;
  }

  protected void setPersonCount(int count){
    personCount = count;
  }


}

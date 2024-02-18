package com.example.demo2.config;

import com.example.demo2.chat.ChatController;
import com.example.demo2.chat.ChatMessage;
import com.example.demo2.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
  private final SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketDisconnect(SessionDisconnectEvent event){
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if(username != null){
      var chatMessage = ChatMessage.builder().type(MessageType.LEAVE).sender(username)
        .personCount(ChatMessage.getRemovedPersonCount()).build();
      messagingTemplate.convertAndSend("/topic/public",chatMessage);
    }
  }
}

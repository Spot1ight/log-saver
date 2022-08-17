package com.example.logsaver.websockets;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class MessageDTO {
    String command;
    String content;
    LocalDateTime createdDate;
}

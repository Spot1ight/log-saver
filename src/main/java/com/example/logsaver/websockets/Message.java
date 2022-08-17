package com.example.logsaver.websockets;

import lombok.Value;

@Value
public class Message {
    String command;
    String content;
}

package com.example.logsaver.websockets;

import lombok.Value;

import java.util.List;

@Value
public class OutputMessage {
    List<MessageDTO> messages;
}

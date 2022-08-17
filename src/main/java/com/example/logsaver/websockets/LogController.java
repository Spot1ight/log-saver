package com.example.logsaver.websockets;

import com.example.logsaver.data.LogData;
import com.example.logsaver.data.LogDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LogController {

    private final LogDataRepository logDataRepository;

    @MessageMapping("/logs")
    public void send(Message message) {
        log.info("Received message: {}", message);
        LogData logData = mapMessageToLogData(message);
        logDataRepository.save(logData);
    }

    @SubscribeMapping("/topic/messages")
    public OutputMessage get() {
        log.info("Handled subscribe messages");
        List<MessageDTO> messages = logDataRepository
                .findAll()
                .stream()
                .map(this::mapLogDataToMessage)
                .collect(Collectors.toList());

        return new OutputMessage(messages);
    }

    private MessageDTO mapLogDataToMessage(LogData logData) {
        return new MessageDTO(logData.getCommand(), logData.getContent(), logData.getCreatedDate());
    }

    private LogData mapMessageToLogData(Message message) {
        return new LogData(message.getCommand(), message.getContent());
    }
}

package com.baydijital.softtech.app.log.service;

import com.baydijital.softtech.app.kafka.dto.LogMessage;
import com.baydijital.softtech.app.log.service.entityService.LogDetailEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Gokalp on 9/20/22
 */
@Service
@RequiredArgsConstructor
public class  LogService {

    @Value("${softtech.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, LogMessage> kafkaTemplate;

    private final LogDetailEntityService logDetailEntityService;

    public void log(LogMessage logMessage){
        String id = UUID.randomUUID().toString();

        kafkaTemplate.send(topic, id, logMessage);
    }
}

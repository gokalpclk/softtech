package com.baydijital.softtech.app.kafka.consumer;

import com.baydijital.softtech.app.kafka.dto.LogMessage;
import com.baydijital.softtech.app.log.converter.LogMapper;
import com.baydijital.softtech.app.log.entity.LogDetail;
import com.baydijital.softtech.app.log.service.entityService.LogDetailEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Gokalp on 9/19/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {

    private final LogDetailEntityService logDetailEntityService;

    @KafkaListener(
            topics = "${softtech.kafka.topic}",
            groupId = "${softtech.kafka.group-id}"
    )
    public void listen(@Payload LogMessage logMessage){
        log.info("Message received  by consumer... " + logMessage.getId() + " - " + logMessage.getMessage());
        saveLogToDb(logMessage);
    }

    private void saveLogToDb(LogMessage logMessage) {
        LogDetail logDetail = LogMapper.INSTANCE.convertToLogDetail(logMessage);
        logDetail = logDetailEntityService.save(logDetail);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("************************************");
        System.out.println("end");
    }
}

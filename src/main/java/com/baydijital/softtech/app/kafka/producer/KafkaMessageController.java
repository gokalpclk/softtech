package com.baydijital.softtech.app.kafka.producer;

import com.baydijital.softtech.app.kafka.dto.LogMessage;
import com.baydijital.softtech.app.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gokalp on 9/19/22
 */
@RestController
@RequestMapping("/kafka-messages")
@RequiredArgsConstructor
public class KafkaMessageController {

    private final LogService logService;

    @PostMapping
    public void sendMessage(@RequestBody LogMessage logMessage) {
        System.out.println("Starting to produce");
        logService.log(logMessage);
        System.out.println("message sent to producer");

    }
}

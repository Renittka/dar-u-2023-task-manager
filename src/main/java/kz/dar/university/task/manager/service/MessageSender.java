package kz.dar.university.task.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.dar.university.task.manager.domain.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.test.topic}")
    private String topicName;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(EmailDTO email) throws JsonProcessingException {
        log.info("EMAIL TO SENT: " + email);
        kafkaTemplate.send(topicName, objectMapper.writeValueAsString(email));
    }

}

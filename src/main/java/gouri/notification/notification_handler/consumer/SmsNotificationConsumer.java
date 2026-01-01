package gouri.notification.notification_handler.consumer;

import gouri.notification.notification_handler.service.SmsSenderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationConsumer {

    private final SmsSenderService senderService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SmsNotificationConsumer(SmsSenderService senderService, KafkaTemplate<String, Object> kafkaTemplate){
        this.senderService = senderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "notification_sms")
    public void consume(NotificationRequest request){
        try{
            senderService.send(request);
        }catch (Exception ex){
            kafkaTemplate.send("notification_sms_dlq", request);
        }
    }
}

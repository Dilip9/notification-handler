package gouri.notification.notification_handler.service;

import gouri.notification.notification_handler.vendor.SmsVendorClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {

    private final SmsVendorClient vendorClient;

    public SmsSenderService(SmsVendorClient smsVendorClient){
        this.vendorClient = smsVendorClient;
    }

    @Retry(name = "notificationHandlerRetry")
    @CircuitBreaker(name = "notificationHandlerCircuitBreaker", fallbackMethod = "fallback")
    public void send(NotificationRequest request){
        vendorClient.sendSMS(request);
    }

    public void fallback(NotificationRequest request, Throwable ex){
        throw new RuntimeException("SMS delivery failed after retries", ex);
    }

}

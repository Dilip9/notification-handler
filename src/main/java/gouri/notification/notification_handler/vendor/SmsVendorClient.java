package gouri.notification.notification_handler.vendor;

import gouri.notification.notification_handler.exceptions.VendorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsVendorClient {

    private final static Logger logger = LoggerFactory.getLogger(SmsVendorClient.class);

    public void sendSMS(NotificationRequest request) {
        // Logic to send SMS via vendor's API
        logger.info("Sending SMS to {} with message: {}", request.getRecipient(), request.getMessage());
        if(request.getRecipient() == null || request.getRecipient().isEmpty()) {
            throw new VendorException("Recipient phone number is required");
        } else if (request.getMessage() == null || request.getMessage().isEmpty()) {
            throw new VendorException("Message content is required");
        }

    }
}

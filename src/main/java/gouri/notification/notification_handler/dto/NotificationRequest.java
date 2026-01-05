package gouri.notification.notification_handler.dto;
import gouri.notification.notification_handler.enums.ChannelType;
import gouri.notification.notification_handler.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequest {
    private String clientId;
    private String userId;
    private ChannelType channelType;
    private String recipient;

    @NotBlank
    private String content;
    private MessageType messageType;
}

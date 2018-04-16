package pl.sternik.as.projekt.services;

import java.util.List;

public interface NotificationService {

    void addInfoMessage(String msg);

    void addErrorMessage(String msg);

    List<NotificationMessage> getNotificationMessages();


    enum NotificationMessageType {
        INFO, ERROR
    }

    class NotificationMessage {
        NotificationMessageType type;
        String text;

        public NotificationMessage(NotificationMessageType type, String text) {
            this.type = type;
            this.text = text;
        }

        public NotificationMessageType getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }
}
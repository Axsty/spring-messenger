package se.iths.axel.springmessenger.service;

import org.springframework.stereotype.Service;
import se.iths.axel.springmessenger.messaging.Messenger;
import se.iths.axel.springmessenger.model.Message;
import se.iths.axel.textformatterlibrary.TextFormatter;

import java.util.Map;

@Service
public class MessageService {

    // String i generics hämtar typen messenger, t.ex email för emailSender
    private final Map<String, Messenger> messengers;
    private final TextFormatter textFormatter;

    public MessageService(Map<String, Messenger> messengers, TextFormatter textFormatter) {
        this.messengers = messengers;
        this.textFormatter = textFormatter;
    }

    public void send(Message message) {
        for (String type : messengers.keySet()) {
            if (message.getType().equals(type)) {
                Messenger messenger = messengers.get(type);
                message.setMessage(textFormatter.toUpperCase(message.getMessage()));
                messenger.send(message);
                return;
            }
        }
    }
}

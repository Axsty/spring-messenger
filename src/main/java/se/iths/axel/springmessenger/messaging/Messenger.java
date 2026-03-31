package se.iths.axel.springmessenger.messaging;

import se.iths.axel.springmessenger.model.Message;

public interface Messenger {

    void send(Message message);
}

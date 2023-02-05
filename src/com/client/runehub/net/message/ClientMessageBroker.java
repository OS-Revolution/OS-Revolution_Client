package com.client.runehub.net.message;

import org.runehub.api.net.message.Message;
import org.runehub.api.net.message.MessageBroker;
import org.runehub.api.net.message.MessageHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ClientMessageBroker implements MessageBroker {

    private static ClientMessageBroker instance = null;

    public static ClientMessageBroker getInstance() {
        if (instance == null)
            instance = new ClientMessageBroker();
        return instance;
    }

    public void registerMessageHandler(String key, MessageHandler<?> handler) {
        messageHandlerMap.putIfAbsent(key,handler);
    }

    @Override
    public void dispatch(Message message) {
        MessageHandler messageHandler = messageHandlerMap.get(message.getType());
        if(message != null) {
            Logger.getGlobal().info("Dispatching Message: " + message);
            if (messageHandler == null) {
                Logger.getGlobal().warning("No Message Listener Available for Message: " + message);
            } else {
                messageHandler.onMessage(message);
            }
        } else {
            Logger.getGlobal().severe("Null Message Received: " + message);
        }
    }

    private ClientMessageBroker() {
        this.messageHandlerMap = new HashMap<>();
    }

    private final Map<String, MessageHandler<?>> messageHandlerMap;
}

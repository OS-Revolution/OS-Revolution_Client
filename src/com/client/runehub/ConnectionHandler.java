package com.client.runehub;

import org.runehub.api.net.Connection;
import org.runehub.api.net.message.Message;
import org.runehub.api.net.message.MessageDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionHandler implements Runnable {

    public static final int SERVER_PORT = 5555;

    @Override
    public void run() {
        try {
            Logger.getGlobal().info("Connection Handler Online");

            String inputLine;
            while ((inputLine = connection.getInput().readLine()) != null) {
                Logger.getGlobal().info("Message Request Received: " + inputLine);
                Class<?> messageClass = Class.forName(MessageDecoder.getInstance().decode(inputLine, Message.class).getType());
                Message<?> message = (Message<?>) MessageDecoder.getInstance().decode(inputLine, messageClass);

                ClientMessageBroker.getInstance().dispatch(message);
            }
        } catch (IOException e) {
            this.stop();
            Logger.getGlobal().severe("Connection Dropped: " + clientSocket.getInetAddress().getHostAddress());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            Connection connection = new Connection(clientSocket.getInetAddress().getHostAddress());

            connection.setOutput(new PrintWriter(clientSocket.getOutputStream(), true));
            connection.setInput(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));

            this.connection = connection;
//            User.getInstance().getAttributes().setConnection(connection);
//            Server.getInstance().getConnections().add(new Connection(clientSocket.getInetAddress().getHostAddress()));
            Logger.getGlobal().info("Connection Accepted: " + clientSocket.getLocalAddress());
        } catch (Exception e) {
            Logger.getGlobal().severe("Could not listen on port: " + SERVER_PORT);
        }
    }

    public void stop() {
        try {
            System.out.println("Shutting down");
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getGlobal().severe("Failed to shutdown login service thread.");
        }
    }

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.start();
    }

    private Socket clientSocket;
    private Connection connection;

}

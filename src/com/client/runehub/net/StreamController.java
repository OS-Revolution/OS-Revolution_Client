package com.client.runehub.net;

import org.runehub.api.net.message.Message;
import org.runehub.api.net.message.MessageEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class StreamController {

    public <M extends Message<?>> void write(M message) {
        try {
            Logger.getGlobal().info("Writing Message: " + message);
            output.println(MessageEncoder.getInstance().encode(message));
            output.flush();
        } catch (Exception e) {
            Logger.getGlobal().severe("Failed to write message: " + e.getMessage());
        }
    }

    public String read() {
        try {
            return input.readLine();
        } catch (Exception e) {
            Logger.getGlobal().severe("Failed to read message: " + e.getMessage());
        }
        return null;
    }

    public void stop() {
        try {
            input.close();
            output.close();
            clientSocket.close();
        } catch (Exception e) {
            Logger.getGlobal().severe("Failed to shutdown server: " + e.getMessage());
        }
    }

    public StreamController() {
        try {
            this.clientSocket = new Socket("localhost", ConnectionHandler.SERVER_PORT);
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Logger.getGlobal().info("Connection Accepted: " + clientSocket.getLocalAddress());
        } catch (Exception e) {
            Logger.getGlobal().severe("Failed to connect to server: " + e.getMessage());
        }
     }

    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
}

package com.client.runehub;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunehubFileServer implements Runnable {

    private static RunehubFileServer instance = null;

    public static RunehubFileServer getInstance() {
        if (instance == null)
            instance = new RunehubFileServer();
        return instance;
    }

    //    public static void main(String[] args) {
//        try {
//            final RunehubFileServer runehub = new RunehubFileServer();
//            runehub.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void run() {
        try {
            boolean online = false;
            String host;

            if (online) {
                host = "70.179.169.220";
            } else {
                host = "localhost";
            }

            connectionHandlerService.submit(new ConnectionHandler(new Socket(host, ConnectionHandler.SERVER_PORT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RunehubFileServer() {
//        ClientMessageBroker.getInstance().registerMessageHandler(LoginSuccessMessage.class.getName(), new LoginSuccessMessageHandler());
    }

    private final ExecutorService connectionHandlerService = Executors.newSingleThreadExecutor();
}

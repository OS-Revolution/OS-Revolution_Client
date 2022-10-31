package com.client.runehub;

import com.client.Client;
import com.client.Configuration;
import com.client.DrawingArea;
import com.client.Rasterizer;
import com.client.features.gameframe.ScreenMode;
import org.runehub.api.util.SkillDictionary;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Console {


    private final int inputLimit = 80; //Input limit for the command box
    private final int height = 140; //Default height of the client
    private int keyPosition = 0;  //Position of the char input
    private int historyPosition = -1;  //Position of the char input
    public boolean consoleOpen;  //If the console is open
    private String consoleInput = ""; //Console Input string
    private ArrayList<String> consoleInputs = new ArrayList<>();  //List of past inputs
    private ArrayList<String> consoleMessages = new ArrayList<>();  //List of messages in the console that was sent
    private final Client client; //Instance of the Client
    private final String startMessage = "This is the developer console. To close, press the ` key on your keyboard.";
    public boolean extendingConsole;
    public int extraConsoleY;

    /**
     * Initialize the console
     * @Param client Client class
     */
    public Console(Client client) {
        this.client = client;
//        CommandManager.loadCommands();
        consoleMessages.add(startMessage);
    }

    /**
     *  Draws the console on screen
     */
    public void drawConsole() {
        if (consoleOpen) {
            Rasterizer.drawTransparentBox(0, 0, Client.gameScreenWidth, height + extraConsoleY, 5320850, 200);
            Rasterizer.drawHorizontalLine(0, height - 15 + extraConsoleY, Client.gameScreenWidth, 0x979797);
            Rasterizer.drawHorizontalLine(0, height + extraConsoleY, Client.gameScreenWidth, 0x979797);
            client.newSmallFont.drawBasicString("> " + consoleInput, 10, height - 3 + extraConsoleY, 0xFFFFFF, 0);
//            Rasterizer.drawCircle(200, 100, 40, RunehubUtils.RS2HSB_to_RGB(RunehubUtils.getBaseColorForSkill(SkillDictionary.Skill.THIEVING)));
            client.newSmallFont.drawBasicString(((Client.instance.tickDelta % 40 < 20) ? "_" : ""), 10 + client.newSmallFont.getTextWidth("> " + consoleInput), height - 2 + extraConsoleY, 0xFFFFFF, 0);


            if(Client.instance.clickMode3 == 1 &&  Client.instance.clickInRegion(0,0,Client.gameScreenWidth,height + extraConsoleY)) {
                extendingConsole = true;
            }


            Rasterizer.setDrawingArea(height - 18 + extraConsoleY, 0, Client.gameScreenWidth, 4);
            int yPos = height - 20;
            for (int index = consoleMessages.size() - 1; index >= 0; index--) {
                client.newSmallFont.drawBasicString(consoleMessages.get(index), 10, yPos + extraConsoleY, 0xFFFFFF, 0);
                yPos -= 13;

            }
            Rasterizer.defaultDrawingAreaSize();
        }
    }


    public void processExtend() {

        if (extendingConsole) {
            if(Client.currentScreenMode == ScreenMode.FIXED) {
                if(Client.instance.mouseY <= Client.gameScreenHeight - 150) {
                    extraConsoleY = Client.instance.mouseY;
                }
            }
        }

    }

    /**
     *  Manages the inputs for this console
     */
    private void handleConsoleInput() {
        String input = consoleInput;
        if(!input.equals("")) {
            consoleInputs.add(input);
            consoleInput = "";
            String[] parts = input.split(" ");
            parts[0] = parts[0].toLowerCase();
            sendMessage(input);
            Client.stream.createFrame(103);
            Client.stream.writeWordBigEndian(input.length() + 1);
            Client.stream.writeString(input);
//            Command command = CommandManager.commands.get(parts[0]);
//            if (command != null) {
//                if (command.canUse(client)) {
//                    consoleMessages.add("> " + input);
//                    command.execute(this, input, parts);
//                }
//            } else {
//                sendMessage("This command does not exist.");
//            }
        }
    }

    /**
     * Sends a command message to the user
     * @Param message Message sent to the console
     */
    public void sendMessage(String message) {
        consoleMessages.add(message);
    }

    /**
     * Manages the key inputs for this console
     * @Param keyEvent key event
     */
    public void parseKeyForConsole(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if(consoleInput.length() > 0 && keyPosition != 0) {
                StringBuilder sb = new StringBuilder(consoleInput);
                sb.deleteCharAt(keyPosition - 1);
                consoleInput = sb.toString();
                keyPosition--;
            }
        } else if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            handleConsoleInput();
            keyPosition = 0;
            historyPosition = 0;
        } else if(keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_V) {
            try {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable contents = clipboard.getContents(null);
                boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
                if(hasTransferableText) {
                    System.out.println("Paste Text");
                    consoleInput += (String)contents.getTransferData(DataFlavor.stringFlavor);
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if(keyPosition != 0) {
                keyPosition--;
            }
        } else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(keyPosition != consoleInput.length()) {
                keyPosition++;
            }
        } else if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if(consoleInputs != null) {
                if(consoleInputs.size() != 0 &&  consoleInputs.size() > historyPosition) {
                    consoleInput = consoleInputs.get(historyPosition + 1);
                    historyPosition += 1;
                    keyPosition = consoleInputs.get(historyPosition + 1).length();
                }
            }
        } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if(consoleInputs != null) {
                if(consoleInputs.size() != 0 &&  consoleInputs.size() > historyPosition) {
                    consoleInput = consoleInputs.get(historyPosition - 1);
                    historyPosition -= 1;
                    keyPosition = consoleInputs.get(historyPosition - 1).length();
                }
            }
        } else {
            if (keyEvent.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                if(consoleInput.length() <= inputLimit) {
                    StringBuilder sb = new StringBuilder(consoleInput);
                    sb.insert(keyPosition,keyEvent.getKeyChar());
                    keyPosition++;
                    consoleInput = sb.toString();
                }
            }
        }
    }


}

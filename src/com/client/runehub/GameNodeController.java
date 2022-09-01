package com.client.runehub;

import com.client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameNodeController {

    private static final String GAME_NODE_FILE_LOCATION = "./data/game-nodes.json";

    private static GameNodeController instance = null;

    public static GameNodeController getInstance() {
        if (instance == null)
            instance = new GameNodeController();
        return instance;
    }

    public void addGameNode(GameNode gameNode) {
        gameNodeList.getGameNodes().add(gameNode);
        this.save();
    }

    public void initialize() {
        gameNodeList.getGameNodes().forEach(gameNode -> Client.getInstance().addObject(
                gameNode.getId(),
                gameNode.getX(),
                gameNode.getY(),
                gameNode.getFace(),
                gameNode.getType(),
                gameNode.getZ()
        ));
    }

    private void save() {
        serializer.write(new File(GAME_NODE_FILE_LOCATION), serializer.serialize(gameNodeList));
    }

    private GameNodeList load() {
        final File file = new File(GAME_NODE_FILE_LOCATION);
        try {
            return serializer.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GameNodeController() {
        this.serializer = new GameNodeSerializer();
        this.gameNodeList = this.load();
    }

    private final GameNodeList gameNodeList;
    private final GameNodeSerializer serializer;
}

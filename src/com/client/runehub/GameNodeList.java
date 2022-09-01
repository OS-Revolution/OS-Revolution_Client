package com.client.runehub;

import java.util.ArrayList;
import java.util.List;

public class GameNodeList {

    public List<GameNode> getGameNodes() {
        return gameNodes;
    }

    public GameNodeList() {
        this.gameNodes = new ArrayList<>();
    }

    private final List<GameNode> gameNodes;
}

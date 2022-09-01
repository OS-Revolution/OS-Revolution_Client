package com.client.runehub;

import org.runehub.api.io.load.JsonSerializer;

public class GameNodeSerializer extends JsonSerializer<GameNodeList> {

    public GameNodeSerializer() {
        super(GameNodeList.class);
    }
}

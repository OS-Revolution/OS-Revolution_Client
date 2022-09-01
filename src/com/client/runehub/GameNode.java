package com.client.runehub;

public class GameNode {

    public int getId() {
        return id;
    }

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getFace() {
        return face;
    }

    public int getType() {
        return type;
    }

    public GameNode(int id, int x, int y, int z, int face, int type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
        this.type = type;
    }

    private final int id;
    private final int x;
    private final int y;
    private final int z;
    private final int face;
    private final int type;
}

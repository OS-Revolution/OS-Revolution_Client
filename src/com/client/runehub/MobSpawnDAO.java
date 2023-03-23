package com.client.runehub;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class MobSpawnDAO extends BetaAbstractDataAcessObject<MobSpawn> {

    private static MobSpawnDAO instance = null;

    public static MobSpawnDAO getInstance() {
        if (instance == null)
            instance = new MobSpawnDAO();
        return instance;
    }

    private MobSpawnDAO() {
        super("C:\\Users\\quint\\Desktop\\rsps\\OS-Revolution\\Server\\Data\\runehub\\db\\os-definitions.db", MobSpawn.class);
    }
}

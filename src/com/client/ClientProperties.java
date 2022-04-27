package com.client;

import java.util.Properties;
import java.util.ResourceBundle;

public class ClientProperties {

    private static ClientProperties instance = null;

    public static ClientProperties getInstance() {
        if (instance == null)
            instance = new ClientProperties();
        return instance;
    }

    public Object getPropertyValue(String key) {
        if (properties.containsKey(key))
            return properties.getObject(key);
        throw new NullPointerException("No Such Key: " + key);
    }

    public String getCacheName() {
        return (String) this.getPropertyValue("cache_name");
    }

    public String getCacheLink() {
        return (String) this.getPropertyValue("cache_link");
    }

    public String getCacheVersionLink() {
        return (String) this.getPropertyValue("cache_version_url");
    }

    public String getGameName() {
        return (String) this.getPropertyValue("game_name");
    }

    public String getClientIcon() {
        return (String) this.getPropertyValue("client_icon");
    }

    public String getServerAddress() {
        return (String) this.getPropertyValue("server_address");
    }

    public int getServerPort() {
        return Integer.parseInt((String) this.getPropertyValue("server_port"));
    }

    private ClientProperties() {
        this.properties = ResourceBundle.getBundle("config");
    }

    private final ResourceBundle properties;
}

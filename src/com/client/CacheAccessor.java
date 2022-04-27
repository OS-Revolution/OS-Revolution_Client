package com.client;

import java.io.*;

public class CacheAccessor {

    public void initializeCache() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getuid(String s) {
        try {
            File file = new File(s + "uid.dat");
            if (!file.exists() || file.length() < 4L) {
                DataOutputStream dataoutputstream = new DataOutputStream(
                        new FileOutputStream(s + "uid.dat"));
                dataoutputstream.writeInt((int) (Math.random() * 99999999D));
                dataoutputstream.close();
            }
        } catch (Exception _ex) {
        }
        try {
            DataInputStream datainputstream = new DataInputStream(
                    new FileInputStream(s + "uid.dat"));
            int i = datainputstream.readInt();
            datainputstream.close();
            return i + 1;
        } catch (Exception _ex) {
            return 0;
        }
    }
}

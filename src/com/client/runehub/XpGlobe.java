package com.client.runehub;

import java.awt.image.BufferedImage;

public class XpGlobe {

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BufferedImage getSkillIcon() {
        return skillIcon;
    }

    public void setSkillIcon(BufferedImage skillIcon) {
        this.skillIcon = skillIcon;
    }

    public XpGlobe(int skill, int currentXp, int currentLevel)
    {
        this.skill = skill;
        this.currentXp = currentXp;
        this.currentLevel = currentLevel;
    }

    private int skill;
    private int currentXp;
    private int currentLevel;
    private int size;
    private BufferedImage skillIcon;
}

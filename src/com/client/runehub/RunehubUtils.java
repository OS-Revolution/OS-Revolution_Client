package com.client.runehub;

import com.client.graphics.interfaces.runehub.Rune;
import org.runehub.api.util.SkillDictionary;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunehubUtils {


    public static int[] rsToRGB(int rs) {
        if (rs < 0) {
            rs += 1;
        }
        rs %= 0xFFFF;
        int hue = ((rs >> 10) & 63) / 63;
        int sat = ((rs >> 7) & 7) / 7;
        int bri = (rs & 127) / 127;
        return new int[0];
    }

    public static int RGB_to_RS2HSB(int red, int green, int blue) {
        float[] HSB = Color.RGBtoHSB(red, green, blue, null);
        float hue = (HSB[0]);
        float saturation = (HSB[1]);
        float brightness = (HSB[2]);
        int encode_hue = (int) (hue * 63);			//to 6-bits
        int encode_saturation = (int) (saturation * 7);		//to 3-bits
        int encode_brightness = (int) (brightness * 127); 	//to 7-bits
        return (encode_hue << 10) + (encode_saturation << 7) + (encode_brightness);
    }

    public static int RS2HSB_to_RGB(int RS2HSB) {
        int decode_hue = (RS2HSB >> 10) & 0x3f;
        int decode_saturation = (RS2HSB >> 7) & 0x07;
        int decode_brightness = (RS2HSB & 0x7f);
        return Color.HSBtoRGB((float)decode_hue/63, (float)decode_saturation/7, (float)decode_brightness/127);
    }

    public static Color getSkillColor(int skill) {
        return new Color(RS2HSB_to_RGB(getBaseColorForSkill(skill)));
    }

    public static int getBaseColorForSkill(SkillDictionary.Skill skill) {
        switch (skill) {
            case ATTACK:
                return 810;
            case DEFENCE:
                return -30260;
            case STRENGTH:
                return 23340;
            case HITPOINTS:
                return -4300;
            case RANGED:
                return 14133;
            case PRAYER:
                return 10707;
            case MAGIC:
                return -21700;
            case COOKING:
                return -18628;
            case WOODCUTTING:
                return 23721;
            case FLETCHING:
                return -30802;
            case FISHING:
                return -31178;
            case FIREMAKING:
                return 8014;
            case CRAFTING:
                return 9397;
            case SMITHING:
                return 9397;
            case MINING:
                return 7208;
            case HERBLORE:
                return 22344;
            case AGILITY:
                return -23878;
            case THIEVING:
                return -18884;
            case SLAYER:
                return -1753;
            case FARMING:
                return 26038;
            case RUNECRAFTING:
                return 10815;
            case HUNTER:
                return 11304;
            case CONSTRUCTION:
                return 9151;
        }
        return 11187;
    }

    public static int getBaseColorForRune(Rune runeId) {
        switch (runeId) {
            case AIR: //air
                return 127;
            case MIND://mind
                return 5056;
            case WATER: //water
                return 43945;
            case EARTH: //earth
                return 6036;
            case FIRE: //fire
                return 937;
            case BODY: //body
                return 43961;
            case COSMIC: //cosmic
                return 11200;
            case CHAOS: // chaos
                return 7104;
            case ASTRAL: //astral
                return 54503;
            case NATURE: //nature
                return 22430;
            case LAW: //law
                return 43968;
            case DEATH: //death
                return 127;
            case BLOOD: //blood
                return 926;
            case SOUL: //soul
                return 43484;
            case WRATH: //wrath
                return 7208;
        }
        return 11187;
    }

    public static int getBaseColorForSkill(int skill) {
       if (Arrays.stream(SkillDictionary.Skill.values()).anyMatch(skill1 -> skill1.getId() == skill))
           return getBaseColorForSkill(SkillDictionary.Skill.values()[skill]);
        return 11187;
    }

    public static String capitalize(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                if (i + 1 < s.length()) {
                    s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)), s.substring(i + 2));
                }
            }
        }
        return s;
    }

    public static String getSkillName(int id) {
        if (id >= 0 && id <= 22) {
            return capitalize(SkillDictionary.getSkillNameFromId(id).toLowerCase());
        }
        return "Sailing";
    }


}

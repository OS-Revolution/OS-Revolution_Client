package com.client.definitions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.client.*;
import com.client.sign.Signlink;
import com.client.utilities.FileOperations;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

public final class ItemDefinition {
    public byte[] customSpriteLocation;
    private final static List<Integer> starIds = Arrays.asList(22009, 22010, 22011, 22012, 22013, 22014, 22015, 22016, 22017, 22018, 22019, 22020, 22021, 22022, 22023, 22024, 22025, 22026, 22027, 22028, 22029, 22030, 22031, 22032, 22033, 22034, 22035, 22036, 22037, 22038, 22039, 22040, 22041, 22042, 22043, 22044, 22045, 22046, 22047, 22048, 22049, 22050, 22051, 22052, 22053, 22054, 22055, 22056, 22057, 22058, 22059, 22060, 22061, 22062, 22063, 22064, 22065, 22066, 22067, 22068, 22069, 22070, 22071, 22072, 22073, 22074, 22075, 22076, 22077, 22078, 22079, 22080, 22081, 22082, 22083, 22084, 22085, 22086, 22087, 22088, 22089, 22090, 22091, 22092,22191,22227,22228,22229,22230,21873,21874,21875);
    private static List<ItemDefinition> starDefinitions = new ArrayList<>();


    public static void unpackConfig(final StreamLoader streamLoader) {
        // stream = new Stream(streamLoader.getDataForName("obj.dat"));
        // Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
        stream = new Stream(FileOperations.readFile(Signlink.getCacheDirectory() + "/data/obj.dat"));
        final Stream stream = new Stream(FileOperations.readFile(Signlink.getCacheDirectory() + "/data/obj.idx"));

        totalItems = stream.readUnsignedWord();
        System.out.println("Total Item: " + totalItems);
        streamIndices = new int[totalItems + 1000];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }

        cache = new ItemDefinition[10];
        for (int index = 0; index < 10; index++) {
            cache[index] = new ItemDefinition();
        }
//        itemDump();
        generateStars(starIds);
    }

    public static ItemDefinition forID(int itemId) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == itemId) {
                return cache[j];
            }
        }

        if (itemId == -1)
            itemId = 0;
        if (itemId > streamIndices.length)
            itemId = 0;

        cacheIndex = (cacheIndex + 1) % 10;
        final ItemDefinition itemDef = cache[cacheIndex];
        stream.currentOffset = streamIndices[itemId];
        itemDef.id = itemId;
        itemDef.setDefaults();
        itemDef.readValues(stream);

        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }

        if (itemDef.opcode140 != -1) {
            itemDef.method2789(forID(itemDef.opcode140), forID(itemDef.opcode139));
        }

        if (itemDef.opcode149 != -1) {
            itemDef.method2790(forID(itemDef.opcode149), forID(itemDef.opcode148));
        }

//        List<Integer> ids = new ArrayList<>();
//        for (int i = 22009; i < 22093; i++) {
//            ids.add(i);
//        }
//        System.out.println(ids);


        customItems(itemDef.id);

        switch (itemId) {

        }
        return itemDef;
    }

    private static void createCoinBasedCurrency(int[] stackAmounts, int[] stackIDs, String name, String description, ItemDefinition itemDef, int newColor) {
        itemDef.name = name;
        itemDef.modelId = 2484;
        itemDef.description = description;
        itemDef.stackAmounts = stackAmounts;
        itemDef.stackIDs = stackIDs;
        itemDef.originalModelColors = new int[]{newColor};
        itemDef.modifiedModelColors = new int[]{8128};
        itemDef.stackable = true;
        itemDef.modelZoom = 710;
        itemDef.modelOffset1 = 3;
        itemDef.modelOffset2 = 0;
        itemDef.modelRotation1 = 184;
        itemDef.modelRotation2 = 2012;
    }

    private static void createCoinBasedCurrencyStack(ItemDefinition definition, String name, String description, int newColor, int stackAmount) {
        definition.name = name;

        definition.description = description;
        definition.originalModelColors = new int[]{newColor};
        definition.modifiedModelColors = new int[]{8128};
        definition.stackable = true;

        if (stackAmount == 2) {
            definition.modelId = 2485;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 3) {
            definition.modelId = 2486;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 4) {
            definition.modelId = 2487;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 5) {
            definition.modelId = 2488;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 25) {
            definition.modelId = 2667;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 100) {
            definition.modelId = 2825;
            definition.modelZoom = 710;
            definition.modelRotation1 = 184;
            definition.modelRotation2 = 2012;
            definition.modelOffset1 = 3;
            definition.modelOffset2 = 0;

        } else if (stackAmount == 250) {
            definition.modelId = 2423;
            definition.modelZoom = 650;
            definition.modelRotation1 = 160;
            definition.modelRotation2 = 2044;
            definition.modelOffset1 = 2;
            definition.modelOffset2 = -2;

        } else if (stackAmount == 1000) {
            definition.modelId = 2710;
            definition.modelZoom = 980;
            definition.modelRotation1 = 172;
            definition.modelRotation2 = 64;
            definition.modelOffset1 = 11;
            definition.modelOffset2 = 13;

        } else if (stackAmount == 10000) {
            definition.modelId = 2775;
            definition.modelZoom = 1000;
            definition.modelRotation1 = 168;
            definition.modelRotation2 = 80;
            definition.modelOffset1 = 11;
            definition.modelOffset2 = 0;

        }
    }


    private static void copyDef(ItemDefinition newDefinition, ItemDefinition baseDefinition) {
        newDefinition.name = baseDefinition.name;
        newDefinition.modelId = baseDefinition.modelId;
        newDefinition.description = baseDefinition.description;
        newDefinition.stackAmounts = baseDefinition.stackAmounts;
        newDefinition.stackIDs = baseDefinition.stackIDs;
        newDefinition.originalModelColors = baseDefinition.originalModelColors;
        newDefinition.modifiedModelColors = baseDefinition.modifiedModelColors;
        newDefinition.stackable = baseDefinition.stackable;
        newDefinition.modelZoom = baseDefinition.modelZoom;
        newDefinition.modelOffset1 = baseDefinition.modelOffset1;
        newDefinition.modelOffset2 = baseDefinition.modelOffset2;
        newDefinition.modelRotation1 = baseDefinition.modelRotation1;
        newDefinition.modelRotation2 = baseDefinition.modelRotation2;
        newDefinition.inventoryOptions = baseDefinition.inventoryOptions;
        newDefinition.equipActions = baseDefinition.equipActions;
        newDefinition.maleModel = baseDefinition.maleModel;
        newDefinition.femaleModel = baseDefinition.femaleModel;
    }

    private static ItemDefinition createStar(ItemDefinition definition, String name, String description, int newColor) {
        copyDef(definition, ItemDefinition.forID(6824));
        definition.name = name;
        definition.description = description;
        definition.inventoryOptions = new String[]{"Select Skill", null, null, null, null};
        definition.originalModelColors = new int[]{newColor};
        definition.modifiedModelColors = new int[]{11187};
        return definition;
    }

    private static String capitalize(String s) {
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

    private static void generateStar(ItemDefinition def, int skillId, int tier) {
        try {
            SkillDictionary.Skill skill = SkillDictionary.Skill.values()[skillId];
            String skillName = skill.name();
            if (tier == 0) {
                createStar(def, "Dull " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill));
            } else if (tier == 1) {
                createStar(def, capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 10);
            } else if (tier == 2) {
                createStar(def, "Shining " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 20);
            } else if (tier == 3) {
                createStar(def, "Glorious " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 40);
            }

            System.out.println("Making " + def.name + " ID: " + def.id);
        } catch (NullPointerException e) {
            System.out.println("Missing Skill for: " + skillId);
        }
    }

    private static void generateStars(List<Integer> nullDefinitions) {
        int skillId = 0;
        int tier = 0;
        int index =0;
        for (int i = 1; i < nullDefinitions.size(); i++) {
            try {
                SkillDictionary.Skill skill = SkillDictionary.Skill.values()[skillId];
                String skillName = skill.name();
                ItemDefinition def = new ItemDefinition();
                def.readValues(stream);
                def.id = nullDefinitions.get(index);

                if (tier == 0) {
                    createStar(def, "Dull " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill));
                } else if (tier == 1) {
                    createStar(def, capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 10);
                } else if (tier == 2) {
                    createStar(def, "Shining " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 20);
                } else if (tier == 3) {
                    createStar(def, "Glorious " + capitalize(skillName.toLowerCase()) + " Star", "A Strange Star.", getBaseColorForSkill(skill) + 40);
                }

                tier++;
                index++;
//                System.out.println("Making " + def.name + " ID: " + def.id);
                starDefinitions.add(def);
                if (i % 4 == 0 && i != 0) {
                    skillId++;
                    tier = 0;
                }
            } catch (NullPointerException e) {
                System.out.println("Missing Skill for: " + i);
            }
        }
    }


    private static int getBaseColorForSkill(SkillDictionary.Skill skill) {
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

    private static List<Integer> findNullItems(int total) {
        List<Integer> nullItems = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            ItemDefinition obj = ItemDefinition.forID(i);

            if (obj.name == null && nullItems.size() < total) {
                nullItems.add(obj.id);
            }

        }
        return nullItems;
    }


    private static void customItems(int itemId) {
        ItemDefinition itemDef = forID(itemId);

        if ((itemId >= 22009 && itemId <= 22092) || starIds.contains(itemId)) {
            ItemDefinition starDef = starDefinitions.stream().filter(definition -> definition.id == itemId).findAny().orElse(null);
            copyDef(itemDef, ItemDefinition.forID(6824));
            itemDef.name = starDef.name;
            itemDef.description = starDef.description;
            itemDef.inventoryOptions = starDef.inventoryOptions;
            itemDef.originalModelColors = starDef.originalModelColors;
            itemDef.modifiedModelColors = starDef.modifiedModelColors;
        }

        switch (itemId) {
            case 7622:
                copyDef(itemDef,forID(7622));
                itemDef.name = "Ultracompost";
                break;
            case 7623:
                copyDef(itemDef,forID(7623));
                itemDef.name = "Ultracompost";
                break;
            case 7624:
                copyDef(itemDef,forID(7624));
                itemDef.name = "Bottomless compost bucket";
                itemDef.inventoryOptions = new String[]{null, "Empty", "Check", null, null};
                itemDef.description = "Can store up to 10,000 compost charges.";
                break;
            case 13658:
                copyDef(itemDef,forID(13658));
                itemDef.name = "Deck of Cards";
                itemDef.description = "A deck of playing cards.";
                break;
            case 21354:
                copyDef(itemDef,forID(21354));
                itemDef.name = "Feather fan";
                itemDef.description = "An ornate fan made from the feathers of Tengu it is capable of controlling wind";
                break;
            case 20035:
                copyDef(itemDef,forID(20035));
                itemDef.name = "Tetsu kasa";
                itemDef.originalModelColors = new int[] {30385,8920,920};
                itemDef.modifiedModelColors = new int[] {8497,8617,10378};
                break;
            case 20038:
                copyDef(itemDef,forID(20038));
                itemDef.name = "Tetsu body";
                itemDef.originalModelColors = new int[] {30385,30385,8920,920,30385,8800,8920,8920,30385,8920};
                itemDef.modifiedModelColors = new int[] {8497,8737,63250,22,8617,63002,9019,62348,30,9135};
                break;
            case 20041:
                copyDef(itemDef,forID(20041));
                itemDef.name = "Tetsu kote";
                itemDef.originalModelColors = new int[] {53306,33535,30385,8920,33279,32339,32639};
                itemDef.modifiedModelColors = new int[] {63250,9028,22,63002,9019,9164,9135};
                break;
            case 20044:
                copyDef(itemDef,forID(20044));
                itemDef.name = "Tetsu platelegs";
                itemDef.originalModelColors = new int[] {30385,920,8920,30385,30385,30385,30385,8920,30385,30385,30385};
                itemDef.modifiedModelColors = new int[] {8497,9737,63250,18,22,39,8617,63002,9019,30,142};
                break;
            case 21396:
                copyDef(itemDef,forID(12357));
                itemDef.name = "Wind Blade";
                itemDef.originalModelColors = new int[] {32639,43531,8808};
                itemDef.modifiedModelColors = new int[] {8644,32836,38792};
                break;
            case 20047:
                copyDef(itemDef,forID(20047));
                itemDef.name = "Tetsu kogake";
                itemDef.originalModelColors = new int[] {30385,30385,8920,920,920,30385,8920};
                itemDef.modifiedModelColors = new int[] {8497,63250,22,8617,8377,63002,30};
                break;
            case 5553:
                ItemDefinition rogueChest = forID(5553);
                itemDef.name = "Death Lotus Assassin Chestplate";
                itemDef.originalModelColors = new int[] {900,900,900,900,925,900};
                itemDef.modifiedModelColors = new int[] {49,33,10378,138,6430,10270};
                itemDef.modelId = rogueChest.modelId;
                itemDef.description = rogueChest.description;
                itemDef.stackAmounts = rogueChest.stackAmounts;
                itemDef.stackIDs = rogueChest.stackIDs;
                itemDef.stackable = rogueChest.stackable;
                itemDef.modelZoom = rogueChest.modelZoom;
                itemDef.modelOffset1 = rogueChest.modelOffset1;
                itemDef.modelOffset2 = rogueChest.modelOffset2;
                itemDef.modelRotation1 = rogueChest.modelRotation1;
                itemDef.modelRotation2 = rogueChest.modelRotation2;
                itemDef.inventoryOptions = rogueChest.inventoryOptions;
                itemDef.equipActions = rogueChest.equipActions;
                itemDef.maleModel = rogueChest.maleModel;
                itemDef.femaleModel = rogueChest.femaleModel;
                itemDef.originalTextureColors = new short[] {40};
                itemDef.originalTextureColors = new short[] {0};
                break;
            case 9055:
                ItemDefinition rogueHood = forID(5554);
                itemDef.name = "Death Lotus Assassin Hood";
                itemDef.originalModelColors = new int[] {925,0,925};
                itemDef.modifiedModelColors = new int[] {10258,10378,6430};
                itemDef.modelId = rogueHood.modelId;
                itemDef.description = rogueHood.description;
                itemDef.stackAmounts = rogueHood.stackAmounts;
                itemDef.stackIDs = rogueHood.stackIDs;
                itemDef.stackable = rogueHood.stackable;
                itemDef.modelZoom = rogueHood.modelZoom;
                itemDef.modelOffset1 = rogueHood.modelOffset1;
                itemDef.modelOffset2 = rogueHood.modelOffset2;
                itemDef.modelRotation1 = rogueHood.modelRotation1;
                itemDef.modelRotation2 = rogueHood.modelRotation2;
                itemDef.inventoryOptions = rogueHood.inventoryOptions;
                itemDef.equipActions = rogueHood.equipActions;
                itemDef.maleModel = rogueHood.maleModel;
                itemDef.femaleModel = rogueHood.femaleModel;
                break;
            case 9056:
                ItemDefinition rogueChaps = forID(5555);
                itemDef.name = "Death Lotus Assassin Chaps";
                itemDef.originalModelColors = new int[] {0,925,900,925,900,925};
                itemDef.modifiedModelColors = new int[] {49,10258,10378,9100,6430,10270};
                itemDef.modelId = rogueChaps.modelId;
                itemDef.description = rogueChaps.description;
                itemDef.stackAmounts = rogueChaps.stackAmounts;
                itemDef.stackIDs = rogueChaps.stackIDs;
                itemDef.stackable = rogueChaps.stackable;
                itemDef.modelZoom = rogueChaps.modelZoom;
                itemDef.modelOffset1 = rogueChaps.modelOffset1;
                itemDef.modelOffset2 = rogueChaps.modelOffset2;
                itemDef.modelRotation1 = rogueChaps.modelRotation1;
                itemDef.modelRotation2 = rogueChaps.modelRotation2;
                itemDef.inventoryOptions = rogueChaps.inventoryOptions;
                itemDef.equipActions = rogueChaps.equipActions;
                itemDef.maleModel = rogueChaps.maleModel;
                itemDef.femaleModel = rogueChaps.femaleModel;
                break;
            case 9057:
                ItemDefinition rogueBoots = forID(5556);
                itemDef.name = "Death Lotus Assassin Tekoh";
                itemDef.originalModelColors = new int[] {0,925,0,0};
                itemDef.modifiedModelColors = new int[] {49,6569,10378,6430};
                itemDef.modelId = rogueBoots.modelId;
                itemDef.description = rogueBoots.description;
                itemDef.stackAmounts = rogueBoots.stackAmounts;
                itemDef.stackIDs = rogueBoots.stackIDs;
                itemDef.stackable = rogueBoots.stackable;
                itemDef.modelZoom = rogueBoots.modelZoom;
                itemDef.modelOffset1 = rogueBoots.modelOffset1;
                itemDef.modelOffset2 = rogueBoots.modelOffset2;
                itemDef.modelRotation1 = rogueBoots.modelRotation1;
                itemDef.modelRotation2 = rogueBoots.modelRotation2;
                itemDef.inventoryOptions = rogueBoots.inventoryOptions;
                itemDef.equipActions = rogueBoots.equipActions;
                itemDef.maleModel = rogueBoots.maleModel;
                itemDef.femaleModel = rogueBoots.femaleModel;
                break;
            case 9058:
                ItemDefinition rogueGloves = forID(5557);
                itemDef.name = "Death Lotus Assassin Tabi";
                itemDef.originalModelColors = new int[] {900,925};
                itemDef.modifiedModelColors = new int[] {10378,6430};
                itemDef.modelId = rogueGloves.modelId;
                itemDef.description = rogueGloves.description;
                itemDef.stackAmounts = rogueGloves.stackAmounts;
                itemDef.stackIDs = rogueGloves.stackIDs;
                itemDef.stackable = rogueGloves.stackable;
                itemDef.modelZoom = rogueGloves.modelZoom;
                itemDef.modelOffset1 = rogueGloves.modelOffset1;
                itemDef.modelOffset2 = rogueGloves.modelOffset2;
                itemDef.modelRotation1 = rogueGloves.modelRotation1;
                itemDef.modelRotation2 = rogueGloves.modelRotation2;
                itemDef.inventoryOptions = rogueGloves.inventoryOptions;
                itemDef.equipActions = rogueGloves.equipActions;
                itemDef.maleModel = rogueGloves.maleModel;
                itemDef.femaleModel = rogueGloves.femaleModel;
                break;
            case 6822:
                createStar(itemDef, "Dull Prismatic Star", "A strange star", 11187);
                break;
            case 6823:
                createStar(itemDef, "Prismatic Star", "A strange star", 10070);
                break;
            case 6824:
                createStar(itemDef, "Shining Prismatic Star", "A strange star", 10089);
                break;
            case 6825:
                createStar(itemDef, "Glorious Prismatic Star", "A strange star", 10239);
                break;
//            case 271:
//                createStar(itemDef,"Dull Attack Star","A strange star",810);
//                break;
//            case 272:
//                createStar(itemDef,"Attack Star","A strange star",820);
//                break;
//            case 274:
//                createStar(itemDef,"Shining Attack Star","A strange star",830);
//                break;
//            case 275:
//                createStar(itemDef,"Glorious Attack Star","A strange star",850);
//                break;
//            case 276:
//                createStar(itemDef,"Dull Defence Star","A strange star",-30260);
//                break;
//            case 277:
//                createStar(itemDef,"Defence Star","A strange star",-30250);
//                break;
//            case 280:
//                createStar(itemDef,"Shining Defence Star","A strange star",-30240);
//                break;
//            case 281:
//                createStar(itemDef,"Glorious Defence Star","A strange star",-30220);
//                break;
//            case 282:
//                createStar(itemDef,"Dull Strength Star","A strange star",23340);
//                break;
//            case 283:
//                createStar(itemDef,"Strength Star","A strange star",23350);
//                break;
//            case 286:
//                createStar(itemDef,"Shining Strength Star","A strange star",23360);
//                break;
//            case 287:
//                createStar(itemDef,"Glorious Strength Star","A strange star",23380);
//                break;
//            case 6822:
//                copyDef(itemDef, ItemDefinition.forID(6822));
//                itemDef.name = "Dull Prismatic Star";
//                itemDef.description = "A dull looking star";
//                itemDef.inventoryOptions = new String[]{"Select Skill", null, null, null, null};
//                break;
//            case 6823:
//                copyDef(itemDef, ItemDefinition.forID(6823));
//                itemDef.name = "Prismatic Star";
//                itemDef.description = "A strange star";
//                itemDef.inventoryOptions = new String[]{"Select Skill", null, null, null, null};
//                break;
//            case 6824:
//                copyDef(itemDef, ItemDefinition.forID(6824));
//                itemDef.name = "Shining Prismatic Star";
//                itemDef.description = "A shining star";
//                itemDef.inventoryOptions = new String[]{"Select Skill", null, null, null, null};
//                break;
//            case 6825:
//                copyDef(itemDef, ItemDefinition.forID(6825));
//                itemDef.name = "Glorious Prismatic Star";
//                itemDef.description = "A glorious looking star";
//                itemDef.inventoryOptions = new String[]{"Select Skill", null, null, null, null};
//                break;
            case 4425:
                copyDef(itemDef, ItemDefinition.forID(732));
                itemDef.name = "Lumberjack's Brew";
                itemDef.originalModelColors = new int[]{24222};
                itemDef.modifiedModelColors = new int[]{61};
                itemDef.description = "A sip of this doubles your Woodcutting XP for 30 minutes.";
                itemDef.inventoryOptions[0] = "Drink";
                break;
            case 2396:
                copyDef(itemDef, ItemDefinition.forID(2396));
                itemDef.name = "Teleport Charge Scroll";
                itemDef.description = "Read this to gain a home teleport charge.";
                break;
            case 3103:
                copyDef(itemDef, ItemDefinition.forID(3103));
                itemDef.originalModelColors = new int[]{6598};
                itemDef.modifiedModelColors = new int[]{-31105};
                itemDef.name = "Magic Notepaper";
                itemDef.description = "Use on items to note them.";
                itemDef.inventoryOptions[0] = null;
                break;
            case 12419:
                copyDef(itemDef, ItemDefinition.forID(12419));
                itemDef.originalModelColors = new int[]{-27493, 0, 947, -27493, 947};
                itemDef.modifiedModelColors = new int[]{695, 55977, 9152, 41920, 17979};
                itemDef.name = "Dimensional Witch Hat";
                itemDef.description = "This seems to be woven from the fabric of time and space.";
                break;
            case 12420:
                copyDef(itemDef, ItemDefinition.forID(12420));
                itemDef.originalModelColors = new int[]{-27493, 0, 947, -27493, 947, 947};
                itemDef.modifiedModelColors = new int[]{695, 55977, 24512, 46016, 9152, 58316};
                itemDef.name = "Dimensional Witch Robe Top";
                itemDef.description = "This seems to be woven from the fabric of time and space.";
                break;
            case 12421:
                copyDef(itemDef, ItemDefinition.forID(12421));
                itemDef.originalModelColors = new int[]{-27493, 0, 947, -27493, 947};
                itemDef.modifiedModelColors = new int[]{695, 55977, 24512, 46016, 58316};
                itemDef.name = "Dimensional Witch Robe Top";
                itemDef.description = "This seems to be woven from the fabric of time and space.";
                break;
            case 15000:
                copyDef(itemDef, ItemDefinition.forID(219));
                itemDef.originalModelColors = new int[]{947, 822};
                itemDef.modifiedModelColors = new int[]{22418, 22428};
                itemDef.name = "Grimy Bloodweed";
                itemDef.description = "A rare herb containing a thick blood like substance.";
                break;
            case 6198:
                copyDef(itemDef, ItemDefinition.forID(6199));
                itemDef.originalModelColors = new int[]{822};
                itemDef.modifiedModelColors = new int[]{22410};
                itemDef.name = "Starter Kit";
                itemDef.description = "A kit of essentials.";
                break;
            case 15001:
                copyDef(itemDef, ItemDefinition.forID(269));
                itemDef.originalModelColors = new int[]{947, 822};
                itemDef.modifiedModelColors = new int[]{22418, 22428};
                itemDef.name = "Bloodweed";
                itemDef.description = "A rare herb containing a thick blood like substance.";
                break;
            case 7404:
                itemDef.name = "Cinnamon Logs";
                itemDef.description = "These can be refined into cinnamon with a knife.";
                itemDef.originalModelColors = new int[]{2487, 1078, 3994};
                itemDef.modifiedModelColors = new int[]{5559, 5665, 5784};
                break;
            case 7411:
                copyDef(itemDef, ItemDefinition.forID(7409));
                itemDef.name = "Jewel Secateurs";
                itemDef.originalModelColors = new int[]{-32011, -32041, -32011};
                itemDef.modifiedModelColors = new int[]{61, 41, 5772};
                break;
            case 1653:
                itemDef.name = "Void";
                itemDef.modelId = 15282;
                itemDef.description = ":";
                itemDef.originalModelColors = new int[]{947};
                itemDef.modifiedModelColors = new int[]{43072};
                itemDef.modelZoom = 919;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset2 = 3;
                itemDef.modelRotation1 = 459;
                itemDef.modelRotation2 = 0;
                itemDef.maleModel = 14567;
                itemDef.femaleModel = 15290;
                break;
            case 1459:
                createCoinBasedCurrency(
                        new int[]{2, 3, 4, 5, 25, 100, 250, 1000, 10000, 0},
                        new int[]{1460, 1461, 1589, 1647, 1648, 1649, 1650, 1651, 1652, 0},
                        "Jewels",
                        "A Valuable Currency used by Commodity Traders!",
                        itemDef,
                        -32011
                );
                break;
            case 1460:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 2);
                break;
            case 1461:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 3);
                break;
            case 1589:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 4);
                break;
            case 1647:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 5);
                break;
            case 1648:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 25);
                break;
            case 1649:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 100);
                break;
            case 1650:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 250);
                break;
            case 1651:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 1000);
                break;
            case 1652:
                createCoinBasedCurrencyStack(itemDef, "Jewels", "More Precious than Gold!", -32011, 10000);
                break;
            case 7774:
                itemDef.name = "Reward Token (Common)";
                itemDef.description = "Obtained From A Fairy Trader.";
                itemDef.stackable = true;
                break;

            case 7775:
                itemDef.name = "Reward Token (Uncommon)";
                itemDef.description = "Obtained From A Fairy Trader.";
                itemDef.stackable = true;
                break;

            case 7776:
                itemDef.name = "Reward Token (Rare)";
                itemDef.description = "Obtained From A Fairy Trader.";
                itemDef.stackable = true;
                break;

            case 5080:
                itemDef.name = "Cash Bag (Tier I)";
                itemDef.description = "Obtained From A Fairy Trader.";
                itemDef.stackable = true;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Open";
                itemDef.inventoryOptions[4] = "Drop";
                break;

            case 7791:
            case 7792:
            case 7793:
                itemDef.name = "Tome Of Experience (Mining)";
                itemDef.description = "Obtained From A Fairy Trader.";
                itemDef.stackable = true;
                break;

            case 13571:
                itemDef.name = "@yel@Fairy Dust";
                itemDef.description = "Mined from a fairy rock.";
                itemDef.stackable = true;
                break;

            case 11179:
                itemDef.name = "Raid Points";
                itemDef.description = "Gather these from doing raids.";
                break;

            case 22550:
                itemDef.modelId = 8275;
                itemDef.name = "Divine Spirit Shield";
                itemDef.description = "It's a Divine Spirit Shield.";
                itemDef.maleModel = 8275;
                itemDef.femaleModel = 8275;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Divine_spirit_shield.png");
                itemDef.customSpriteLocation = ph;
                break;

            case 22501:
                itemDef.modelId = 8264;
                itemDef.name = "Statius's full helm";
                itemDef.description = "It's a Statius full helm.";
                itemDef.maleModel = 8264;
                itemDef.femaleModel = 8261;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph1 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Statius's_full_helm.png");
                itemDef.customSpriteLocation = ph1;
                break;

            case 22502:
                itemDef.modelId = 8265;
                itemDef.name = "Statius's platebody";
                itemDef.description = "It's a Statius platebody.";
                itemDef.maleModel = 8265;
                itemDef.femaleModel = 8262;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph2 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Statius's_platebody.png");
                itemDef.customSpriteLocation = ph2;
                break;


            case 22503:
                itemDef.modelId = 8266;
                itemDef.name = "Statius's platelegs";
                itemDef.description = "It's a Statius platelegs.";
                itemDef.maleModel = 8266;
                itemDef.femaleModel = 8263;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph3 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Statius's_platelegs.png");
                itemDef.customSpriteLocation = ph3;
                break;

            case 22504:
                itemDef.modelId = 8267;
                itemDef.name = "Statius's warhammer";
                itemDef.description = "It's a Statius platelegs.";
                itemDef.maleModel = 8267;
                itemDef.femaleModel = 8267;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph4 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Statius's_warhammer.png");
                itemDef.customSpriteLocation = ph4;
                break;

            case 22505:
                itemDef.modelId = 8268;
                itemDef.name = "Vesta's Longsword";
                itemDef.description = "It's a Vesta longsword.";
                itemDef.maleModel = 8268;
                itemDef.femaleModel = 8268;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph5 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Vesta's_longsword.png");
                itemDef.customSpriteLocation = ph5;
                break;
            case 7478:
                itemDef.name = "Donator Token";
                itemDef.description = "The form of currency for Os-Revolution Donators.";
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[4] = "Drop";
                break;
            case 22506:
                itemDef.modelId = 8269;
                itemDef.name = "Vesta's Spear";
                itemDef.description = "It's a Vesta spear.";
                itemDef.maleModel = 8269;
                itemDef.femaleModel = 8269;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph6 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Vesta's_spear.png");
                itemDef.customSpriteLocation = ph6;
                break;

            case 22507:
                itemDef.modelId = 8270;
                itemDef.name = "Vesta's Chainbody";
                itemDef.description = "It's a Vesta chainbody.";
                itemDef.maleModel = 8270;
                itemDef.femaleModel = 8271;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph7 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Vesta's_chainbody.png");
                itemDef.customSpriteLocation = ph7;
                break;

            case 22508:
                itemDef.modelId = 8272;
                itemDef.name = "Vesta's Plateskirt";
                itemDef.description = "It's a Vesta plateskirt.";
                itemDef.maleModel = 8272;
                itemDef.femaleModel = 8272;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wield";
                itemDef.inventoryOptions[4] = "Drop";
                byte[] ph8 = FileOperations.readFile(Signlink.getCacheDirectory() + "/Customs/Vesta's_plateskirt.png");
                itemDef.customSpriteLocation = ph8;
                break;

            case 22907:
                itemDef.modelId = 35757;//35755;
                itemDef.name = "Beta Cape";
                itemDef.description = "It's a Vesta plateskirt.";
                itemDef.maleModel = 35757;
                itemDef.femaleModel = 35757;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Wear";
                itemDef.inventoryOptions[4] = "Drop";
                break;

            case 11864:
            case 11865:
            case 19639:
            case 19641:
            case 19643:
            case 19645:
            case 19647:
            case 19649:
                itemDef.equipActions[2] = "Log";
                itemDef.equipActions[1] = "Check";
                break;

            case 8152:
                itemDef.name = "@mag@Bank Chest";
                itemDef.inventoryOptions = new String[]{"Open", null, "Check Charges", null, "Drop"};
                itemDef.description = "Bank your items here, for a price!";
                break;

            case 3495:
                itemDef.name = "Bank Charge";
                itemDef.inventoryOptions = new String[]{"Use", null, null, null, "Drop"};
                itemDef.description = "Gives you one charge for your bank.";
                itemDef.stackable = true;
                break;
            case 13136:
                itemDef.equipActions[2] = "Elidinis";
                itemDef.equipActions[1] = "Kalphite Hive";
                break;
            case 2550:
                itemDef.equipActions[2] = "Check";
                break;
            case 1712:
            case 1710:
            case 1708:
            case 1706:
                itemDef.equipActions[1] = "Edgeville";
                itemDef.equipActions[2] = "Karamja";
                itemDef.equipActions[3] = "Draynor";
                itemDef.equipActions[4] = "Al-Kharid";
                break;

            case 2552:
            case 2554:
            case 2556:
            case 2558:
            case 2560:
            case 2562:
            case 2564:
            case 2566: // Ring of duelling
                itemDef.equipActions[2] = "Shantay Pass";
                itemDef.equipActions[1] = "Clan wars";
                break;

            case 19668:
                itemDef.name = "Portable Bank"; // Creates a trading post for players // check michael
                break;

            case 11739:
                itemDef.name = "Hourly Box";
                break;
            //DeadlyStuff
            case 13302:
                itemDef.name = "@red@Deadly Key";
                break;

            case 964:
                itemDef.name = "@red@Deadly Key Piece";
                break;

            case 6104:
                itemDef.name = "@red@Deadly Key Piece";
                break;
            //EndDeadlyStuff
            case 6949:
                itemDef.name = "Imbued scroll";
                break;

            case 20238:
                itemDef.name = "Imbue Scroll";
                itemDef.inventoryOptions = new String[]{null, null, null, "Drop"};
                break;
            case 21307:
                itemDef.name = "Pursuit crate";
                break;
            case 2996:
                itemDef.name = "PKP Ticket";
                itemDef.inventoryOptions = new String[5];
                itemDef.description = "Exchange this for a PK Point.";
                break;
            case 13346:
                itemDef.name = "Ultra Mystery Box";
                itemDef.inventoryOptions = new String[]{"Open", null, null, null, "Drop"};
                itemDef.description = "Mystery box that contains goodies.";
                break;
            case 15098:
                itemDef.name = "Dice (up to 100)";
                itemDef.description = "A 100-sided dice.";
                itemDef.modelId = 31223;
                itemDef.modelZoom = 1104;
                itemDef.modelRotation2 = 215;
                itemDef.modelRotation1 = 94;
                itemDef.modelOffset2 = -5;
                itemDef.modelOffset1 = -18;
                itemDef.inventoryOptions = new String[5];
                itemDef.inventoryOptions[1] = "Public-roll";
                itemDef.inventoryOptions[2] = null;
                itemDef.name = "Dice (up to 100)";
                itemDef.anInt196 = 15;
                itemDef.anInt184 = 25;
                break;
            case 11773:
            case 11771:
            case 11770:
            case 11772:
                itemDef.anInt196 += 45;
                break;
            case 2697:
                itemDef.name = "$5 Scroll";
                itemDef.description = "Read this scroll to be rewarded with $5 credit.";
                break;
            case 2698:
                itemDef.name = "$15 Scroll";
                itemDef.description = "Read this scroll to be rewarded with $15 credit.";
                break;
            case 2699:
                itemDef.name = "$35 Scroll";
                itemDef.description = "Read this scroll to be rewarded with $35 credit.";
                break;
            case 2700:
                itemDef.name = "$50 Scroll";
                itemDef.description = "Read this scroll to be rewarded with $50 credit.";
                break;
            case 1464:
                itemDef.name = "Vote ticket";
                itemDef.description = "This ticket can be exchanged for a voting point.";
                break;
        }
    }

    void method2789(ItemDefinition var1, ItemDefinition var2) {
        modelId = var1.modelId * 1;
        modelZoom = var1.modelZoom * 1;
        modelRotation1 = 1 * var1.modelRotation1;
        modelRotation2 = 1 * var1.modelRotation2;
        anInt204 = 1 * var1.anInt204;
        modelOffset1 = 1 * var1.modelOffset1;
        modelOffset2 = var1.modelOffset2 * 1;
        originalModelColors = var2.originalModelColors;
        modifiedModelColors = var2.modifiedModelColors;
        // originalTextureColors = var2.originalTextureColors;
        // modifiedTextureColors = var2.modifiedTextureColors;
        name = var2.name;
        membersObject = var2.membersObject;
        stackable = var2.stackable;
        maleModel = 1 * var2.maleModel;
        anInt188 = 1 * var2.anInt188;
        anInt185 = 1 * var2.anInt185;
        femaleModel = var2.femaleModel * 1;
        anInt164 = var2.anInt164 * 1;
        anInt162 = 1 * var2.anInt162;
        anInt175 = 1 * var2.anInt175;
        anInt166 = var2.anInt166 * 1;
        anInt197 = var2.anInt197 * 1;
        anInt173 = var2.anInt173 * 1;
        team = var2.team * 1;
        groundOptions = var2.groundOptions;
        inventoryOptions = new String[5];
        equipActions = new String[5];
        if (null != var2.inventoryOptions) {
            for (int var4 = 0; var4 < 4; ++var4) {
                inventoryOptions[var4] = var2.inventoryOptions[var4];
            }
        }

        inventoryOptions[4] = "Discard";
        value = 0;
    }

    void method2790(ItemDefinition var1, ItemDefinition var2) {
        modelId = var1.modelId * 1;
        modelZoom = 1 * var1.modelZoom;
        modelRotation1 = var1.modelRotation1 * 1;
        modelRotation2 = var1.modelRotation2 * 1;
        anInt204 = var1.anInt204 * 1;
        modelOffset1 = 1 * var1.modelOffset1;
        modelOffset2 = var1.modelOffset2 * 1;
        originalModelColors = var1.originalModelColors;
        modifiedModelColors = var1.modifiedModelColors;
        originalTextureColors = var1.originalTextureColors;
        modifiedTextureColors = var1.modifiedTextureColors;
        stackable = var1.stackable;
        name = var2.name;
        value = 0;
    }

    /*
     * private void readValues(Stream stream) { while(true) { int opcode =
     * stream.readUnsignedByte(); if(opcode == 0) return; if(opcode == 1) modelId =
     * stream.readUnsignedWord(); else if(opcode == 2) name = stream.readString();
     * else if(opcode == 3) description = stream.readString(); else if(opcode == 4)
     * modelZoom = stream.readUnsignedWord(); else if(opcode == 5) modelRotation1 =
     * stream.readUnsignedWord(); else if(opcode == 6) modelRotation2 =
     * stream.readUnsignedWord(); else if(opcode == 7) { modelOffset1 =
     * stream.readUnsignedWord(); if(modelOffset1 > 32767) modelOffset1 -= 0x10000;
     * } else if(opcode == 8) { modelOffset2 = stream.readUnsignedWord();
     * if(modelOffset2 > 32767) modelOffset2 -= 0x10000; } else if(opcode == 11)
     * stackable = true; else if(opcode == 12) value = stream.readDWord(); else
     * if(opcode == 16) membersObject = true; else if(opcode == 23) { maleModel =
     * stream.readUnsignedWord(); aByte205 = stream.readSignedByte(); } else if
     * (opcode == 24) anInt188 = stream.readUnsignedWord(); else if (opcode == 25) {
     * femaleModel = stream.readUnsignedWord(); aByte154 = stream.readSignedByte();
     * } else if (opcode == 26) anInt164 = stream.readUnsignedWord(); else if(opcode
     * >= 30 && opcode < 35) { if(groundOptions == null) groundOptions = new
     * String[5]; groundOptions[opcode - 30] = stream.readString();
     * if(groundOptions[opcode - 30].equalsIgnoreCase("hidden"))
     * groundOptions[opcode - 30] = null; } else if(opcode >= 35 && opcode < 40) {
     * if(inventoryOptions == null) inventoryOptions = new String[5];
     * inventoryOptions[opcode - 35] = stream.readString(); } else if(opcode == 40)
     * { int size = stream.readUnsignedByte(); originalModelColors = new int[size];
     * modifiedModelColors = new int[size]; for(int index = 0; index < size;
     * index++) { originalModelColors[index] = stream.readUnsignedWord();
     * modifiedModelColors[index] = stream.readUnsignedWord(); } } else if(opcode ==
     * 41) { int size = stream.readUnsignedByte(); originalTextureColors = new
     * short[size]; modifiedTextureColors = new short[size]; for(int index = 0;
     * index < size; index++) { originalTextureColors[index] = (short)
     * stream.readUnsignedWord(); modifiedTextureColors[index] = (short)
     * stream.readUnsignedWord(); } } else if(opcode == 65) { searchableItem = true;
     * } else if(opcode == 78) anInt185 = stream.readUnsignedWord(); else if(opcode
     * == 79) anInt162 = stream.readUnsignedWord(); else if(opcode == 90) anInt175 =
     * stream.readUnsignedWord(); else if(opcode == 91) anInt197 =
     * stream.readUnsignedWord(); else if(opcode == 92) anInt166 =
     * stream.readUnsignedWord(); else if(opcode == 93) anInt173 =
     * stream.readUnsignedWord(); else if(opcode == 95) anInt204 =
     * stream.readUnsignedWord(); else if(opcode == 97) certID =
     * stream.readUnsignedWord(); else if(opcode == 98) certTemplateID =
     * stream.readUnsignedWord(); else if (opcode >= 100 && opcode < 110) { if
     * (stackIDs == null) { stackIDs = new int[10]; stackAmounts = new int[10]; }
     * stackIDs[opcode - 100] = stream.readUnsignedWord(); stackAmounts[opcode -
     * 100] = stream.readUnsignedWord(); } else if(opcode == 110) anInt167 =
     * stream.readUnsignedWord(); else if(opcode == 111) anInt192 =
     * stream.readUnsignedWord(); else if(opcode == 112) anInt191 =
     * stream.readUnsignedWord(); else if(opcode == 113) anInt196 =
     * stream.readSignedByte(); else if(opcode == 114) anInt184 =
     * stream.readSignedByte() * 5; else if(opcode == 115) team =
     * stream.readUnsignedByte(); else if (opcode == 139) opcode139 =
     * stream.readUnsignedWord(); else if (opcode == 140) opcode140 =
     * stream.readUnsignedWord(); else if (opcode == 148) opcode148 =
     * stream.readUnsignedWord(); else if (opcode == 149) opcode149 =
     * stream.readUnsignedWord(); else { System.out.println("Error loading item " +
     * id + ", opcode " + opcode); } } }
     */

    private void readValues(Stream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0)
                return;
            if (opcode == 1)
                modelId = stream.readUnsignedWord();
            else if (opcode == 2)
                name = stream.readString();
            else if (opcode == 3)
                description = stream.readString();
            else if (opcode == 4)
                modelZoom = stream.readUnsignedWord();
            else if (opcode == 5)
                modelRotation1 = stream.readUnsignedWord();
            else if (opcode == 6)
                modelRotation2 = stream.readUnsignedWord();
            else if (opcode == 7) {
                modelOffset1 = stream.readUnsignedWord();
                if (modelOffset1 > 32767)
                    modelOffset1 -= 0x10000;
            } else if (opcode == 8) {
                modelOffset2 = stream.readUnsignedWord();
                if (modelOffset2 > 32767)
                    modelOffset2 -= 0x10000;
            } else if (opcode == 11)
                stackable = true;
            else if (opcode == 12)
                value = stream.readDWord();
            else if (opcode == 16)
                membersObject = true;
            else if (opcode == 23) {
                maleModel = stream.readUnsignedWord();
                aByte205 = stream.readSignedByte();
            } else if (opcode == 24)
                anInt188 = stream.readUnsignedWord();
            else if (opcode == 25) {
                femaleModel = stream.readUnsignedWord();
                aByte154 = stream.readSignedByte();
            } else if (opcode == 26)
                anInt164 = stream.readUnsignedWord();
            else if (opcode >= 30 && opcode < 35) {
                if (groundOptions == null)
                    groundOptions = new String[5];
                groundOptions[opcode - 30] = stream.readString();
                if (groundOptions[opcode - 30].equalsIgnoreCase("hidden"))
                    groundOptions[opcode - 30] = null;
            } else if (opcode >= 35 && opcode < 40) {
                if (inventoryOptions == null)
                    inventoryOptions = new String[5];
                inventoryOptions[opcode - 35] = stream.readString();
            } else if (opcode == 40) {
                int size = stream.readUnsignedByte();
                originalModelColors = new int[size];
                modifiedModelColors = new int[size];
                for (int index = 0; index < size; index++) {
                    originalModelColors[index] = stream.readUnsignedWord();
                    modifiedModelColors[index] = stream.readUnsignedWord();
                }
            } else if (opcode == 41) {
                int size = stream.readUnsignedByte();
                originalTextureColors = new short[size];
                modifiedTextureColors = new short[size];
                for (int index = 0; index < size; index++) {
                    originalTextureColors[index] = (short) stream.readUnsignedWord();
                    modifiedTextureColors[index] = (short) stream.readUnsignedWord();
                }
            } else if (opcode == 65) {
                searchableItem = true;
            } else if (opcode == 78)
                anInt185 = stream.readUnsignedWord();
            else if (opcode == 79)
                anInt162 = stream.readUnsignedWord();
            else if (opcode == 90)
                anInt175 = stream.readUnsignedWord();
            else if (opcode == 91)
                anInt197 = stream.readUnsignedWord();
            else if (opcode == 92)
                anInt166 = stream.readUnsignedWord();
            else if (opcode == 93)
                anInt173 = stream.readUnsignedWord();
            else if (opcode == 95)
                anInt204 = stream.readUnsignedWord();
            else if (opcode == 97)
                certID = stream.readUnsignedWord();
            else if (opcode == 98)
                certTemplateID = stream.readUnsignedWord();
            else if (opcode >= 100 && opcode < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[opcode - 100] = stream.readUnsignedWord();
                stackAmounts[opcode - 100] = stream.readUnsignedWord();
            } else if (opcode == 110)
                anInt167 = stream.readUnsignedWord();
            else if (opcode == 111)
                anInt192 = stream.readUnsignedWord();
            else if (opcode == 112)
                anInt191 = stream.readUnsignedWord();
            else if (opcode == 113)
                anInt196 = stream.readSignedByte();
            else if (opcode == 114)
                anInt184 = stream.readSignedByte() * 5;
            else if (opcode == 115)
                team = stream.readUnsignedByte();
            else if (opcode == 139)
                opcode139 = stream.readUnsignedWord();
            else if (opcode == 140)
                opcode140 = stream.readUnsignedWord();
            else if (opcode == 148)
                opcode148 = stream.readUnsignedWord();
            else if (opcode == 149)
                opcode149 = stream.readUnsignedWord();
            else {
//                 System.out.println("Error loading item " + id + ", opcode " + opcode);
            }
        }
    }

    public int opcode139, opcode140, opcode148, opcode149;

    public static void nullLoader() {
        mruNodes2 = null;
        mruNodes1 = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public boolean method192(int j) {
        int k = anInt175;
        int l = anInt166;
        if (j == 1) {
            k = anInt197;
            l = anInt173;
        }
        if (k == -1)
            return true;
        boolean flag = true;
        if (!Model.method463(k))
            flag = false;
        if (l != -1 && !Model.method463(l))
            flag = false;
        return flag;
    }

    public Model method194(int j) {
        int k = anInt175;
        int l = anInt166;
        if (j == 1) {
            k = anInt197;
            l = anInt173;
        }
        if (k == -1)
            return null;
        Model model = Model.method462(k);
        if (l != -1) {
            Model model_1 = Model.method462(l);
            Model aclass30_sub2_sub4_sub6s[] = {model, model_1};
            model = new Model(2, aclass30_sub2_sub4_sub6s);
        }
        if (modifiedModelColors != null) {
            for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
                model.method476(modifiedModelColors[i1], originalModelColors[i1]);

        }
        return model;
    }

    public boolean method195(int j) {
        int k = maleModel;
        int l = anInt188;
        int i1 = anInt185;
        if (j == 1) {
            k = femaleModel;
            l = anInt164;
            i1 = anInt162;
        }
        if (k == -1)
            return true;
        boolean flag = true;
        if (!Model.method463(k))
            flag = false;
        if (l != -1 && !Model.method463(l))
            flag = false;
        if (i1 != -1 && !Model.method463(i1))
            flag = false;
        return flag;
    }

    public Model method196(int i) {
        int j = maleModel;
        int k = anInt188;
        int l = anInt185;
        if (i == 1) {
            j = femaleModel;
            k = anInt164;
            l = anInt162;
        }
        if (j == -1)
            return null;
        Model model = Model.method462(j);
        if (k != -1)
            if (l != -1) {
                Model model_1 = Model.method462(k);
                Model model_3 = Model.method462(l);
                Model aclass30_sub2_sub4_sub6_1s[] = {model, model_1, model_3};
                model = new Model(3, aclass30_sub2_sub4_sub6_1s);
            } else {
                Model model_2 = Model.method462(k);
                Model aclass30_sub2_sub4_sub6s[] = {model, model_2};
                model = new Model(2, aclass30_sub2_sub4_sub6s);
            }
        if (i == 0 && aByte205 != 0)
            model.method475(0, aByte205, 0);
        if (i == 1 && aByte154 != 0)
            model.method475(0, aByte154, 0);
        if (modifiedModelColors != null) {
            for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
                model.method476(modifiedModelColors[i1], originalModelColors[i1]);

        }
        return model;
    }

    private void setDefaults() {
        // equipActions = new String[6];
        customSpriteLocation = null;
        equipActions = new String[]{"Remove", null, "Operate", null, null};
        modelId = 0;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        modifiedTextureColors = null;
        originalTextureColors = null;
        modelZoom = 2000;
        modelRotation1 = 0;
        modelRotation2 = 0;
        anInt204 = 0;
        modelOffset1 = 0;
        modelOffset2 = 0;
        stackable = false;
        value = 1;
        membersObject = false;
        groundOptions = null;
        inventoryOptions = null;
        maleModel = -1;
        anInt188 = -1;
        aByte205 = 0;
        femaleModel = -1;
        anInt164 = -1;
        aByte154 = 0;
        anInt185 = -1;
        anInt162 = -1;
        anInt175 = -1;
        anInt166 = -1;
        anInt197 = -1;
        anInt173 = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        anInt167 = 128;
        anInt192 = 128;
        anInt191 = 128;
        anInt196 = 0;
        anInt184 = 0;
        team = 0;

        opcode140 = -1;
        opcode139 = -1;
        opcode148 = -1;
        opcode149 = -1;

        searchableItem = false;
    }

    public static void dumpBonuses() {
        int[] bonuses = new int[14];
        int bonus = 0;
        int amount = 0;
        for (int i = 21304; i < totalItems; i++) {
            ItemDefinition item = ItemDefinition.forID(i);
            URL url;
            try {
                try {
                    try {
                        url = new URL("http://2007.runescape.wikia.com/wiki/" + item.name.replaceAll(" ", "_"));
                        URLConnection con = url.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String line;
                        BufferedWriter writer = new BufferedWriter(new FileWriter("item.cfg", true));
                        while ((line = in.readLine()) != null) {
                            try {
                                if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "")
                                            .replace("\"\"", "")
                                            .replace("<td style=\"text-align: center; width: 35px;\">", "");
                                    bonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                } else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "")
                                            .replace("<td style=\"text-align: center; width: 30px;\">", "");
                                    bonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                }
                            } catch (NumberFormatException e) {

                            }
                            if (bonus >= 13)
                                bonus = 0;
                            // in.close();
                        }
                        in.close();
                        writer.write("item	=	" + i + "	" + item.name.replace(" ", "_") + "	"
                                + item.description.replace(" ", "_") + "	" + item.value + "	" + item.value + "	"
                                + item.value + "	" + bonuses[0] + "	" + bonuses[1] + "	" + bonuses[2] + "	"
                                + bonuses[3] + "	" + bonuses[4] + "	" + bonuses[5] + "	" + bonuses[6] + "	"
                                + bonuses[7] + "	" + bonuses[8] + "	" + bonuses[9] + "	" + bonuses[10] + "	"
                                + bonuses[13]);
                        bonuses[0] = bonuses[1] = bonuses[2] = bonuses[3] = bonuses[4] = bonuses[5] = bonuses[6] = bonuses[7] = bonuses[8] = bonuses[9] = bonuses[10] = bonuses[13] = 0;
                        writer.newLine();
                        amount++;
                        writer.close();
                    } catch (NullPointerException e) {

                    }
                } catch (FileNotFoundException e) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done dumping " + amount + " item bonuses!");
    }

    public static void dumpBonus() {
        final int[] wikiBonuses = new int[18];
        int bonus = 0;
        int amount = 0;
        System.out.println("Starting to dump item bonuses...");
        for (int i = 20000; i < totalItems; i++) {
            ItemDefinition item = ItemDefinition.forID(i);
            try {
                try {
                    try {
                        final URL url = new URL(
                                "http://2007.runescape.wikia.com/wiki/" + item.name.replaceAll(" ", "_"));
                        URLConnection con = url.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String line;
                        writer = new BufferedWriter(new FileWriter("item.cfg", true));
                        while ((line = in.readLine()) != null) {
                            try {
                                if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "")
                                            .replace("\"\"", "")
                                            .replace("<td style=\"text-align: center; width: 35px;\">", "");
                                    wikiBonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                } else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "")
                                            .replace("<td style=\"text-align: center; width: 30px;\">", "");
                                    wikiBonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            in.close();
                            writer.write("item = " + i + "	" + item.name.replace(" ", "_") + "	"
                                    + item.description.replace(" ", "_") + "	" + item.value + "	" + item.value
                                    + "	" + item.value + "	" + wikiBonuses[0] + "	" + wikiBonuses[1] + "	"
                                    + wikiBonuses[2] + "	" + wikiBonuses[3] + "	" + wikiBonuses[4] + "	"
                                    + wikiBonuses[5] + "	" + wikiBonuses[6] + "	" + wikiBonuses[7] + "	"
                                    + wikiBonuses[8] + "	" + wikiBonuses[9] + "	" + wikiBonuses[10] + "	"
                                    + wikiBonuses[13]);
                            amount++;
                            wikiBonuses[0] = wikiBonuses[1] = wikiBonuses[2] = wikiBonuses[3] = wikiBonuses[4] = wikiBonuses[5] = wikiBonuses[6] = wikiBonuses[7] = wikiBonuses[8] = wikiBonuses[9] = wikiBonuses[10] = wikiBonuses[11] = wikiBonuses[13] = 0;
                            writer.newLine();
                            writer.close();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done dumping " + amount + " item bonuses!");
        }
    }

    public static void dumpItemDefs() {
        final int[] wikiBonuses = new int[18];
        int bonus = 0;
        int amount = 0;
        int value = 0;
        int slot = -1;
        // Testing Variables just so i know format is correct
        String fullmask = "false";
        // boolean stackable1 = false;
        String stackable = "false";
        // boolean noteable1 = false;
        String noteable = "true";
        // boolean tradeable1 = false;
        String tradeable = "true";
        // boolean wearable1 = false;
        String wearable = "true";
        String showBeard = "true";
        String members = "true";
        boolean twoHanded = false;
        System.out.println("Starting to dump item definitions...");
        for (int i = 21298; i < totalItems; i++) {
            ItemDefinition item = ItemDefinition.forID(i);
            try {
                try {
                    try {
                        final URL url = new URL(
                                "http://2007.runescape.wikia.com/wiki/" + item.name.replaceAll(" ", "_"));
                        URLConnection con = url.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String line;
                        writer = new BufferedWriter(new FileWriter("itemDefs.json", true));
                        while ((line = in.readLine()) != null) {
                            try {
                                if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "")
                                            .replace("\"\"", "")
                                            .replace("<td style=\"text-align: center; width: 35px;\">", "");
                                    wikiBonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                } else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
                                    line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "")
                                            .replace("<td style=\"text-align: center; width: 30px;\">", "");
                                    wikiBonuses[bonus] = Integer.parseInt(line);
                                    bonus++;
                                }
                                if (line.contains("<div id=\"GEPCalcResult\" style=\"display:inline;\">")) {
                                    line = line.replace("</div>", "").replace("%", "").replace("?", "").replace("%", "")
                                            .replace("<div id=\"GEPCalcResult\" style=\"display:inline;\">", "");
                                    value = Integer.parseInt(line);
                                }

                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            in.close();
                            // fw.write("ItemID: "+itemDefinition.id+" - "+itemDefinition.name);
                            // fw.write(System.getProperty("line.separator"));
                            // writer.write("[\n");
                            writer.write("  {\n\t\"id\": " + item.id + ",\n\t\"name\": \"" + item.name
                                    + "\",\n\t\"desc\": \"" + item.description.replace("_", " ") + "\",\n\t\"value\": "
                                    + value + ",\n\t\"dropValue\": " + value + ",\n\t\"bonus\": [\n\t  "
                                    + wikiBonuses[0] + ",\n\t  " + wikiBonuses[1] + ",\n\t  " + wikiBonuses[2]
                                    + ",\n\t  " + wikiBonuses[3] + ",\n\t  " + wikiBonuses[4] + ",\n\t  "
                                    + wikiBonuses[5] + ",\n\t  " + wikiBonuses[6] + ",\n\t  " + wikiBonuses[7]
                                    + ",\n\t  " + wikiBonuses[8] + ",\n\t  " + wikiBonuses[9] + ",\n\t  "
                                    + wikiBonuses[10] + ",\n\t  " + wikiBonuses[13] + ",\n\t],\n\t\"slot\": " + slot
                                    + ",\n\t\"fullmask\": " + fullmask + ",\n\t\"stackable\": " + stackable
                                    + ",\n\t\"noteable\": " + noteable + ",\n\t\"tradeable\": " + tradeable
                                    + ",\n\t\"wearable\": " + wearable + ",\n\t\"showBeard\": " + showBeard
                                    + ",\n\t\"members\": " + members + ",\n\t\"slot\": " + twoHanded
                                    + ",\n\t\"requirements\": [\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t  0,\n\t]\n  },\n");
                            /*
                             * writer.write("item = " + i + "	" + item.name.replace(" ", "_") + "	" +
                             * item.description.replace(" ", "_") + "	" + item.value + "	" + item.value +
                             * "	" + item.value + "	" + wikiBonuses[0] + "	" + wikiBonuses[1] + "	" +
                             * wikiBonuses[2] + "	" + wikiBonuses[3] + "	" + wikiBonuses[4] + "	" +
                             * wikiBonuses[5] + "	" + wikiBonuses[6] + "	" + wikiBonuses[7] + "	" +
                             * wikiBonuses[8] + "	" + wikiBonuses[9] + "	" + wikiBonuses[10] + "	" +
                             * wikiBonuses[13]);
                             */
                            amount++;
                            wikiBonuses[0] = wikiBonuses[1] = wikiBonuses[2] = wikiBonuses[3] = wikiBonuses[4] = wikiBonuses[5] = wikiBonuses[6] = wikiBonuses[7] = wikiBonuses[8] = wikiBonuses[9] = wikiBonuses[10] = wikiBonuses[11] = wikiBonuses[13] = 0;
                            writer.newLine();
                            writer.close();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done dumping " + amount + " item definitions!");
        }
    }

    public static void itemDump() {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.home") + "/Desktop/Item Dump.txt");
            for (int i = 0; i < totalItems; i++) {
                ItemDefinition obj = ItemDefinition.forID(i);

                fw.write("case " + i + ":");
                fw.write(System.getProperty("line.separator"));
                Arrays.stream(obj.getClass().getFields()).forEach(field -> {
                    try {
                        if (!field.getName().equalsIgnoreCase("streamIndices") && !field.getName().equalsIgnoreCase("cache")) {
//                            System.out.println("Working on: " + field.getName());
                            if (field.getType().isArray()) {
                                if (field.getType().toString().equalsIgnoreCase("class [I")) {
                                    fw.write(field.getName() + "= " + Arrays.toString((int[]) field.get(obj)) + ";");
                                    fw.write(System.getProperty("line.separator"));
                                } else {
                                    try {
                                        fw.write(field.getName() + "= " + Arrays.toString((String[]) field.get(obj)) + ";");
                                        fw.write(System.getProperty("line.separator"));
                                    } catch (ClassCastException e) {
                                        fw.write(field.getName() + "= " + field.get(obj) + ";");
                                        fw.write(System.getProperty("line.separator"));
                                    }
                                }

                            } else {
                                fw.write(field.getName() + "= " + field.get(obj) + "\";");
                                fw.write(System.getProperty("line.separator"));
                            }
                        }

                    } catch (IOException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
//		try {
//			FileWriter fw = new FileWriter(System.getProperty("user.home") + "/Desktop/Item Dump.txt");
//			for (int i = totalItems - 9000; i < totalItems; i++) {
//				ItemDefinition item = ItemDefinition.forID(i);
//				fw.write("case " + i + ":");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.name = \"" + item.name + "\";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelID= " + item.modelId + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.maleModel= " + item.maleModel + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.femaleModel= " + item.femaleModel + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelZoom = " + item.modelZoom + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelRotationX = " + item.modelRotation1 + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelRotationY = " + item.modelRotation2 + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelOffset1 = " + item.modelOffset1 + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.modelOffset2 = " + item.modelOffset2 + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.description = \"" + item.description + "\";");
//				fw.write(System.getProperty("line.separator"));
//
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.value = " + item.value + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("itemDef.team = " + item.team + ";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("break;");
//				fw.write(System.getProperty("line.separator"));
//				fw.write(System.getProperty("line.separator"));
//			}
//			fw.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
                fw.write(System.getProperty("line.separator"));
                fw.write(System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dumpList() {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.home") + "/Desktop/item_data.json");
            for (int i = 0; i < totalItems; i++) {
                ItemDefinition itemDefinition = ItemDefinition.forID(i);
                fw.write("id: " + itemDefinition.id + " - " + itemDefinition.name + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dumpStackableList() {
        try {
            File file = new File("stackables.dat");

            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < totalItems; i++) {
                    ItemDefinition definition = forID(i);
                    if (definition != null) {
                        writer.write(definition.id + "\t" + definition.stackable);
                        writer.newLine();
                    } else {
                        writer.write(i + "\tfalse");
                        writer.newLine();
                    }
                }
            }

            System.out.println("Finished dumping noted items definitions.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] unNoteable = {};

    public static void dumpNotes() {
        try {
            FileOutputStream out = new FileOutputStream(new File("notes.dat"));
            for (int j = 0; j < totalItems; j++) {
                ItemDefinition item = ItemDefinition.forID(j);
                for (int i = 0; i < totalItems; i++)
                    if (j == unNoteable[i] + 1)
                        out.write(0);
                    else
                        out.write(item.certTemplateID != -1 ? 0 : 1);
            }
            out.write(-1);
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void dumpStackable() {
        try {
            FileOutputStream out = new FileOutputStream(new File("stackable.dat"));
            for (int j = 0; j < totalItems; j++) {
                ItemDefinition item = ItemDefinition.forID(j);
                out.write(item.stackable ? 1 : 0);
            }
            out.write(-1);
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void dumpNotableList() {
        try {
            File file = new File("note_id.dat");

            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < totalItems; i++) {
                    ItemDefinition definition = ItemDefinition.forID(i);
                    if (definition != null) {
                        if (definition.certTemplateID == -1 && definition.certID != -1) {
                            writer.write(definition.id + "\t" + definition.certID);
                            writer.newLine();
                        }
                    } else {
                        writer.write(i + "\t-1");
                        writer.newLine();
                    }
                }
            }

            System.out.println("Finished dumping noted items definitions.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toNote() {
        ItemDefinition itemDef = forID(certTemplateID);
        modelId = itemDef.modelId;
        modelZoom = itemDef.modelZoom;
        modelRotation1 = itemDef.modelRotation1;
        modelRotation2 = itemDef.modelRotation2;

        anInt204 = itemDef.anInt204;
        modelOffset1 = itemDef.modelOffset1;
        modelOffset2 = itemDef.modelOffset2;
        modifiedModelColors = itemDef.modifiedModelColors;
        originalModelColors = itemDef.originalModelColors;
        ItemDefinition itemDef_1 = forID(certID);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        if (itemDef_1.name != null) {
            char c = itemDef_1.name.charAt(0);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
                s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    public static Sprite getSmallSprite(int itemId) {
        ItemDefinition itemDef = forID(itemId);
        Model model = itemDef.method201(1);
        if (model == null) {
            return null;
        }
        Sprite sprite1 = null;
        if (itemDef.certTemplateID != -1) {
            sprite1 = getSprite(itemDef.certID, 10, -1);
            if (sprite1 == null) {
                return null;
            }
        }
        Sprite enabledSprite = new Sprite(18, 18);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(18, 18, enabledSprite.myPixels, new float[1024]);
        DrawingArea.method336(18, 0, 0, 0, 18);
        Rasterizer.method364();
        int k3 = (int) (itemDef.modelZoom * 1.6D);
        int l3 = Rasterizer.anIntArray1470[itemDef.modelRotation1] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.modelRotation1] * k3 >> 16;
        model.method482(itemDef.modelRotation2, itemDef.anInt204, itemDef.modelRotation1, itemDef.modelOffset1,
                l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite1.maxWidth;
            int j6 = sprite1.maxHeight;
            sprite1.maxWidth = 18;
            sprite1.maxHeight = 18;
            sprite1.drawSprite(0, 0);
            sprite1.maxWidth = l5;
            sprite1.maxHeight = j6;
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, new float[1024]);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;

        enabledSprite.maxWidth = 18;
        enabledSprite.maxHeight = 18;

        return enabledSprite;
    }

    public static Sprite getSprite(int itemId, int itemAmount, int highlightColor) {
        if (highlightColor == 0) {
            Sprite sprite = (Sprite) mruNodes1.insertFromCache(itemId);
            if (sprite != null && sprite.maxHeight != itemAmount && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null)
                return sprite;
        }
        ItemDefinition itemDef = forID(itemId);
        if (itemDef.stackIDs == null)
            itemAmount = -1;
        if (itemAmount > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++)
                if (itemAmount >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
                    i1 = itemDef.stackIDs[j1];

            if (i1 != -1)
                itemDef = forID(i1);
        }
        Model model = itemDef.method201(1);
        if (model == null)
            return null;
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null)
                return null;
        } else if (itemDef.opcode140 != -1) {
            sprite = getSprite(itemDef.opcode139, itemAmount, -1);
            if (sprite == null)
                return null;
        } else if (itemDef.opcode149 != -1) {
            sprite = getSprite(itemDef.opcode148, itemAmount, -1);
            if (sprite == null)
                return null;
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels, new float[1024]);
        DrawingArea.method336(32, 0, 0, 0, 32);
        Rasterizer.method364();
        if (itemDef.opcode149 != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        int k3 = itemDef.modelZoom;
        if (highlightColor == -1)
            k3 = (int) ((double) k3 * 1.5D);
        if (highlightColor > 0)
            k3 = (int) ((double) k3 * 1.04D);
        int l3 = Rasterizer.anIntArray1470[itemDef.modelRotation1] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.modelRotation1] * k3 >> 16;
        model.method482(itemDef.modelRotation2, itemDef.anInt204, itemDef.modelRotation1, itemDef.modelOffset1,
                l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
        if (itemDef.opcode140 != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--)
                if (sprite2.myPixels[i5 + j4 * 32] == 0)
                    if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1)
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
                        sprite2.myPixels[i5 + j4 * 32] = 1;

        }

        if (highlightColor > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--)
                    if (sprite2.myPixels[j5 + k4 * 32] == 0)
                        if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1)
                            sprite2.myPixels[j5 + k4 * 32] = highlightColor;
                        else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
                            sprite2.myPixels[j5 + k4 * 32] = highlightColor;
                        else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
                            sprite2.myPixels[j5 + k4 * 32] = highlightColor;
                        else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
                            sprite2.myPixels[j5 + k4 * 32] = highlightColor;

            }

        } else if (highlightColor == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--)
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
                            && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;

            }

        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (highlightColor == 0)
            mruNodes1.removeFromCache(sprite2, itemId);
        DrawingArea.initDrawingArea(j2, i2, ai1, new float[1024]);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (itemDef.stackable)
            sprite2.maxWidth = 33;
        else
            sprite2.maxWidth = 32;
        sprite2.maxHeight = itemAmount;
        return sprite2;
    }

    public Model method201(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++)
                if (i >= stackAmounts[k] && stackAmounts[k] != 0)
                    j = stackIDs[k];

            if (j != -1)
                return forID(j).method201(1);
        }
        Model model = (Model) mruNodes2.insertFromCache(id);
        if (model != null)
            return model;
        model = Model.method462(modelId);
        if (model == null)
            return null;
        if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
            model.method478(anInt167, anInt191, anInt192);
        if (modifiedModelColors != null) {
            for (int l = 0; l < modifiedModelColors.length; l++)
                model.method476(modifiedModelColors[l], originalModelColors[l]);

        }
        model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
        model.aBoolean1659 = true;
        mruNodes2.removeFromCache(model, id);
        return model;
    }

    public Model method202(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++)
                if (i >= stackAmounts[k] && stackAmounts[k] != 0)
                    j = stackIDs[k];

            if (j != -1)
                return forID(j).method202(1);
        }
        Model model = Model.method462(modelId);
        if (model == null)
            return null;
        if (modifiedModelColors != null) {
            for (int l = 0; l < modifiedModelColors.length; l++)
                model.method476(modifiedModelColors[l], originalModelColors[l]);

        }
        return model;
    }

    private ItemDefinition() {
        id = -1;
    }

    private byte aByte154;
    public int value;
    public int[] originalModelColors;
    public int[] modifiedModelColors;

    private short[] originalTextureColors;
    private short[] modifiedTextureColors;

    public int id;
    public static MRUNodes mruNodes1 = new MRUNodes(100);
    public static MRUNodes mruNodes2 = new MRUNodes(50);

    public boolean membersObject;
    private int anInt162;
    public int certTemplateID;
    private int anInt164;
    public int maleModel;
    private int anInt166;
    private int anInt167;
    public String groundOptions[];
    public int modelOffset1;
    public String name;
    private static ItemDefinition[] cache;
    private int anInt173;
    public int modelId;
    private int anInt175;
    public boolean stackable;
    public String description;
    public int certID;
    private static int cacheIndex;
    public int modelZoom;
    private static Stream stream;
    private int anInt184;
    private int anInt185;
    private int anInt188;
    public String inventoryOptions[];
    public String equipActions[];
    public int modelRotation1;
    private int anInt191;
    private int anInt192;
    private int[] stackIDs;
    public int modelOffset2;
    private static int[] streamIndices;
    private int anInt196;
    private int anInt197;
    public int modelRotation2;
    public int femaleModel;
    private int[] stackAmounts;
    public int team;
    public static int totalItems;
    private int anInt204;
    private byte aByte205;
    public boolean searchableItem;
    private static BufferedWriter writer;

}

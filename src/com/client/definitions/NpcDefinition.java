package com.client.definitions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//
//import org.apache.commons.io.FileUtils;

import com.client.Class36;
import com.client.Client;
import com.client.Configuration;
import com.client.MRUNodes;
import com.client.Model;
import com.client.Stream;
import com.client.StreamLoader;

public final class NpcDefinition {

    private static ItemDefinition getRecoloredItemDefinition(int itemId, int[] colors) {
        ItemDefinition itemDefinition = ItemDefinition.forID(itemId);
        itemDefinition.originalModelColors = colors;
        return itemDefinition;
    }

    public static NpcDefinition forID(int i) {
        for (int j = 0; j < 20; j++)
            if (cache[j].interfaceType == i)
                return cache[j];

        anInt56 = (anInt56 + 1) % 20;
        NpcDefinition entityDef = cache[anInt56] = new NpcDefinition();
        stream.currentOffset = streamIndices[i];
        entityDef.interfaceType = i;
        entityDef.readValues(stream);
//		if(i == 100) {
//			entityDef.models[0] = 379; //HEAD
//			entityDef.models[1] = 368; //JAW
//			entityDef.models[2] = 468; //CHEST
//			entityDef.models[3] = 440; //CAPE
//			entityDef.models[4] =353; //ARM
//			entityDef.models[5] = 339; //HAND
////			entityDef.models[6] = entityDef.femaleModel; //HAND
//		}
        if (i == 3455) {
            entityDef.name = "Derek";
            entityDef.description = "An ancient nephalem ghost.";
        }
        if (i == 1039) {
            entityDef.name = "Jewel Merchant";
            entityDef.description = "His shop is well stocked";
        }
        if (i == 306) {
            entityDef.name = "Journey Guide";
            entityDef.description = "Talk to him to begin taking journeys";
            entityDef.actions = new String[]{"Talk-to", null, "Start Journey", null, null};
        }
        if (i == 338) {
            entityDef = forID(301);
            ItemDefinition weapon = ItemDefinition.forID(20733);
            ItemDefinition head = ItemDefinition.forID(11919);
            ItemDefinition chest = ItemDefinition.forID(7399);
            ItemDefinition legs = ItemDefinition.forID(7398);
            ItemDefinition feet = ItemDefinition.forID(6920);
            ItemDefinition cape = ItemDefinition.forID(1052);
            ItemDefinition hands = ItemDefinition.forID(7462);

            entityDef.models = new int[9];
            entityDef.models[0] = head.femaleModel; //HEAD
            entityDef.models[1] = ItemDefinition.forID(12851).femaleModel; //JAW
            entityDef.models[2] = chest.femaleModel - 1; //CHEST
            entityDef.models[3] = cape.femaleModel; //CAPE
            entityDef.models[4] = chest.femaleModel; //ARM
            entityDef.models[5] = hands.femaleModel; //HAND
            entityDef.models[6] = weapon.femaleModel; //WEP
            entityDef.models[7] = legs.femaleModel; //LEG
            entityDef.models[8] = feet.femaleModel; //BOOT

            entityDef.name = "Oxhead";
            entityDef.combatLevel = 112;
        }
        if(i == 2872) {
            ItemDefinition weapon = ItemDefinition.forID(4212);
            ItemDefinition head = ItemDefinition.forID(12419);
            ItemDefinition chest = ItemDefinition.forID(12420);
            ItemDefinition legs = ItemDefinition.forID(12421);
            ItemDefinition feet = ItemDefinition.forID(6920);
            ItemDefinition cape = ItemDefinition.forID(1052);
            ItemDefinition hands = ItemDefinition.forID(7462);

            entityDef.models = new int[9];
            entityDef.models[0] = head.femaleModel; //HEAD
            entityDef.models[1] = 246; //JAW
            entityDef.models[2] = chest.femaleModel; //CHEST
            entityDef.models[3] = cape.femaleModel; //CAPE
            entityDef.models[4] = 31187; //ARM
            entityDef.models[5] = hands.femaleModel; //HAND
            entityDef.models[6] = weapon.femaleModel; //WEP
            entityDef.models[7] = legs.femaleModel; //LEG
            entityDef.models[8] = feet.femaleModel; //BOOT
        }
        if(i == 3169) {
            ItemDefinition weapon = ItemDefinition.forID(21396);
            ItemDefinition head = ItemDefinition.forID(12419);
            ItemDefinition chest = ItemDefinition.forID(12420);
            ItemDefinition legs = ItemDefinition.forID(12421);
            ItemDefinition feet = ItemDefinition.forID(6920);
            ItemDefinition cape = ItemDefinition.forID(1052);
            ItemDefinition hands = ItemDefinition.forID(7462);

            entityDef.models = new int[9];
//            entityDef.models[0] = head.femaleModel; //HEAD
//            entityDef.models[1] = 246; //JAW
//            entityDef.models[2] = chest.femaleModel; //CHEST
//            entityDef.models[3] = cape.femaleModel; //CAPE
//            entityDef.models[4] = 31187; //ARM
//            entityDef.models[5] = hands.femaleModel; //HAND
            entityDef.models[6] = weapon.femaleModel; //WEP
//            entityDef.models[7] = legs.femaleModel; //LEG
//            entityDef.models[8] = feet.femaleModel; //BOOT
        }
        if (i == 3308) {
            entityDef.name = "Draynor Representative";
        }
        if (i == 5832) {
            entityDef.actions = new String[]{"Talk-to", null, "Pickpocket", "Trade", null};
        }
        if (i == 5736) {
            entityDef.standAnim = 106;
            entityDef.walkAnim = 106;
            entityDef.name = "Fairy Trader";
            entityDef.description = "Loves dust.";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }

        if (i == 5527) {
            entityDef.name = "@red@Donator Shop";
            entityDef.description = "@yel@Thanks for all the support!";
            entityDef.actions = new String[]{"Trade", null, null, null, null};

        }
        if (i == 507) {
            entityDef.name = "@cya@General Store";
            entityDef.description = "@yel@General store for all players";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 3216) {
            entityDef.name = "@cya@Combat Shop";
            entityDef.description = "@yel@Buy Combat weapons & Armour here";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 3217) {
            entityDef.name = "@cya@Range Shop";
            entityDef.description = "@yel@Buy Ranged weapons & Gear here";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 3218) {
            entityDef.name = "@cya@Magical Shop";
            entityDef.description = "@yel@Buy Magic weapons & Gear here";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 3219) {
            entityDef.name = "@cya@Cooking Shop";
            entityDef.description = "@yel@Buy Potions & Consumables here";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 506) {
            entityDef.name = "Shop Owner";
            entityDef.description = "General store";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
            //}
            //if(i==3218 || i ==3217){
            //entityDef.actions = new String[] { "Trade", null, null, null, null };

        }
        if (i == 311) {
            entityDef.name = "@gre@Ironman Assistant";
            entityDef.actions = new String[]{"Talk-To", "Armour", null, null, null};
            entityDef.description = "@yel@Revert Ironman status here & More";
        }
        if (i == 2989) {
            entityDef.name = "@gre@Prestige Assistant";
            entityDef.actions = new String[]{"Talk-To", null, null, null, null};
            entityDef.description = "@yel@Prestige your skills here";
        }
        if (i == 7456) {
            entityDef.name = "@cya@Item Repairs";
            entityDef.actions = new String[]{"Talk-To", null, null, null, null};
            entityDef.description = "@yel@Repair your items with me!";
        }
        if (i == 7303) {
            entityDef.name = "@cya@Clue Scroll Master";
            entityDef.description = "@yel@Convert your clue scrolls with me";
        }
        //New Home Npcs
        if (i == 7330) {
            entityDef.name = "@cya@CEO";
            entityDef.actions = new String[]{"Talk-To", null, null, null, null};
            entityDef.description = "@gre@This guy means business...";
        }
        if (i == 7924) {
            entityDef.name = "@cya@Regional Director";
            entityDef.description = "@gre@Looks like he helps run the CEO's agency.";
        }

        //End
        if (i == 5314) {
            entityDef.name = "@cya@Mystical Wizard";
            entityDef.actions = new String[]{"Teleport", "Previous Location", null, null, null};
            entityDef.description = "@yel@This wizard has the power to teleport you to many locations.";
        }
        if (i == 2123) {
            entityDef.name = "Bank Guard";
        }
        if (i == 1909) {
            entityDef.name = "@cya@Information Clerk";
            entityDef.description = "@yel@See Os-Revolution Info";
        }
        if (i == 315) {
            entityDef.name = "@cya@Emblem Master";
            entityDef.actions = new String[]{"Talk-To", null, "Trade", "Hide-Streaks", "Skull"};
            entityDef.description = "@yel@Check out my Emblem Shop & More";
        }
        if (i == 3307) {
            entityDef.name = "@cya@Combat Master";
            entityDef.description = "@yel@I can reset you combat skills & More";
        }
        if (i == 2110) {
            entityDef.name = "@cya@Pet Gambler";
            entityDef.actions = new String[]{"Talk-To", null, null, null, null};
            entityDef.description = "@yel@Gamble for a possible pet!";
        }
        if (i == 6875) {
            entityDef.name = "@cya@Nurse";
            entityDef.actions = new String[]{"Heal", null, null, null, null};
            entityDef.description = "@yel@I will heal your Hp, Prayer, Stamina, & Special Attack";
        }
        if (i == 4306) {
            entityDef.name = "@cya@Skillcape Master";
            entityDef.actions = new String[]{"Trade", null, null, null, null};
            entityDef.description = "@yel@You can find all your Skillcapes here";
        }
        if (i == 6481) {
            entityDef.name = "@cya@Mac";
            entityDef.actions = new String[]{"Talk-To", null, null, null, null};
            entityDef.description = "@yel@Check my dirty sack ;)";
        }

        if (i == 771) {
            entityDef.name = "";
            entityDef.models = new int[1];
            entityDef.models[0] = 1822;
            entityDef.actions = new String[5];
            entityDef.actions[0] = "";
            entityDef.actions[1] = null;
            entityDef.actions[2] = null;
            entityDef.actions[3] = null;
            entityDef.actions[4] = null;
            entityDef.description = "";
            entityDef.onMinimap = false;
        }

        if (i == 6295) {
            entityDef.name = "@red@Demon @whi@God";
            entityDef.description = "A mighty foe, only the best can defeat!";
            entityDef.combatLevel = 5268;
            entityDef.onMinimap = true;
        }
        if (i == 3257) {
            entityDef.actions = new String[]{"Trade", null, null, null, null};
        }
        if (i == 4625) {
            entityDef.name = "Donator shop";
            entityDef.actions = new String[]{"Talk-to", "Trade", null, null, null};
        }
        if (i == 8026) {
            entityDef.name = "Vorkath";
            // entityDef.combatLevel = 732;
            entityDef.models = new int[]{35023};
            entityDef.standAnim = 7946;
            entityDef.onMinimap = true;
            entityDef.actions = new String[5];
            entityDef.actions = new String[]{"Poke", null, null, null, null};
            entityDef.anInt86 = 100;
            entityDef.anInt91 = 100;
        }
        if (i == 8027) {
            entityDef.name = "Vorkath";
            entityDef.combatLevel = 732;
            entityDef.models = new int[]{35023};
            entityDef.standAnim = 7950;
            entityDef.onMinimap = true;
            entityDef.actions = new String[5];
            entityDef.actions = new String[]{null, null, null, null, null};
            entityDef.anInt86 = 100;
            entityDef.anInt91 = 100;
        }
        if (i == 8028) {
            entityDef.name = "Vorkath";
            entityDef.combatLevel = 732;
            entityDef.models = new int[]{35023};
            entityDef.standAnim = 7948;
            entityDef.onMinimap = true;
            entityDef.actions = new String[5];
            entityDef.actions = new String[]{null, "Attack", null, null, null};
            entityDef.anInt86 = 100;
            entityDef.anInt91 = 100;
        }

        if (i == 637) {
            entityDef.actions = new String[]{"Talk-to", null, "Trade", "Teleport", null};
        }

        if (i == 1328) {
            entityDef.actions = new String[]{"Talk-to", null, "Trade", null, null};
        }

        if (i == 7144) {
            entityDef.anInt75 = 0;
        }
        if (i == 963) {
            entityDef.anInt75 = 6;
        }
        if (i == 7145) {
            entityDef.anInt75 = 1;
        }
        if (i == 7146) {
            entityDef.anInt75 = 2;
        }
        NpcDefinition original = NpcDefinition.forID(2637);
        switch (i) {
            case 156:
                 original = NpcDefinition.forID(2637);
//                entityDef.anInt75 = 2; //overhead
                entityDef.boundDim = 1;
                entityDef.name = "Shrinx";
                entityDef.actions = new String[5];
                entityDef.actions[0] = null;
                entityDef.actions[1] = null;
                entityDef.actions[2] = "Pick-Up";
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.models = original.models;
                entityDef.standAnim = original.standAnim;
                entityDef.walkAnim = original.walkAnim;
                entityDef.anInt58 = original.anInt58;
                entityDef.anInt55 = original.anInt55;
                entityDef.anInt83 = original.anInt83;
                entityDef.originalColors = original.originalColors;
                entityDef.newColors = original.newColors;
                entityDef.anInt86 = 90;
                entityDef.anInt91 = 90;
                entityDef.description = "Season 1 Completionist";
                entityDef.onMinimap = false;
                entityDef.combatLevel = 0;
                break;
            case 6990:
                original = NpcDefinition.forID(2644);
                entityDef.boundDim = 1;
                entityDef.name = "Lovecats";
                entityDef.actions = new String[5];
                entityDef.actions[0] = null;
                entityDef.actions[1] = null;
                entityDef.actions[2] = "Pick-Up";
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.models = original.models;
                entityDef.standAnim = original.standAnim;
                entityDef.walkAnim = original.walkAnim;
                entityDef.anInt58 = original.anInt58;
                entityDef.anInt55 = original.anInt55;
                entityDef.anInt83 = original.anInt83;
                entityDef.originalColors = original.originalColors;
                entityDef.newColors = original.newColors;
                entityDef.anInt86 = 90;
                entityDef.anInt91 = 90;
                entityDef.description = "Play-Pass Season Completionist";
                entityDef.onMinimap = false;
                entityDef.combatLevel = 0;
                break;
            case 233:
                original = NpcDefinition.forID(233);
                entityDef.boundDim = 1;
                entityDef.name = "Cody Maverick";
                entityDef.actions = new String[5];
                entityDef.actions[0] = null;
                entityDef.actions[1] = null;
                entityDef.actions[2] = "Pick-Up";
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.models = original.models;
                entityDef.standAnim = original.standAnim;
                entityDef.walkAnim = original.walkAnim;
                entityDef.anInt58 = original.anInt58;
                entityDef.anInt55 = original.anInt55;
                entityDef.anInt83 = original.anInt83;
                entityDef.originalColors = original.originalColors;
                entityDef.newColors = original.newColors;
                entityDef.anInt86 = 90;
                entityDef.anInt91 = 90;
                entityDef.description = "Play-Pass Season Completionist";
                entityDef.onMinimap = false;
                entityDef.combatLevel = 0;
                break;
            case 4709:
                original = NpcDefinition.forID(4708);
                entityDef.boundDim = 1;
                entityDef.name = "Lion Cub..?";
                entityDef.actions = new String[5];
                entityDef.actions[0] = null;
                entityDef.actions[1] = null;
                entityDef.actions[2] = "Pick-Up";
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.models = original.models;
                entityDef.standAnim = original.standAnim;
                entityDef.walkAnim = original.walkAnim;
                entityDef.anInt58 = original.anInt58;
                entityDef.anInt55 = original.anInt55;
                entityDef.anInt83 = original.anInt83;
                entityDef.originalColors = original.originalColors;
                entityDef.newColors = original.newColors;
                entityDef.anInt86 = 40;
                entityDef.anInt91 = 40;
                entityDef.description = "Play-Pass Season Completionist";
                entityDef.onMinimap = false;
                entityDef.combatLevel = 0;
                break;
            case 5567:
                entityDef.actions = new String[5];
                entityDef.actions[0] = "Claim";
                entityDef.actions[1] = null;
                entityDef.actions[2] = null;
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.onMinimap = true;
                break;
            case 803:
                 original = NpcDefinition.forID(805);
//                entityDef.anInt75 = 2; //overhead
                entityDef.boundDim = 1;
                entityDef.name = "Gilded Sheep";
                entityDef.actions = new String[5];
                entityDef.actions[0] = null;
                entityDef.actions[1] = null;
                entityDef.actions[2] = "Pick-Up";
                entityDef.actions[3] = null;
                entityDef.actions[4] = null;
                entityDef.models = original.models;
                entityDef.standAnim = original.standAnim;
                entityDef.walkAnim = original.walkAnim;
                entityDef.anInt58 = original.anInt58;
                entityDef.anInt55 = original.anInt55;
                entityDef.anInt83 = original.anInt83;
                entityDef.originalColors = original.originalColors;
                entityDef.newColors = original.newColors;
                entityDef.anInt86 = 90;
                entityDef.anInt91 = 90;
                entityDef.description = "Season 1 Completionist";
                entityDef.onMinimap = false;
                entityDef.combatLevel = 0;
                break;
        }
        return entityDef;
    }

    public static int totalAmount;

    public static void unpackConfig(StreamLoader streamLoader) {
        stream = new Stream(streamLoader.getDataForName("npc.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("npc.idx"));
        totalAmount = stream.readUnsignedWord();
        streamIndices = new int[totalAmount];
        int i = 2;
        for (int j = 0; j < totalAmount; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }

        cache = new NpcDefinition[20];
        for (int k = 0; k < 20; k++)
            cache[k] = new NpcDefinition();
        for (int index = 0; index < totalAmount; index++) {
            NpcDefinition ed = forID(index);
            if (ed == null)
                continue;
            if (ed.name == null)
                continue;
        }

        dumpList();
    }

    /*
     * public void readValues(Stream stream) { do { int i =
     * stream.readUnsignedByte(); if (i == 0) return; if (i == 1) { int j =
     * stream.readUnsignedByte(); models = new int[j]; for (int j1 = 0; j1 < j;
     * j1++) models[j1] = stream.readUnsignedWord();
     *
     * } else if (i == 2) name = stream.readString(); else if (i == 3) description =
     * stream.readString(); else if (i == 12) squareLength =
     * stream.readSignedByte(); else if (i == 13) standAnim =
     * stream.readUnsignedWord(); else if (i == 14) walkAnim =
     * stream.readUnsignedWord(); else if (i == 17) { walkAnim =
     * stream.readUnsignedWord(); anInt58 = stream.readUnsignedWord(); anInt83 =
     * stream.readUnsignedWord(); anInt55 = stream.readUnsignedWord(); if (anInt58
     * == 65535) { anInt58 = -1; } if (anInt83 == 65535) { anInt83 = -1; } if
     * (anInt55 == 65535) { anInt55 = -1; } } else if (i >= 30 && i < 40) { if
     * (actions == null) actions = new String[5]; actions[i - 30] =
     * stream.readString(); if (actions[i - 30].equalsIgnoreCase("hidden"))
     * actions[i - 30] = null; } else if (i == 40) { int k =
     * stream.readUnsignedByte(); originalColors = new int[k]; newColors = new
     * int[k]; for (int k1 = 0; k1 < k; k1++) { originalColors[k1] =
     * stream.readUnsignedWord(); newColors[k1] = stream.readUnsignedWord(); }
     *
     * } else if (i == 60) { int l = stream.readUnsignedByte(); dialogueModels = new
     * int[l]; for (int l1 = 0; l1 < l; l1++) dialogueModels[l1] =
     * stream.readUnsignedWord();
     *
     * } else if (i == 90) stream.readUnsignedWord(); else if (i == 91)
     * stream.readUnsignedWord(); else if (i == 92) stream.readUnsignedWord(); else
     * if (i == 93) minimapDot = false; else if (i == 95) combatLevel =
     * stream.readUnsignedWord(); else if (i == 97) anInt91 =
     * stream.readUnsignedWord(); else if (i == 98) anInt86 =
     * stream.readUnsignedWord(); else if (i == 99) aBoolean93 = true; else if (i ==
     * 100) anInt85 = stream.readSignedByte(); else if (i == 101) anInt92 =
     * stream.readSignedByte() * 5; else if (i == 102) anInt75 =
     * stream.readUnsignedByte(); else if (i == 103) getDegreesToTurn =
     * stream.readUnsignedByte(); else if (i == 106) { anInt57 =
     * stream.readUnsignedWord(); if (anInt57 == 65535) anInt57 = -1; anInt59 =
     * stream.readUnsignedWord(); if (anInt59 == 65535) anInt59 = -1; int i1 =
     * stream.readUnsignedByte(); childrenIDs = new int[i1 + 1]; for (int i2 = 0; i2
     * <= i1; i2++) { childrenIDs[i2] = stream.readUnsignedWord(); if
     * (childrenIDs[i2] == 65535) childrenIDs[i2] = -1; }
     *
     * } else if (i == 107) aBoolean84 = false; } while (true); }
     */
    private void readValues(Stream stream) {
        while (true) {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0)
                return;
            if (opcode == 1) {
                int j = stream.readUnsignedByte();
                models = new int[j];
                for (int j1 = 0; j1 < j; j1++)
                    models[j1] = stream.readUnsignedWord();

            } else if (opcode == 2)
                name = stream.readString();
            else if (opcode == 3)
                description = stream.readString();
            else if (opcode == 12)
                boundDim = stream.readSignedByte();
            else if (opcode == 13)
                standAnim = stream.readUnsignedWord();
            else if (opcode == 14)
                walkAnim = stream.readUnsignedWord();
            else if (opcode == 17) {
                walkAnim = stream.readUnsignedWord();
                anInt58 = stream.readUnsignedWord();
                anInt83 = stream.readUnsignedWord();
                anInt55 = stream.readUnsignedWord();
                if (anInt58 == 65535) {
                    anInt58 = -1;
                }
                if (anInt83 == 65535) {
                    anInt83 = -1;
                }
                if (anInt55 == 65535) {
                    anInt55 = -1;
                }
            } else if (opcode >= 30 && opcode < 40) {
                if (actions == null)
                    actions = new String[5];
                actions[opcode - 30] = stream.readString();
                if (actions[opcode - 30].equalsIgnoreCase("hidden"))
                    actions[opcode - 30] = null;
            } else if (opcode == 40) {
                int k = stream.readUnsignedByte();
                originalColors = new int[k];
                newColors = new int[k];
                for (int k1 = 0; k1 < k; k1++) {
                    originalColors[k1] = stream.readUnsignedWord();
                    newColors[k1] = stream.readUnsignedWord();
                }

            } else if (opcode == 60) {
                int l = stream.readUnsignedByte();
                dialogueModels = new int[l];
                for (int l1 = 0; l1 < l; l1++)
                    dialogueModels[l1] = stream.readUnsignedWord();

            } else if (opcode == 93)
                onMinimap = false;
            else if (opcode == 95)
                combatLevel = stream.readUnsignedWord();
            else if (opcode == 97)
                anInt91 = stream.readUnsignedWord();
            else if (opcode == 98)
                anInt86 = stream.readUnsignedWord();
            else if (opcode == 99)
                aBoolean93 = true;
            else if (opcode == 100)
                anInt85 = stream.readSignedByte();
            else if (opcode == 101)
                anInt92 = stream.readSignedByte();
            else if (opcode == 102)
                anInt75 = stream.readSignedByte();
            else if (opcode == 103)
                getDegreesToTurn = stream.readSignedByte();
            else if (opcode == 106) {
                anInt57 = stream.readUnsignedWord();
                if (anInt57 == 65535)
                    anInt57 = -1;
                anInt59 = stream.readUnsignedWord();
                if (anInt59 == 65535)
                    anInt59 = -1;
                int i1 = stream.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                for (int i2 = 0; i2 <= i1; i2++) {
                    childrenIDs[i2] = stream.readUnsignedWord();
                    if (childrenIDs[i2] == 65535)
                        childrenIDs[i2] = -1;
                }
            } else if (opcode == 107)
                aBoolean84 = false;
        }
    }

    public Model method160() {
        if (childrenIDs != null) {
            NpcDefinition entityDef = method161();
            if (entityDef == null)
                return null;
            else
                return entityDef.method160();
        }
        if (dialogueModels == null) {
            return null;
        }
        boolean flag1 = false;
        for (int i = 0; i < dialogueModels.length; i++)
            if (!Model.method463(dialogueModels[i]))
                flag1 = true;

        if (flag1)
            return null;
        Model aclass30_sub2_sub4_sub6s[] = new Model[dialogueModels.length];
        for (int j = 0; j < dialogueModels.length; j++)
            aclass30_sub2_sub4_sub6s[j] = Model.method462(dialogueModels[j]);

        Model model;
        if (aclass30_sub2_sub4_sub6s.length == 1)
            model = aclass30_sub2_sub4_sub6s[0];
        else
            model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);

        if (originalColors != null)
            for (int k = 0; k < originalColors.length; k++)
                model.replaceColor(originalColors[k], newColors[k]);

        return model;
    }

    public NpcDefinition method161() {
        int j = -1;
        if (anInt57 != -1 && anInt57 <= 2113) {
            VarBit varBit = VarBit.cache[anInt57];
            int k = varBit.anInt648;
            int l = varBit.anInt649;
            int i1 = varBit.anInt650;
            int j1 = Client.anIntArray1232[i1 - l];
            j = clientInstance.variousSettings[k] >> l & j1;
        } else if (anInt59 != -1)
            j = clientInstance.variousSettings[anInt59];
        if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1)
            return null;
        else
            return forID(childrenIDs[j]);
    }

    public Model method164(int j, int k, int ai[]) {
        if (childrenIDs != null) {
            NpcDefinition entityDef = method161();
            if (entityDef == null)
                return null;
            else
                return entityDef.method164(j, k, ai);
        }
        Model model = (Model) mruNodes.insertFromCache(interfaceType);
        if (model == null) {
            boolean flag = false;
            for (int i1 = 0; i1 < models.length; i1++)
                if (!Model.method463(models[i1]))
                    flag = true;

            if (flag)
                return null;
            Model aclass30_sub2_sub4_sub6s[] = new Model[models.length];
            for (int j1 = 0; j1 < models.length; j1++)
                aclass30_sub2_sub4_sub6s[j1] = Model.method462(models[j1]);

            if (aclass30_sub2_sub4_sub6s.length == 1)
                model = aclass30_sub2_sub4_sub6s[0];
            else
                model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
            if (originalColors != null) {
                for (int k1 = 0; k1 < originalColors.length; k1++)
                    model.replaceColor(originalColors[k1], newColors[k1]);

            }
            model.method469();
            model.method479(64 + anInt85, 850 + anInt92, -30, -50, -30, true);
            // model.method479(84 + anInt85, 1000 + anInt92, -90, -580, -90, true);
            mruNodes.removeFromCache(model, interfaceType);
        }
        Model model_1 = Model.EMPTY_MODEL;
        model_1.method464(model, Class36.method532(k) & Class36.method532(j));
        if (k != -1 && j != -1)
            model_1.method471(ai, j, k);
        else if (k != -1)
            model_1.method470(k);
        if (anInt91 != 128 || anInt86 != 128)
            model_1.method478(anInt91, anInt91, anInt86);
        model_1.method466();
        model_1.faceGroups = null;
        model_1.vertexGroups = null;
        if (boundDim == 1)
            model_1.aBoolean1659 = true;
        return model_1;
    }

    private NpcDefinition() {
        anInt55 = -1;
        anInt57 = walkAnim;
        anInt58 = walkAnim;
        anInt59 = walkAnim;
        combatLevel = -1;
        anInt64 = 1834;
        walkAnim = -1;
        boundDim = 1;
        anInt75 = -1;
        standAnim = -1;
        interfaceType = -1L;
        getDegreesToTurn = 32;
        anInt83 = -1;
        aBoolean84 = true;
        anInt86 = 128;
        onMinimap = true;
        anInt91 = 128;
        aBoolean93 = false;
    }

    public static void nullLoader() {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public static void dumpList() {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.home") + "/Desktop/NPC Dump.txt");
            for (int i = 0; i < totalAmount; i++) {
                NpcDefinition obj = forID(i);

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
                fw.write(System.getProperty("line.separator"));
                fw.write(System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            File file = new File(System.getProperty("user.home") + "/Desktop/npcList " + Configuration.dumpID + ".txt");
//
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//                for (int i = 0; i < totalAmount; i++) {
//                    NpcDefinition definition = forID(i);
//                    if (definition != null) {
//
//                        writer.write("npc = " + i + "\t" + definition.name + "\t" + definition.combatLevel + "\t"
//                                + definition.standAnim + "\t" + definition.walkAnim + "\t");
//                        writer.newLine();
//                    }
//                }
//            }
//
//            System.out.println("Finished dumping npc definitions.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void dumpSizes() {
        try {
            File file = new File(System.getProperty("user.home") + "/Desktop/npcSizes 143.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < totalAmount; i++) {
                    NpcDefinition definition = forID(i);
                    if (definition != null) {
                        writer.write(i + "	" + definition.boundDim);
                        writer.newLine();
                    }
                }
            }

            System.out.println("Finished dumping npc definitions.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int anInt55;
    public static int anInt56;
    public int anInt57;
    public int anInt58;
    public int anInt59;
    public static Stream stream;
    public int combatLevel;
    public final int anInt64;
    public String name;
    public String actions[];
    public int walkAnim;
    public byte boundDim;
    public int[] newColors;
    public static int[] streamIndices;
    public int[] dialogueModels;
    public int anInt75;
    public int[] originalColors;
    public int standAnim;
    public long interfaceType;
    public int getDegreesToTurn;
    public static NpcDefinition[] cache;
    public static Client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    public int anInt85;
    public int anInt86;
    public boolean onMinimap;
    public int childrenIDs[];
    public String description;
    public int anInt91;
    public int anInt92;
    public boolean aBoolean93;
    public int[] models;
    public static MRUNodes mruNodes = new MRUNodes(30);

}
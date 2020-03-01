package com.client;

final class Class18 {

    Class18(Stream stream) {
        int anInt341 = stream.readUnsignedWord();
        anIntArray342 = new int[anInt341];
        anIntArray343 = new int[anInt341][];
        for(int j = 0; j < anInt341; j++)
        	anIntArray342[j] = stream.readUnsignedWord();
		for(int j = 0; j < anInt341; j++)
			anIntArray343[j] = new int[stream.readUnsignedWord()];
        for(int j = 0; j < anInt341; j++)
			for(int l = 0; l < anIntArray343[j].length; l++)
				anIntArray343[j][l] = stream.readUnsignedWord();
    }

    final int[] anIntArray342;//anIntArray342
    final int[][] anIntArray343;//anIntArray343
}

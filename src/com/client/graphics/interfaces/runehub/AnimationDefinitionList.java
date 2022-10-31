package com.client.graphics.interfaces.runehub;

import com.client.definitions.AnimationDefinition;

import java.util.ArrayList;
import java.util.List;

public class AnimationDefinitionList {

    public List<AnimationDefinition> getDefinitions() {
        return definitions;
    }

    public AnimationDefinitionList() {
        this.definitions = new ArrayList<>();
    }

    private final List<AnimationDefinition> definitions;
}

package my_project.model;

import java.util.Map;

public class BlockTextures {
    private static final Map<String, Texture> textureMap = Map.of(
            "highlight", new Texture("src/my_project/resources/blocks/Highlight.png"),
            "dirt", new Texture("src/my_project/resources/blocks/Dirt.png"),
            "grass", new Texture("src/my_project/resources/blocks/Grass.png"),
            "stone", new Texture("src/my_project/resources/blocks/Stone.png"),
            "iron", new Texture("src/my_project/resources/blocks/Iron.png"),
            "leaf", new Texture("src/my_project/resources/blocks/Leaf.png"),
            "water", new Texture("src/my_project/resources/blocks/Water.png")
    );

    public static Texture getTexture(String name) {
        return textureMap.get(name);
    }
}

package my_project.model;

import java.util.Map;

public class ItemTextures {
    private static final Map<String, Texture> textureMap = Map.of(
            "dirt", new Texture("src/my_project/resources/items/Dirt.png"),
            "stone", new Texture("src/my_project/resources/items/Stone.png"),
            "brick", new Texture("src/my_project/resources/items/Brick.png")

    );

    public static Texture getTexture(String name) {
        return textureMap.get(name);
    }
}

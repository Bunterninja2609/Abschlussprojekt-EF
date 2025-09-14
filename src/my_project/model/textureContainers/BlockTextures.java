package my_project.model.textureContainers;

import my_project.model.Texture;

import java.util.Map;

public class BlockTextures {
    private static final String path = "src/my_project/resources/blocks/";
    private static final Map<String, Texture> textureMap = Map.of(
            "highlight", new Texture(path + "Highlight.png"),
            "dirt", new Texture(path + "Dirt.png"),
            "grass", new Texture(path + "Grass.png"),
            "stone", new Texture(path + "Stone.png"),
            "iron", new Texture(path + "Iron Ore.png"),
            "leaf", new Texture(path + "Leaf.png"),
            "water", new Texture(path + "Water.png"),
            "brick", new Texture(path + "Brick.png"),
            "sand", new Texture(path + "Sand.png"),
            "solidBorder", new Texture(path + "Solid Block Border.png")
    );

    public static Texture getTexture(String name) {
        return textureMap.get(name);
    }
}

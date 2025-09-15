package my_project.model.textureContainers;

import my_project.model.Texture;

import java.util.HashMap;

public class BlockTextures {
    private static final String path = "src/my_project/resources/blocks/";
    private static HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
    private BlockTextures() {
        textureMap.put("highlight", new Texture(path + "Highlight.png"));
        textureMap.put("dirt", new Texture(path + "Dirt.png"));
        textureMap.put("grass", new Texture(path + "Grass.png"));
        textureMap.put("stone", new Texture(path + "Stone.png"));
        textureMap.put("leaf", new Texture(path + "Leaf.png"));
        textureMap.put("water", new Texture(path + "Water.png"));
        textureMap.put("brick", new Texture(path + "Brick.png"));
        textureMap.put("sand", new Texture(path + "Sand.png"));
        textureMap.put("solidBorder", new Texture(path + "Solid Block Border.png"));
        textureMap.put("tnt", new Texture(path + "Tnt.png"));
    }
    private static BlockTextures instance = new BlockTextures();


    public static Texture getTexture(String name) {
        return textureMap.get(name);
    }
}

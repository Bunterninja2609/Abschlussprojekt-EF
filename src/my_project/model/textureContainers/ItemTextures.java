package my_project.model.textureContainers;

import my_project.model.Texture;

import java.util.Map;

public class ItemTextures {
    private static final String path = "src/my_project/resources/items/";
    private static final Map<String, Texture> textureMap = Map.of(
            "dirt", new Texture(path+"Dirt.png"),
            "stone", new Texture(path+"Stone.png"),
            "brick", new Texture(path+"Brick.png"),
            "sand", new Texture(path+"Sand.png"),
            "debug", new Texture(path+"Debug.png")

    );

    public static Texture getTexture(String name) {
        if (textureMap.containsKey(name)) {
            return textureMap.get(name);
        }else{
            return textureMap.get("debug");
        }

    }
}

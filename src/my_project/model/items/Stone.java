package my_project.model.items;

import my_project.model.ItemTextures;

public class Stone extends Item {
    public Stone() {
        super();
        name = "Stone";
        texture = ItemTextures.getTexture("stone");
    }
}

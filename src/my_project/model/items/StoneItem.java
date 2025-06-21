package my_project.model.items;

import my_project.model.ItemTextures;

public class StoneItem extends Item {
    public StoneItem(int amount) {
        super(amount);
        name = "Stone";
        texture = ItemTextures.getTexture("stone");
    }
}

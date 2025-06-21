package my_project.model.items;

import my_project.model.ItemTextures;

public class DirtItem extends Item {
    public DirtItem(int amount) {
        super(amount);
        name = "Dirt";
        texture = ItemTextures.getTexture("dirt");
    }
}

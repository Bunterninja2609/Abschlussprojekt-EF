package my_project.model.items;

import my_project.model.ItemTextures;

public class BrickItem extends Item {
    public BrickItem(int amount) {
        super(amount);
        name = "Brick";
        texture = ItemTextures.getTexture("brick");
    }
}

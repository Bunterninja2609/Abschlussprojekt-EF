package my_project.model.items;

import my_project.model.ItemTextures;
import my_project.model.blocks.Brick;

public class BrickItem extends BlockItem {
    public BrickItem(int amount) {
        super(amount);
        name = "Brick";
        block = Brick.class;
        texture = ItemTextures.getTexture("brick");
    }
}

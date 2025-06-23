package my_project.model.items;

import my_project.model.ItemTextures;
import my_project.model.blocks.Stone;

public class StoneItem extends BlockItem {
    public StoneItem(int amount) {
        super(amount);
        name = "Stone";
        block = Stone.class;
        texture = ItemTextures.getTexture("stone");
    }
}

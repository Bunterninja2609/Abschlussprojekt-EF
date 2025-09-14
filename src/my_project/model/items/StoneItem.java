package my_project.model.items;

import my_project.model.textureContainers.ItemTextures;
import my_project.model.blocks.BlockStone;

public class StoneItem extends BlockItem {
    public StoneItem(int amount) {
        super(amount);
        name = "Stone";
        block = BlockStone.class;
        texture = ItemTextures.getTexture("stone");
    }
}

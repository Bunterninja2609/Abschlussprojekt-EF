package my_project.model.items;

import my_project.model.blocks.BlockTnt;
import my_project.model.textureContainers.ItemTextures;

public class TntItem extends BlockItem {
    public TntItem(int amount) {
        super(amount);
        name = "Tnt";
        block = BlockTnt.class;
        texture = ItemTextures.getTexture("Debug");
    }
}
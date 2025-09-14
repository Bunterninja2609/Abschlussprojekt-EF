package my_project.model.items;

import my_project.model.textureContainers.ItemTextures;
import my_project.model.blocks.BlockDirt;

public class DirtItem extends BlockItem {
    public DirtItem(int amount) {
        super(amount);
        name = "Dirt";
        block = BlockDirt.class;
        texture = ItemTextures.getTexture("dirt");
    }
}

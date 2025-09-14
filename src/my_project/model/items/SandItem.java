package my_project.model.items;

import my_project.model.textureContainers.ItemTextures;
import my_project.model.blocks.BlockSand;

public class SandItem extends BlockItem {
    public SandItem(int amount) {
        super(amount);
        name = "Sand";
        block = BlockSand.class;
        texture = ItemTextures.getTexture("sand");
    }
}

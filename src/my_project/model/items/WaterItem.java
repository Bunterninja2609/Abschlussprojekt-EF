package my_project.model.items;

import my_project.model.blocks.BlockLava;
import my_project.model.blocks.Debug;
import my_project.model.textureContainers.ItemTextures;

public class WaterItem extends BlockItem{
    public WaterItem(int amount) {
        super(amount);
        name = "Lava";
        block = BlockLava.class;
        texture = ItemTextures.getTexture("debug");
    }
}

package my_project.model.items;

import my_project.model.textureContainers.ItemTextures;
import my_project.model.blocks.BlockBrick;

public class BrickItem extends BlockItem {
    public BrickItem(int amount) {
        super(amount);
        name = "Brick";
        block = BlockBrick.class;
        texture = ItemTextures.getTexture("brick");
    }
}

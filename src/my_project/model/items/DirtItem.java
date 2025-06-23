package my_project.model.items;

import my_project.model.ItemTextures;
import my_project.model.blocks.Dirt;

public class DirtItem extends BlockItem {
    public DirtItem(int amount) {
        super(amount);
        name = "Dirt";
        block = Dirt.class;
        texture = ItemTextures.getTexture("dirt");
    }
}

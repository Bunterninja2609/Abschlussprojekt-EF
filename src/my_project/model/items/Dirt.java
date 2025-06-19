package my_project.model.items;

import my_project.model.ItemTextures;

public class Dirt extends Item {
    public Dirt() {
        super();
        name = "Dirt";
        texture = ItemTextures.getTexture("dirt");
    }
}

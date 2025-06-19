package my_project.model.items;

import my_project.model.Texture;

public abstract class Item {

    protected int amount;
    protected String name;
    protected Texture texture;

    public Item() {
        amount = 1;
    }

    public String getName() {
        return name;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }
    public int getAmount() {
        return amount;
    }
    public Texture getTexture() {
        return texture;
    }
}

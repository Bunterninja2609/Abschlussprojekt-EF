package my_project.model.items;

import com.sun.javafx.geom.Vec2d;
import my_project.model.Texture;
import my_project.model.entities.Entity;

public abstract class Item {

    protected int amount;
    protected String name;
    protected Texture texture;

    public Item(int amount) {
        this.amount = amount;
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
    public void use(double x, double y, Entity user) {

    }
}

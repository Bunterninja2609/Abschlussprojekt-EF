package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.model.Inventory;

public abstract class Entity extends InteractiveGraphicalObject {

    protected double hitpoints;
    protected double speed;
    protected double stamina;
    protected double damage;
    protected Inventory inventory;

    public Entity(int invSize) {
        inventory = new Inventory(invSize);
    }

    protected void pathFind(double targetX,double targetY){

    }
}

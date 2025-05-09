package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;

public abstract class Entity extends InteractiveGraphicalObject {

    protected double hitpoints;
    protected double speed;
    protected double stamina;
    protected double damage;

    public Entity(){

    }

    protected void pathFind(double targetX,double targetY){

    }
}

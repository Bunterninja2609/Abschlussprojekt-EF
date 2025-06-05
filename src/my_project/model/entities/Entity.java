package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.EntityRenderer;
import my_project.control.Renderer;
import my_project.model.Cage;
import my_project.model.Collider;
import my_project.model.Inventory;

public abstract class Entity extends InteractiveGraphicalObject {

    protected double hitpoints;
    protected double speed;
    protected double stamina;
    protected double damage;
    protected Inventory inventory;
    protected Cage cage;

    public Entity(EntityRenderer er, int invSize) {
        cage = new Cage(this, 1, 1);
        inventory = new Inventory(invSize);
    }

    @Override
    public void update(double dt){
        cage.update(dt);
    }


    protected void pathFind(double targetX,double targetY){

    }

    public void drawHitbox(DrawTool drawTool) {
        drawTool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(width), Renderer.scale(height));
    }
    protected void move(double x, double y,double dt){
        moveX(x, dt);
        moveY(y, dt);
    }
    protected void moveX(double x,double dt){
        Collider colliderToCheck = cage.getLeftCollider();
        if (x > 0){
            colliderToCheck = cage.getRightCollider();
        }
        boolean isColliding = false;
        if (isColliding) {

        }
    }
    protected void moveY(double y,double dt){

    }
}

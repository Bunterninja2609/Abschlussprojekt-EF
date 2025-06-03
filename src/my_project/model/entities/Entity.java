package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Renderer;
import my_project.model.Collider;
import my_project.model.Inventory;

public abstract class Entity extends InteractiveGraphicalObject {

    protected double hitpoints;
    protected double speed;
    protected double stamina;
    protected double damage;
    protected Inventory inventory;

    protected Collider leftCollider;
    protected Collider rightCollider;
    protected Collider topCollider;
    protected Collider bottomCollider;


    public Entity(int invSize) {
        inventory = new Inventory(invSize);
        createColliders();
    }

    @Override
    public void update(double dt){
        setCollidersPos();
    }

    protected void pathFind(double targetX,double targetY){

    }

    public void drawHitbox(DrawTool drawTool) {
        drawTool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(width), Renderer.scale(height));
    }

    protected void createColliders() {
        leftCollider = new Collider(-1,1,1,height-2);
        rightCollider = new Collider(width,1,1,height-2);
        topCollider = new Collider(1,-1,width-2,1);
        bottomCollider = new Collider(1,height,width-2,1);
    }

    protected void setCollidersPos() {
        leftCollider.setPos(x,y);
        rightCollider.setPos(x,y);
        topCollider.setPos(x,y);
        bottomCollider.setPos(x,y);
    }
}

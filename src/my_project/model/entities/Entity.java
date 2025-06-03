package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Renderer;
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

    public void drawHitbox(DrawTool drawTool) {
        drawTool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(width), Renderer.scale(height));
    }
}

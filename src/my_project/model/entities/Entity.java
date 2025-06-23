package my_project.model.entities;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.CollisionHandler;
import my_project.control.EntityRenderer;
import my_project.control.ProgramController;
import my_project.control.Renderer;
import my_project.model.Cage;
import my_project.model.Collider;
import my_project.model.Inventory;
import my_project.model.Spritesheet;
import my_project.model.blocks.Block;

public abstract class Entity extends InteractiveGraphicalObject {

    protected Vec2d velocity = new Vec2d(0,0);
    protected double maxHitpoints;
    protected double hitpoints;
    protected double speed;
    protected double stamina;
    protected double damage;
    protected Inventory inventory;
    protected Cage cage;
    protected Spritesheet spritesheet;
    protected double fallDamageHeight = 0;
    protected double fallDamageFactor = 0;

    public Entity(EntityRenderer er, int invSize) {
        cage = new Cage(this, -1, 1);
        inventory = new Inventory(invSize);
    }

    @Override
    public void update(double dt){
        cage.update(dt);
        processMovement(dt);
    }


    protected void pathFind(double targetX,double targetY){

    }

    public void drawHitbox(DrawTool drawTool) {
        drawTool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(width), Renderer.scale(height));
    }
    protected void move(double x, double y,double dt){
        moveY(y, dt);
        moveX(x, dt);
    }
    protected void moveX(double mx,double dt){
        if (mx != 0) {
            float precision = 0.01f;
            double dir = ProgramController.clamp(-1, 1, Math.abs(mx) / mx);
            int maxIterations = 1000;
            int iteration = 0;
            Collider colliderToCheck = cage.getLeftCollider();
            if (mx > 0) {
                colliderToCheck = cage.getRightCollider();
            }
            if (!CollisionHandler.collidesWithBlock(colliderToCheck)) {
                x += mx * dt;
            }

            while (CollisionHandler.collidesWithBlock(colliderToCheck) && iteration < maxIterations) {
                x -= precision * dir;
                velocity.x = 0;
                iteration++;
            }
        }


        //System.out.println("iteration x: " + iteration);
    }
    protected void moveY(double my,double dt){
        if (my != 0) {
            float precision = 0.1f;
            double dir = ProgramController.clamp(-1, 1, Math.abs(my) / my);
            int maxIterations = 1000;
            int iteration = 0;
            Collider colliderToCheck = cage.getUpCollider();
            if (my > 0) {
                colliderToCheck = cage.getDownCollider();
            }
            if (!CollisionHandler.collidesWithBlock(colliderToCheck)) {
                y += my * dt;
            }

            while (CollisionHandler.collidesWithBlock(colliderToCheck) && iteration < maxIterations) {
                y -= precision * dir;
                if (Math.abs(velocity.y) > fallDamageHeight){
                    //System.out.println("Fall damage: " + (Math.abs(velocity.y)-fallDamageHeight)*fallDamageFactor);
                    damage(-(Math.abs(velocity.y)-fallDamageHeight)*fallDamageFactor);
                    System.out.println(hitpoints);
                }
                velocity.y = 0;
                iteration++;
            }
        }

        //System.out.println("iteration y: " + iteration);
    }
    protected void processMovement(double dt){
        double frictionX = 0.9;
        double frictionY = 0.99;

        move(velocity.x, velocity.y, dt);


        velocity.x *= frictionX;
        velocity.y *= frictionY;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void damage(double damageDealt) {
        this.hitpoints += damageDealt;
    }
}

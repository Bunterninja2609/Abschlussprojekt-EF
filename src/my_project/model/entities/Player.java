package my_project.model.entities;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.EntityRenderer;
import my_project.control.Keyboard;
import my_project.control.Renderer;
import my_project.model.Collider;
import my_project.model.Terrain;
import my_project.model.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private Texture texture;
    private double jumpSpeed;

    public Player(EntityRenderer eR, int invSize) {
        super(eR, invSize);
        x = 400;
        y = 0;
        width = 6;
        height = 25;
        speed = 100;
        jumpSpeed = 500;
        texture = new Texture("src/my_project/resources/player.png");
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 7, 23, 255));

        //drawHitbox(drawTool);
        //cage.draw(drawTool);
        texture.autoDraw(drawTool, x-2, y-4, 10);
    }

    @Override
    public void update(double dt) {


        if (Keyboard.isPressed(KeyEvent.VK_A)) {
          velocity.x = -speed;
        }
        if (Keyboard.isPressed(KeyEvent.VK_D)) {
            velocity.x = speed;
        }
        if ((Keyboard.isPressed(KeyEvent.VK_W) || Keyboard.isPressed(KeyEvent.VK_UP) || Keyboard.isPressed(KeyEvent.VK_SPACE)) && cage.getDownCollider().collides()) {
            System.out.println("jumping");
            velocity.y = -jumpSpeed;
        }
        velocity.y -= -1000*dt;
        //System.out.println("Player position: "+x+"|"+y);
        super.update(dt);

        Renderer.follow(new Vec2d(-x, -y), true);
        Renderer.loadChunks(x, y);
        Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y).highlight();
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }
    private void damageBlock(double x, double y){
        Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y).damage(100000000);
    }
}

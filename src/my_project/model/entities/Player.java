package my_project.model.entities;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.EntityRenderer;
import my_project.control.Keyboard;
import my_project.control.Renderer;
import my_project.model.Collider;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private double gravity = 0;
    private boolean onGround = false;
    private boolean goLeft = false;
    private boolean goRight = false;
    private boolean goDown = false;
    private double walkSpeed = 0;
    private double floor = 1000;

    public Player(EntityRenderer eR, int invSize) {
        super(eR, invSize);
        x = 400;
        y = 0;
        width = 40;
        height = 100;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 7, 23, 255));

        drawHitbox(drawTool);
        cage.draw(drawTool);
    }

    @Override
    public void update(double dt) {


        Renderer.follow(new Vec2d(-x, -y), true);
        Renderer.loadChunks(x, y);

        y += gravity*dt;
        gravity += 2300*dt;

        if(y > floor - height){
            onGround = true;
            gravity = 0;
            y = floor - height;
        }

        if(Keyboard.isPressed(KeyEvent.VK_A) && walkSpeed > -300){
            walkSpeed -= 800*dt;
        }
        if(Keyboard.isPressed(KeyEvent.VK_D) && walkSpeed < 300){
            walkSpeed += 800*dt;
        }
        if(!Keyboard.isPressed(KeyEvent.VK_A) && !Keyboard.isPressed(KeyEvent.VK_D)){
            if(walkSpeed > 0){
                walkSpeed -= 1600*dt;
                if (walkSpeed < 0) {
                    walkSpeed = 0;
                }
            }else if(walkSpeed < 0){
                walkSpeed += 1600*dt;
                if(walkSpeed > 0){
                    walkSpeed = 0;
                }
            }
        }
        x += walkSpeed*dt;

        if(goDown) {
            if(height > 70) {
                height -= 100*dt;
            }else{
                height = 70;
            }
        } else {
            if(height < 100) {
                height += 100*dt;
            }else{
                height = 100;
            }
        }
        super.update(dt);
    }

    @Override
    public void keyPressed(int key) {
        if(onGround && (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W)){
            gravity = -850;
            onGround = false;
        }
        if(key == KeyEvent.VK_A){
            goLeft = true;
        }
        if(key == KeyEvent.VK_D){
            goRight = true;
        }
        if(key == KeyEvent.VK_S){
            goDown = true;
        }
    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_A){
            goLeft = false;
        }
        if(key == KeyEvent.VK_D){
            goRight = false;
        }
        if(key == KeyEvent.VK_S){
            goDown = false;
        }
    }
}

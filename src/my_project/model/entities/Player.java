package my_project.model.entities;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.*;
import my_project.model.*;
import my_project.model.blocks.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Player extends Entity {

    private Texture texture;
    private double jumpSpeed;
    private int currentSlot;
    private double spawnProtection;

    public Player(EntityRenderer eR, int invSize) {
        super(eR, invSize);
        x = 400;
        y = 0;
        width = 6;
        height = 25;
        speed = 100;
        jumpSpeed = 500;
        hitpoints = 20;
        //texture = new Texture("src/my_project/resources/playerAnimations/0|0.png");
        spritesheet = new Spritesheet("src/my_project/resources/playerAnimations/",".png", 2, 4, 0.2, 0.2);
        currentSlot = 0;
        fallDamageFactor = 0.03;
        fallDamageHeight = 550;
        spawnProtection = 2;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 7, 23, 255));
        //drawHitbox(drawTool);
        //cage.draw(drawTool);
        //texture.autoDraw(drawTool, x-2, y-4, 12);
        spritesheet.autoDraw(drawTool, x-3, y-4, 12);
        Renderer.getUIRenderer().addInventoryToDraw(inventory);
    }

    @Override
    public void update(double dt) {
        if (spawnProtection > 0){
            spawnProtection -= dt;
        }
        changeSlot();
        if (Keyboard.isPressed(KeyEvent.VK_A)) {
          velocity.x = -speed;
        }
        if (Keyboard.isPressed(KeyEvent.VK_D)) {
            velocity.x = speed;

        }
        if ((Keyboard.isPressed(KeyEvent.VK_W) || Keyboard.isPressed(KeyEvent.VK_UP) || Keyboard.isPressed(KeyEvent.VK_SPACE)) && cage.getDownCollider().collides()) {
            //System.out.println("jumping");
            velocity.y = -jumpSpeed;
        }
        if (Mouse.isDown(1)) {
            damageBlock(Renderer.getRelativeMousePos().x, Renderer.getRelativeMousePos().y, -10*dt);
        }
        if (Mouse.isDown(3)) {
            System.out.println("use Item " + currentSlot);

            if (inventory.getItem(currentSlot) != null) {
                System.out.println("Item Exists");
                inventory.getItem(currentSlot).use(Renderer.getRelativeMousePos().x, Renderer.getRelativeMousePos().y, this);
            }
            //placeBlock(Renderer.getRelativeMousePos().x, Renderer.getRelativeMousePos().y, Brick.class);
        }
        velocity.y -= -1000*dt;
        //System.out.println("Player position: "+x+"|"+y);
        super.update(dt);
        if (velocity.x > 0) {
            spritesheet.setFrameY(2);
        } else if (velocity.x < 0) {
            spritesheet.setFrameY(3);
        }
        if (ProgramController.isAt(0, 1, velocity.x)) {
            if (spritesheet.getFrameY() == 2) {
                spritesheet.setFrameY(0);
            } else if (spritesheet.getFrameY() == 3) {
                spritesheet.setFrameY(1);
            }
        }
        spritesheet.updateX(dt);

        Renderer.follow(new Vec2d(-x, -y), true);
        Renderer.loadChunks(x, y);
        Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y).highlight();
        if (hitpoints <= 0) {
            Renderer.setScene(3);
        }
        /*
        System.out.println("position: " + x +"\t" + y);
        System.out.println("velocity: " + velocity);
         */
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }
    @Override
    public void damage(double damage) {
        if (spawnProtection < 0){
            super.damage(damage);
        }

    }
    private void damageBlock(double x, double y, double damage) {
        Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y).damage(damage);
    }
    private void placeBlock(double x, double y, Class<? extends Block> blockType) {
        if (Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y) instanceof Air) {
            int blockPosX = (int)Terrain.convertPositionToBlockGrid(x, y).x;
            int blockPosY = (int)Terrain.convertPositionToBlockGrid(x, y).y;
            Renderer.getBlockRenderer().getTerrain().setBlock(blockPosX, blockPosY, blockType);
        }
    }


    private void changeSlot(){
        if(Keyboard.isPressed(KeyEvent.VK_1)){
            currentSlot = 0;
        }else if(Keyboard.isPressed(KeyEvent.VK_2)){
            currentSlot = 1;
        }else if(Keyboard.isPressed(KeyEvent.VK_3)){
            currentSlot = 2;
        }else if(Keyboard.isPressed(KeyEvent.VK_4)){
            currentSlot = 3;
        }else if(Keyboard.isPressed(KeyEvent.VK_5)){
            currentSlot = 4;
        }else if(Keyboard.isPressed(KeyEvent.VK_6)){
            currentSlot = 5;
        }else if(Keyboard.isPressed(KeyEvent.VK_7)){
            currentSlot = 6;
        }else if(Keyboard.isPressed(KeyEvent.VK_8)){
            currentSlot = 7;
        }else if(Keyboard.isPressed(KeyEvent.VK_9)){
            currentSlot = 8;
        }
        inventory.highlitedSlot(currentSlot);
    }
}

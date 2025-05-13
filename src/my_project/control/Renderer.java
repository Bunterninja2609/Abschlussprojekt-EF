package my_project.control;

import KAGO_framework.Config;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Renderer extends InteractiveGraphicalObject {
    private static double SCALE = 0.3;
    private static Vec2d OFFSET = new Vec2d(0, 0);
    private Vec2d cameraMovement = new Vec2d(0,0);

    private boolean wIsDown = false;
    private boolean sIsDown = false;
    private boolean aIsDown = false;
    private boolean dIsDown = false;

    private BlockRenderer blockRenderer;
    private EntityRenderer entityRenderer;
    private UIRenderer uiRenderer;
    public Renderer(BlockRenderer blockRenderer, EntityRenderer entityRenderer, UIRenderer uiRenderer) {
        this.blockRenderer = blockRenderer;
        this.entityRenderer = entityRenderer;
        this.uiRenderer = new UIRenderer();
    }
    @Override
    public void draw(DrawTool drawTool) {
        entityRenderer.draw(drawTool);
        blockRenderer.draw(drawTool);
        uiRenderer.draw(drawTool);
    }
    @Override
    public void update(double dt){
        double speed = 100;
        entityRenderer.update(dt);
        /*
        blockRenderer.update(dt);
        */

        uiRenderer.update(dt);

        cameraMovement.set(0, 0);
        if (wIsDown){
            cameraMovement.y -= 1;
        }
        if (sIsDown){
            cameraMovement.y += 1;
        }
        if (aIsDown){
            cameraMovement.x -= 1;
        }
        if (dIsDown){
            cameraMovement.x += 1;
        }
        OFFSET.x -= dt * cameraMovement.x * speed;
        OFFSET.y -= dt * cameraMovement.y * speed;


    }

    public void keyPressed(int key) {

        if(key == KeyEvent.VK_W){
            wIsDown = true;
        }
        if(key == KeyEvent.VK_S){
            sIsDown = true;
        }


        if(key == KeyEvent.VK_A){
            aIsDown = true;
        }
        if(key == KeyEvent.VK_D){
           dIsDown = true;
        }
        if(key == KeyEvent.VK_UP){
            SCALE = SCALE * 1.1;
        }
        if(key == KeyEvent.VK_DOWN){
            SCALE = SCALE * 0.9;
        }

    }

    public void keyReleased(int key) {
        if(key == KeyEvent.VK_W){
            wIsDown = false;
        }
        if(key == KeyEvent.VK_S){
            sIsDown = false;
        }
        if(key == KeyEvent.VK_A){
            aIsDown = false;
        }
        if(key == KeyEvent.VK_D){
            dIsDown = false;
        }
    }

    public static Vec2d getOFFSET() {
        return OFFSET;
    }
    public static void setOFFSET(Vec2d offset) {
        OFFSET = offset;
    }
    public static double getSCALE() {
        return SCALE;
    }
    public static void setSCALE(double scale) {
        SCALE = scale;
    }
    public static Vec2d translate(Vec2d vec) {
        return new Vec2d((vec.x + OFFSET.x), (vec.y + OFFSET.y));
    }
    public static double translateX(double x) {
        return x + OFFSET.x;
    }
    public static double translateY(double y) {
        return y + OFFSET.y;
    }
    public static double scale(double scale){
        return SCALE*scale;
    }
    public static double translateAndScaleX(double x) {
        return (x + Renderer.getOFFSET().x)*Renderer.getSCALE();
    }
    public static double translateAndScaleY(double y) {
        return (y + Renderer.getOFFSET().y)*Renderer.getSCALE();
    }
}

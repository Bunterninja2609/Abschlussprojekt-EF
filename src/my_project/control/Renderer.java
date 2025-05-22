package my_project.control;

import my_project.Config;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Chunk;
import my_project.model.blocks.Block;
import my_project.control.UIRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Renderer extends InteractiveGraphicalObject {
    private static double SCALE = 0.3;
    private static Vec2d OFFSET = new Vec2d(0, 0);
    private static Vec2d OFFSET2 = new Vec2d(0, 0);
    private Vec2d cameraMovement = new Vec2d(0,0);

    private static final int RENDERDISTANCE = 5;

    private boolean wIsDown = false;
    private boolean sIsDown = false;
    private boolean aIsDown = false;
    private boolean dIsDown = false;

    private static BlockRenderer blockRenderer;
    private static EntityRenderer entityRenderer;
    private static UIRenderer uiRenderer;
    public Renderer(BlockRenderer blockRenderer, EntityRenderer entityRenderer, UIRenderer uiRenderer) {
        this.blockRenderer = blockRenderer;
        this.entityRenderer = entityRenderer;
        this.uiRenderer = uiRenderer;
    }
    @Override
    public void draw(DrawTool drawTool) {
        entityRenderer.draw(drawTool);
        blockRenderer.draw(drawTool);
        uiRenderer.draw(drawTool);
    }
    @Override
    public void update(double dt){
        uiRenderer.update(dt);

        entityRenderer.update(dt);

        blockRenderer.update(dt);

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
        return (x + Renderer.getOFFSET().x)*Renderer.getSCALE() + OFFSET2.x;
    }
    public static double translateAndScaleY(double y) {
        return (y + Renderer.getOFFSET().y)*Renderer.getSCALE() + OFFSET2.y;
    }
    public static void follow(Vec2d pos, boolean center) {
        OFFSET = pos;
        if(center) {
            OFFSET2.x = Config.WINDOW_WIDTH/2;
            OFFSET2.y = Config.WINDOW_HEIGHT/2;
        } else {
            OFFSET2.x = 0;
            OFFSET2.y = 0;
        }
    }
    public static void loadChunks(double x, double y){
        double rDIP = (Chunk.getSIZE().x * Block.getSIZE().x); //Chunk size in Pixels
        for (int i = -RENDERDISTANCE; i <= RENDERDISTANCE; i++) {
            for (int j = -RENDERDISTANCE; j <= RENDERDISTANCE; j++) {
                blockRenderer.getTerrain().getChunkByPosition(x + i * rDIP, y + j * rDIP).setLoaded(true);
            }
        }
    }
}

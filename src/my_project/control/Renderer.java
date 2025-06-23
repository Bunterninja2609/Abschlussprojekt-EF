package my_project.control;

import my_project.Config;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Background;
import my_project.model.Chunk;
import my_project.model.blocks.Block;
import my_project.control.UIRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Renderer extends InteractiveGraphicalObject {
    private static double SCALE = 5;
    private static Vec2d OFFSET = new Vec2d(0, 0);
    private static Vec2d OFFSET2 = new Vec2d(0, 0);
    private static int scene = 0;
    //TODO Mousehandler Klasse
    private static Vec2d mousePos = new Vec2d(0, 0);
    private static Vec2d relativeMousePos = new Vec2d(0, 0);
    private static boolean mousePressed = false;

    private static final int RENDERDISTANCE = 3;

    private static BlockRenderer blockRenderer;
    private static EntityRenderer entityRenderer;
    private static UIRenderer uiRenderer;

    private Background background;

    public Renderer(BlockRenderer blockRenderer, EntityRenderer entityRenderer, UIRenderer uiRenderer) {
        this.blockRenderer = blockRenderer;
        this.entityRenderer = entityRenderer;
        this.uiRenderer = uiRenderer;

        background = new Background();
    }
    @Override
    public void draw(DrawTool drawTool) {
        switch(scene){
            case 0://Menü
                break;
            case 1://Spiel
                background.draw(drawTool);
                entityRenderer.draw(drawTool);
                blockRenderer.draw(drawTool);
                break;
            case 2:
                background.draw(drawTool);
                entityRenderer.draw(drawTool);
                blockRenderer.draw(drawTool);
                break;
            default:
        }
        uiRenderer.draw(drawTool);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledCircle(mousePos.x, mousePos.y, 5);
        
        blockRenderer.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).highlight();
        drawTool.drawText(mousePos.x, mousePos.y,blockRenderer.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).getClass().getSimpleName());
    }
    @Override
    public void update(double dt){
        // System.out.println(scene);
        uiRenderer.update(dt);
        relativeMousePos.x = (mousePos.x - OFFSET2.x)/SCALE - OFFSET.x;
        relativeMousePos.y =(mousePos.y - OFFSET2.y)/SCALE - OFFSET.y;
        switch(scene) {
            case 0://Menü
                break;
            case 1://Spiel
                background.update(dt);
                entityRenderer.update(dt);
                blockRenderer.update(dt);
                if(Keyboard.isPressed(KeyEvent.VK_O)){
                    SCALE = 0.2;
                }else{
                    SCALE = 5;
                }
                break;
        }

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        //mousePos.x = e.getX();
        //mousePos.y = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }

    public static Vec2d getOFFSET() {
        return OFFSET;
    }
    public static int getSCENE() {
        return scene;
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

    public static Vec2d getMousePos() {
        return mousePos;
    }
    public static double getMousePosX() {
        return mousePos.x;
    }
    public static double getMousePosY() {
        return mousePos.y;
    }

    public static Vec2d getRelativeMousePos() {
        return relativeMousePos;
    }
    public double getRelativeMousePosX() {
        return relativeMousePos.x;
    }
    public double getRelativeMousePosY() {
        return relativeMousePos.y;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }
    public static void setScene(int scene) {
        Renderer.scene = scene;
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
    public static BlockRenderer getBlockRenderer() {
        return blockRenderer;
    }
    public static EntityRenderer getEntityRenderer() {
        return entityRenderer;
    }
    public static UIRenderer getUIRenderer() {
        return uiRenderer;
    }
    public static boolean raycast(Vec2d start, Vec2d end, double maxDistance, double rayDistance, Block ignore) {
        double distance = Math.abs(end.distance(start));
        double direction = Math.atan2(end.y - start.y, end.x - start.x);
        if (distance > maxDistance) {
            return false;
        }
        double posX = start.x;
        double posY = start.y;
        for (double i = 0; i < distance/rayDistance; i++) {
            double tempX = posX + Math.cos(direction) * i * rayDistance;
            double tempY = posY + Math.sin(direction) * i * rayDistance;
            if (!Renderer.getBlockRenderer().getTerrain().getBlockByPosition(tempX, tempY).getTransparent() && Renderer.getBlockRenderer().getTerrain().getBlockByPosition(tempX, tempY) != ignore) {
                return false;
            }
        }
        return true;
    }
}

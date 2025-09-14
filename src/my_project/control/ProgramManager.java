package my_project.control;

import my_project.Config;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Background;
import my_project.model.Chunk;
import my_project.model.blocks.Block;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ProgramManager extends InteractiveGraphicalObject {
    private static double SCALE = 5;
    private static Vec2d OFFSET = new Vec2d(0, 0);
    private static Vec2d OFFSET2 = new Vec2d(0, 0);
    private static int scene = 0;
    //TODO Mousehandler Klasse
    private static Vec2d mousePos = new Vec2d(0, 0);
    private static Vec2d relativeMousePos = new Vec2d(0, 0);
    private static boolean mousePressed = false;

    private static final int RENDERDISTANCE = 3;

    private static final double TICKS_PER_SECOND = 0.25;
    private static double tickTimer = 0;

    private static BlockManager blockManager;
    private static EntityManager entityManager;
    private static UserInterfaceManager userInterfaceManager;

    private Background background;

    public ProgramManager(BlockManager blockManager, EntityManager entityManager, UserInterfaceManager userInterfaceManager) {
        this.blockManager = blockManager;
        this.entityManager = entityManager;
        this.userInterfaceManager = userInterfaceManager;

        background = new Background();
    }
    @Override
    public void draw(DrawTool drawTool) {
        switch(scene){
            case 0://Menü
                break;
            case 1://Spiel
                background.draw(drawTool);
                entityManager.draw(drawTool);
                blockManager.draw(drawTool);
                break;
            case 2:
                background.draw(drawTool);
                entityManager.draw(drawTool);
                blockManager.draw(drawTool);
                break;
            default:
        }
        userInterfaceManager.draw(drawTool);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledCircle(mousePos.x, mousePos.y, 5);
        
        blockManager.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).highlight();
        drawTool.drawText(mousePos.x, mousePos.y, blockManager.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).getClass().getSimpleName());
        drawTool.drawText(mousePos.x, mousePos.y + 10, "X: " + blockManager.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).getX() + " | Y: " + blockManager.getTerrain().getBlockByPosition(relativeMousePos.x, relativeMousePos.y).getY());
    }

    @Override
    public void update(double dt){
        // System.out.println(scene);
        userInterfaceManager.update(dt);
        relativeMousePos.x = (mousePos.x - OFFSET2.x)/SCALE - OFFSET.x;
        relativeMousePos.y =(mousePos.y - OFFSET2.y)/SCALE - OFFSET.y;
        switch(scene) {
            case 0://Menü
                break;
            case 1://Spiel
                tickTimer += dt;
                while(tickTimer > TICKS_PER_SECOND) {
                    updateOnTick();
                    tickTimer -= TICKS_PER_SECOND;
                }
                background.update(dt);
                entityManager.update(dt);
                blockManager.update(dt);
                if(Keyboard.isPressed(KeyEvent.VK_O)){
                    SCALE = 0.2;
                }else{
                    SCALE = 5;
                }
                break;
        }
    }
    public void updateOnTick() {
        blockManager.updateOnTick();
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
    public static double getRelativeMousePosX() {
        return relativeMousePos.x;
    }
    public static double getRelativeMousePosY() {
        return relativeMousePos.y;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }
    public static void setScene(int scene) {
        ProgramManager.scene = scene;
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
        return (x + ProgramManager.getOFFSET().x)* ProgramManager.getSCALE() + OFFSET2.x;
    }

    public static double translateAndScaleY(double y) {
        return (y + ProgramManager.getOFFSET().y)* ProgramManager.getSCALE() + OFFSET2.y;
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
                blockManager.getTerrain().getChunkByPosition(x + i * rDIP, y + j * rDIP).setLoaded(true);
            }
        }
    }
    public static BlockManager getBlockRenderer() {
        return blockManager;
    }
    public static EntityManager getEntityRenderer() {
        return entityManager;
    }
    public static UserInterfaceManager getUIRenderer() {
        return userInterfaceManager;
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
            if (!ProgramManager.getBlockRenderer().getTerrain().getBlockByPosition(tempX, tempY).getTransparent() && ProgramManager.getBlockRenderer().getTerrain().getBlockByPosition(tempX, tempY) != ignore) {
                return false;
            }
        }
        return true;
    }
}

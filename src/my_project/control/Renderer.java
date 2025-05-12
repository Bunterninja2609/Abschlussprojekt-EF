package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Renderer extends InteractiveGraphicalObject {
    private static double SCALE = 1.0;
    private static Vec2d OFFSET = new Vec2d(0, 0);

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
        entityRenderer.update(dt);
        /*
        blockRenderer.update(dt);
        uiRenderer.update(dt);
        */

    }
    /*
    public void keyPressed(int key) {
        OFFSET.x += key;
    }
     */
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

}

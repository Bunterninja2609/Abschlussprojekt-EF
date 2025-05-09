package my_project.control;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Renderer extends GraphicalObject {
    public static double SCALE = 1.0;
    public static Vec2d OFFSET = new Vec2d(0, 0);

    private BlockRenderer blockRenderer;
    private EntityRenderer entityRenderer;
    public Renderer(BlockRenderer blockRenderer, EntityRenderer entityRenderer) {
        this.blockRenderer = blockRenderer;
        this.entityRenderer = entityRenderer;
    }
    @Override
    public void draw(DrawTool drawTool) {
        entityRenderer.draw(drawTool);
        blockRenderer.draw(drawTool);
    }
}

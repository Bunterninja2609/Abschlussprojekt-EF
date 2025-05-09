package my_project.control;

import com.sun.javafx.geom.Vec2d;

public class Renderer {
    public static double SCALE = 1.0;
    public static Vec2d OFFSET = new Vec2d(0, 0);

    private BlockRenderer blockRenderer;
    private EntityRenderer entityRenderer;
    public Renderer(BlockRenderer blockRenderer, EntityRenderer entityRenderer) {
        this.blockRenderer = blockRenderer;
        this.entityRenderer = entityRenderer;
    }

}

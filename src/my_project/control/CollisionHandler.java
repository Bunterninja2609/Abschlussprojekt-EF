package my_project.control;

import my_project.model.Collider;

public class CollisionHandler {
    private static Renderer renderer;
    private static EntityRenderer entityRenderer;
    private static BlockRenderer blockRenderer;

    public static void setRenderer(Renderer renderer) {
        CollisionHandler.renderer = renderer;
    }
    public static void setEntityRenderer(EntityRenderer entityRenderer) {
        CollisionHandler.entityRenderer = entityRenderer;
    }
    public static void setBlockRenderer(BlockRenderer blockRenderer) {
        CollisionHandler.blockRenderer = blockRenderer;
    }

    public static Renderer getRenderer() {
        return renderer;
    }
    public static EntityRenderer getEntityRenderer() {
        return entityRenderer;
    }
    public static BlockRenderer getBlockRenderer() {
        return blockRenderer;
    }

    public static boolean collidesWithBlock(Collider collider) {
        return true;
    }
}

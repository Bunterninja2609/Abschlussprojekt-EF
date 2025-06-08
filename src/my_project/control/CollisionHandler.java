package my_project.control;

import my_project.model.Collider;
import my_project.model.Terrain;
import my_project.model.blocks.Block;

public class CollisionHandler {
    private static Renderer renderer;
    private static EntityRenderer entityRenderer;
    private static BlockRenderer blockRenderer;

    public static void setRenderer(Renderer renderer, boolean autoAddRest) {
        CollisionHandler.renderer = renderer;
        if (autoAddRest) {
            blockRenderer = Renderer.getBlockRenderer();
            entityRenderer = Renderer.getEntityRenderer();

        }
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
        collider.getCage().updatePosition();
        if (blockRenderer == null) return false;
        //System.out.println("checking collidesWithBlock");
        int widthInBlocks = (int)(collider.getWidth()/Block.getSIZE().x);
        int heightInBlocks = (int)(collider.getHeight()/Block.getSIZE().y);
        //System.out.println("widthInBlocks: " + widthInBlocks);
        //System.out.println("heightInBlocks: " + heightInBlocks);
        for (int x = 0; x <= widthInBlocks; x++) {
            for (int y = 0; y <= heightInBlocks; y++) {
                Block block = blockRenderer.getTerrain().getBlockByPosition(collider.getX() + x * Block.getSIZE().x, collider.getY() + y * Block.getSIZE().y);
                boolean collides = !block.getTransparent();
                //System.out.println("collides at " +Terrain.convertPositionToBlockGrid(block.getX(), block.getY()).x+"|"+ Terrain.convertPositionToBlockGrid(block.getX(), block.getY()).y+": "+ collides);
                //System.out.println(block.getClass().getSimpleName());
                if (collides){
                    //System.out.println("collidesWithBlock");
                    collider.setCollides(true);
                    return true;
                }
            }
        }
        //collider.setCollides(false);
        return false;
    }
}

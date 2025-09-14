package my_project.control;

import my_project.model.Collider;
import my_project.model.blocks.Block;

public class CollisionHandler {
    private static ProgramManager programManager;
    private static EntityManager entityManager;
    private static BlockManager blockManager;

    public static void setRenderer(ProgramManager programManager, boolean autoAddRest) {
        CollisionHandler.programManager = programManager;
        if (autoAddRest) {
            blockManager = ProgramManager.getBlockRenderer();
            entityManager = ProgramManager.getEntityRenderer();

        }
    }
    public static void setEntityManager(EntityManager entityManager) {
        CollisionHandler.entityManager = entityManager;
    }
    public static void setBlockManager(BlockManager blockManager) {
        CollisionHandler.blockManager = blockManager;
    }

    public static ProgramManager getProgramManager() {
        return programManager;
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }
    public static BlockManager getBlockManager() {
        return blockManager;
    }

    public static boolean collidesWithBlock(Collider collider) {
        collider.getCage().updatePosition();
        if (blockManager == null) return false;
        //System.out.println("checking collidesWithBlock");
        int widthInBlocks = (int)(collider.getWidth()/Block.getSIZE().x) + 1;
        int heightInBlocks = (int)(collider.getHeight()/Block.getSIZE().y) + 1;
        //System.out.println("widthInBlocks: " + widthInBlocks);
        //System.out.println("heightInBlocks: " + heightInBlocks);
        for (int x = 0; x <= widthInBlocks; x++) {
            for (int y = 0; y <= heightInBlocks; y++) {
                Block block = blockManager.getTerrain().getBlockByPosition(collider.getX() + x * Block.getSIZE().x, collider.getY() + y * Block.getSIZE().y);
                boolean collides = collider.collidesWith(block);
                boolean isSolid = (!block.getTransparent());
                //System.out.println("collides at " +Terrain.convertPositionToBlockGrid(block.getX(), block.getY()).x+"|"+ Terrain.convertPositionToBlockGrid(block.getX(), block.getY()).y+": "+ collides);
                //System.out.println(block.getClass().getSimpleName());
                if (collides && isSolid) {
                    //System.out.println("collidesWithBlock");
                    collider.setCollides(true);
                    return true;
                } else if (collides && !isSolid) {
                    block.setColliderInside(true);
                }
            }
        }
        //collider.setCollides(false);
        return false;
    }
}

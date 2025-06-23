package my_project.model.items;

import my_project.control.Renderer;
import my_project.model.Terrain;
import my_project.model.blocks.Air;
import my_project.model.blocks.Block;
import my_project.model.entities.Entity;

public class BlockItem extends Item {
    protected Class<? extends Block> block;
    public BlockItem(int amount) {
        super(amount);
    }

    @Override
    public void use(double x, double y, Entity user) {
        if (Renderer.getBlockRenderer().getTerrain().getBlockByPosition(x, y) instanceof Air) {
            int blockPosX = (int)Terrain.convertPositionToBlockGrid(x, y).x;
            int blockPosY = (int)Terrain.convertPositionToBlockGrid(x, y).y;
            Renderer.getBlockRenderer().getTerrain().setBlock(blockPosX, blockPosY, block);
            amount--;
        }
    }
}

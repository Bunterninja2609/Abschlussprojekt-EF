package my_project.model;

import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;

public class BlockSpace {
    public Vec2d gridPosition;
    public Vec2d position;
    Block block;
    public BlockSpace(Vec2d gridPosition, Block block) {
        this.gridPosition = gridPosition;
        this.block = block;
        this.block.setGridPosition(gridPosition);
    }

    public Block getBlock() {
        return block;
    }
    public void setBlock(Block block) {
        this.block = block;
        block.setGridPosition(gridPosition);
    }
    public Vec2d getGridPosition() {
        return gridPosition;
    }
    public Vec2d getPosition() {
        return position;
    }
}

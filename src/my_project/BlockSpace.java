package my_project;

import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;

public class BlockSpace {
    public Vec2d gridPosition;
    public Vec2d position;
    Block block;
    public BlockSpace(Vec2d gridPosition, Block block) {
        this.gridPosition = gridPosition;
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
    public void setBlock(Block block) {
        this.block = block;
        block.setX(gridPosition.x);
        block.setY(gridPosition.y);
    }
    public Vec2d getGridPosition() {
        return gridPosition;
    }
    public Vec2d getPosition() {
        return position;
    }
}

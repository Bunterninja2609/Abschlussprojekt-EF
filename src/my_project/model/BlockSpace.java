package my_project.model;

import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;
import my_project.model.blocks.BlockAir;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
        if (block != null) {
            if (block.getBlockSpace() != null) {
                block.getBlockSpace().setBlock(null);
            }
            this.block.setBlockSpace(null);
            this.block = block;
            block.setBlockSpace(this);
        } else {
            this.setNewBlock(BlockAir.class);
        }
    }
    public void setNewBlock(Class<? extends Block> blockType){
        try {
            Constructor<? extends Block> constructor = blockType.getDeclaredConstructor(Vec2d.class);
            constructor.setAccessible(true);
            Block block = constructor.newInstance(gridPosition);

            setBlock(block);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public Vec2d getGridPosition() {
        return gridPosition;
    }
    public Vec2d getPosition() {
        return position;
    }
    public double getX(){
        return gridPosition.x;
    }
    public double getY(){
        return gridPosition.y;
    }
}

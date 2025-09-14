package my_project.model.blocks;

import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramManager;

public abstract class GravityBlock extends Block {
    public GravityBlock(Vec2d gridPosition, boolean isTransparent) {
        super(gridPosition, isTransparent);
    }
    public void update(double dt) {
        super.update(dt);

    }
    @Override
    public void updateOnTick(){
        super.updateOnTick();
        if(getAdjacentBlock(0, 1) instanceof BlockAir || getAdjacentBlock(0, 1) instanceof BlockLiquid) {
            System.out.println("y: "+ this.gridPosition.y);
            move(0, 1, "swap");
        }
    }
}

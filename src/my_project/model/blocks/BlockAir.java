package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class BlockAir extends Block {
    public BlockAir(Vec2d gridPosition) {
        super(gridPosition, true);

    }

    public void draw(DrawTool drawTool) {

    }

    @Override
    public void update(double dt) {

    }
}

package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Water extends Block {
    public Water(Vec2d gridPosition) {
        super(gridPosition);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(0,130,200, 250);
        drawHitbox(drawTool);
    }
}

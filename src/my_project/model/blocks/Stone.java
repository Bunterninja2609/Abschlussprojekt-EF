package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Stone extends Block {
    public Stone(Vec2d gridPosition) {
        super(gridPosition);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,128,128, 255);
        drawHitbox(drawTool);
    }
}

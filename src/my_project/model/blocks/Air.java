package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Air extends Block {
    public Air(Vec2d gridPosition) {
        super(gridPosition);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(0,255,255, 128);
        drawHitbox(drawTool);
    }
}

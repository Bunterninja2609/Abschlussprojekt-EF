package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Iron extends Block {
    public Iron(Vec2d gridPosition) {super(gridPosition);}
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,100,80, 255);
        drawHitbox(drawTool);
    }
}

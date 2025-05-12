package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Dirt extends Block {
    public Dirt(Vec2d gridPosition) {
        super(gridPosition);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,64,0, 255);
        drawHitbox(drawTool);
    }

}

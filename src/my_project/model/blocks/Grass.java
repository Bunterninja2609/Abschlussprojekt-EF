package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Grass extends Block {
    public Grass(Vec2d gridPosition) {
        super(gridPosition);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(64,255,32, 255);
        drawHitbox(drawTool);
    }

}

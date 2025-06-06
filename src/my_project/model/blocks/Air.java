package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;

public class Air extends Block {
    public Air(Vec2d gridPosition) {
        super(gridPosition);
    }

    public void draw(DrawTool drawTool) {
        /*drawTool.setCurrentColor(new Color(0, 0, 0));
        drawHitbox(drawTool);
        drawTool.setCurrentColor(new Color(89, 208, 255, (int)alpha ));
        drawHitbox(drawTool); */
    }

    @Override
    public void update(double dt) {

    }
}

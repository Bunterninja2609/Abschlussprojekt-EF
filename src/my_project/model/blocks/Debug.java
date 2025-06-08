package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;

public class Debug extends Block {
    double value;
    public Debug(Vec2d gridPosition, double value) {
        super(gridPosition, false);
        this.value = value;
    }
    public void draw(DrawTool drawTool) {
        double v = (value*255);

        drawTool.setCurrentColor(0, (int)ProgramController.clamp(0, 255, v),0, 255);

        drawTexture(drawTool);

    }
}

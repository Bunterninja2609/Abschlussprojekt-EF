package my_project.control;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public class UIRenderer {

    private int fps = 0;

    public UIRenderer() {}

    public void update(double dt) {
        fps = (int) Math.floor(1/dt);
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawText(10,10, String.valueOf(fps));
    }


}

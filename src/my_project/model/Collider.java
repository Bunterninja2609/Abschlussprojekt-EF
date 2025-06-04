package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Collider extends GraphicalObject {

    private double xOffset,yOffset;

    public Collider(double xOffset, double yOffset, double width, double height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = 10;
        this.height = 10;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 0, 0));
        drawTool.drawFilledRectangle(x,y,width,height);
    }

    @Override
    public void update(double dt) {

    }

    public void setPos(double x,double y) {
        this.x = x + xOffset;
        this.y = y + yOffset;
    }
}

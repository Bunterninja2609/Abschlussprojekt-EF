package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.Renderer;

public class Texture extends GraphicalObject {
    public Texture(String texture) {
        setNewImage(texture);
    }
    public void draw(DrawTool drawTool, double x, double y, double scale) {
        drawTool.drawTransformedImage(getMyImage(), x, y, 0, scale);
    }
    public void autoDraw(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(getMyImage(), Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y-(width/getMyImage().getWidth())*getMyImage().getHeight()), 0, Renderer.scale(width/getMyImage().getWidth()));
    }

}

package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.Renderer;


public class Button {
    private Vec2d position;
    private int widthButton;
    private int heightButton;
    private boolean isPressed;
    public Button(double x, double y, int width, int height){
        position = new Vec2d(x, y);
        widthButton = width;
        heightButton = height;
        isPressed = false;
    }
    public void draw(DrawTool drawTool) {
        drawTool.drawFilledRectangle(position.x, position.y, widthButton, heightButton);
        if(isPressed) {
            drawTool.drawRectangle(100,100,500,500);
        }
    }
    public void update(double dt) {
        if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + heightButton && Renderer.isMousePressed()){
            isPressed = true;
        }else {
            isPressed = false;
        }
        if(isPressed) {
            Renderer.setScene(1);
        }
    }
}


package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.Renderer;

import java.awt.*;


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
        drawTool.setCurrentColor(new Color(117, 117, 117, 255));
        drawTool.drawFilledRectangle(position.x, position.y, widthButton, heightButton);
        drawTool.setCurrentColor(new Color(147, 232, 143, 255));
        drawTool.formatText("monospaced",2,25);
        drawTool.drawText(position.x + 63,position.y + 28,"Start");
        if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + heightButton) {
            drawTool.setCurrentColor(new Color(80, 80, 80, 255));
            drawTool.drawFilledRectangle(position.x, position.y, widthButton, heightButton);
            drawTool.setCurrentColor(new Color(255, 255, 255, 255));
            drawTool.drawRectangle(position.x, position.y, widthButton,heightButton);
            drawTool.drawText(position.x + 63,position.y + 28,"Start");

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


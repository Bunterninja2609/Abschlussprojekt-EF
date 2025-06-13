package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.Renderer;

import java.awt.*;


public class RespawnButton {
    private Vec2d position;
    private boolean isPressen;
    private int widthRespawnButton;
    private int heightRespawnButton;

    public RespawnButton(double x, double y, int width, int height) {
        position = new Vec2d(x, y);
        isPressen = false;
        heightRespawnButton = height;
        widthRespawnButton = width;
    }
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.drawFilledRectangle(position.x, position.y, widthRespawnButton, heightRespawnButton);
        drawTool.setCurrentColor(Color.CYAN);
        drawTool.formatText("monospaced",2,35);
        drawTool.drawText(position.x + 50, position.y + 20, "Respawn you dumb Fuck");
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthRespawnButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + heightRespawnButton) {
                    drawTool.setCurrentColor(Color.MAGENTA);
                    drawTool.drawFilledRectangle(position.x, position.y, widthRespawnButton, heightRespawnButton);
                    drawTool.setCurrentColor(Color.red);
                    drawTool.drawRectangle(position.x, position.y, widthRespawnButton,heightRespawnButton);
                    drawTool.drawText(position.x + 63,position.y + 28,"Start");
            }
    }
    public void update(double dt) {
        if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthRespawnButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + heightRespawnButton && Renderer.isMousePressed()){
            isPressed = true;
        }else {
            isPressed = false;
        }
        if(isPressed) {
            Renderer.setScene(1);
        }
    }

}



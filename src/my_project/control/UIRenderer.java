package my_project.control;

import KAGO_framework.view.DrawTool;
import my_project.uI.StartMenu;

import java.awt.*;

public class UIRenderer {

    private int fps = 0;
    private StartMenu startMenu;

    public UIRenderer() {
        startMenu = new StartMenu();
    }

    public void update(double dt) {
        fps = (int) Math.floor(1/dt);
        switch(Renderer.getSCENE()){
            case 0:
                startMenu.update(dt);
                break;
            case 1:
                break;
            default:
                break;
        }
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawText(10,10,"FPS: " + fps);
        switch(Renderer.getSCENE()){
            case 0:
                startMenu.draw(drawTool);
                break;
            case 1:
                break;
            default:
                break;
        }
    }


}

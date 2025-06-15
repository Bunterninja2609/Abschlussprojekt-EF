package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

import java.awt.*;

public class Background extends GraphicalObject {

    private double alpha = 255;
    private double time = 0;

    public Background(){

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);

        drawTool.setCurrentColor(new Color(85, 167, 255, (int) alpha));
        drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);
    }

    @Override
    public void update(double dt) {
        double daytime = 20;
        time += dt;
        if (time > daytime && time <= 2*daytime) {
            if (alpha > 0){
                alpha -= 255*dt;
            }
            if (alpha < 0){
                alpha = 0;
            }
        } else if (time > daytime*2) {
            if (alpha < 255){
                alpha += 255*dt;
            }
            if (alpha >= 255){
                alpha = 255;
                time = time%(daytime*2);
            }
        }
    }
}

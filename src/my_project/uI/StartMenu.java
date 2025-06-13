package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;

import java.awt.*;


public class StartMenu {
    private Button startButton = new Button(Config.WINDOW_HEIGHT/2-122,Config.WINDOW_WIDTH/2-20, 200, 40,"src/my_project/resources/Startbutton.png");


    public void draw(DrawTool drawTool){

        drawTool.setCurrentColor(new Color(209, 209, 209, 150));
        drawTool.drawFilledRectangle(-1,-1,600,600);
        startButton.draw(drawTool);
    }
    public void update(double dt){
        startButton.update(dt);
    }
}

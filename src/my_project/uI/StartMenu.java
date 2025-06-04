package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;


public class StartMenu {
    private Button startButton = new Button(Config.WINDOW_HEIGHT/2-50,Config.WINDOW_WIDTH/2-25, 100, 50);


    public void draw(DrawTool drawTool){
        startButton.draw(drawTool);


    }
    public void update(double dt){
        startButton.update(dt);
    }


}

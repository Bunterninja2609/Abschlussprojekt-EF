package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.model.Texture;
import java.awt.*;


public class Menu {
    private Button startButton01 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+150, 50, 40,"src/my_project/resources/Settingsbutton.png", "src/my_project/resources/Settingsbutton_down.png" , 1);
    private Button startButton02 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+300, 50, 40,"src/my_project/resources/Settingsbutton.png", "src/my_project/resources/Settingsbutton_down.png" , 6);
    public Menu() {

    }
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(254, 254, 254, 255));
        drawTool.formatText("monospaced",3,30);
        drawTool.drawText( Config.WINDOW_WIDTH/2-115,Config.WINDOW_HEIGHT/2+300,"Exit Game");

        drawTool.setCurrentColor(new Color(254, 254, 254, 255));
        drawTool.formatText("monospaced",3,30);
        drawTool.drawText(Config.WINDOW_WIDTH/2-145,Config.WINDOW_HEIGHT/2+150,"Continue Game");
        startButton01.draw(drawTool);
        startButton02.draw(drawTool);


    }
    public void update(double dt){
        startButton01.update(dt);
        startButton02.update(dt);

    }
}

package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.model.Texture;
import java.awt.*;


public class StartMenu {
    private Texture texture;
    private Button startButton01 = new Button(Config.WINDOW_WIDTH/2-235,Config.WINDOW_HEIGHT/2+50, 400, "src/my_project/resources/Startbutton.png", "src/my_project/resources/Startbutton_down.png" , 1);
    private Button startButton02 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+300, 50, "src/my_project/resources/Creditsbutton.png", "src/my_project/resources/Creditsbutton_down.png" ,2);

    public StartMenu( String texturePath) {
        texture = new Texture(texturePath);

    }

    public void draw(DrawTool drawTool){


        texture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        drawTool.setCurrentColor(new Color(0, 0, 0, 255));
        drawTool.formatText("monospaced",3,60);
        drawTool.drawText((Config.WINDOW_WIDTH/2)-195,Config.WINDOW_HEIGHT/3,"Terrarium");
        startButton01.draw(drawTool);
        startButton02.draw(drawTool);

    }
    public void update(double dt){
        startButton01.update(dt);
        startButton02.update(dt);
    }
}

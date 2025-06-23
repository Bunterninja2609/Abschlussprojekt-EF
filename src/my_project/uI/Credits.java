package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.model.Texture;
import java.awt.*;


public class Credits {
    private Texture texture;

    private Button startButton01 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+240, 50, 40,"src/my_project/resources/Creditsbutton.png", "src/my_project/resources/Creditsbutton_down.png" ,3);

    public Credits( String texturePath) {
        texture = new Texture(texturePath);

    }

    public void draw(DrawTool drawTool){


        texture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        drawTool.setCurrentColor(new Color(0, 0, 0, 255));
        drawTool.formatText("monospaced",3,60);
        drawTool.drawText((Config.WINDOW_WIDTH/2)-153,Config.WINDOW_HEIGHT/3,"Credits");
        startButton01.draw(drawTool);

    }
    public void update(double dt){
        startButton01.update(dt);

    }
}
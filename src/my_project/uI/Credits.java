package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.model.Texture;
import java.awt.*;


public class Credits {
    private Texture texture;

    private Button startButton01 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+240, 50,"src/my_project/resources/Creditsbutton.png", "src/my_project/resources/Creditsbutton_down.png" ,3);

    public Credits( String texturePath) {
        texture = new Texture(texturePath);

    }

    public void draw(DrawTool drawTool){


        texture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        drawTool.setCurrentColor(new Color(0, 0, 0, 255));
        drawTool.formatText("monospaced",3,60);
        drawTool.drawText((Config.WINDOW_WIDTH/2)-153,Config.WINDOW_HEIGHT/3+50,"Credits");
        drawTool.formatText("monospaced",3,25);
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+100,"Mitarbeiter:");
        drawTool.formatText("monospaced",3,20);
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+150,"Joshua – Programmierung,-");
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+200," Bilddesign");
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+250,"Tom – Programmierung");
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+300,"Daniel – Programmierung ");
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+350,"Mischa – Programmierung");
        drawTool.drawText((Config.WINDOW_WIDTH/2)-300,Config.WINDOW_HEIGHT/3+400,"Sam – Programmierung ");
        drawTool.formatText("monospaced",3,25);
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+100,"Verwendete Tools:");
        drawTool.formatText("monospaced",3,20);
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+150,"IntelliJ IDEA");
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+200,"GitHub");
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+250,"Pixaki 6");
        drawTool.formatText("monospaced",3,25);
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+300,"Grafiken:");
        drawTool.formatText("monospaced",3,20);
        drawTool.drawText((Config.WINDOW_WIDTH/2)+60,Config.WINDOW_HEIGHT/3+350,"Alle Grafiken wurden selbst gezeichnet");

        startButton01.draw(drawTool);

    }
    public void update(double dt){
        startButton01.update(dt);

    }
}
package my_project.control;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Inventory;
import my_project.uI.Credits;
import my_project.uI.EndScreen;
import my_project.uI.StartMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class UIRenderer {

    private int fps = 0;
    private StartMenu startMenu;
    private EndScreen endScreen;
    private Credits credits;
    private ArrayList<Inventory> inventoriesToDraw;

    public UIRenderer() {
        startMenu = new StartMenu("src/my_project/resources/Background.jpg");
        credits = new Credits("src/my_project/resources/Background.jpg");
        inventoriesToDraw = new ArrayList<>();
        endScreen = new EndScreen(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT,"src/my_project/resources/Background.jpg" );
    }

    public void update(double dt) {
        fps = (int) Math.floor(1/dt);
        switch(Renderer.getSCENE()){
            case 0:
                startMenu.update(dt);
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                endScreen.update(dt);
                break;
            case 4:
                credits.update(dt);
                break;
            default:
                break;
        }
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.BLACK);
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawText(10,10,"FPS: " + fps);
        switch(Renderer.getSCENE()){
            case 0:
                startMenu.draw(drawTool);
                break;
            case 1:
                for(Inventory inv : inventoriesToDraw){
                    inv.draw(drawTool);
                }
                inventoriesToDraw.clear();
                break;
            case 2:
                break;
            case 3:
                endScreen.draw(drawTool);
                break;
            case 4:
                credits.draw(drawTool);
                break;
            default:
                break;
        }
    }
    public void addInventoryToDraw(Inventory inventory){
        inventoriesToDraw.add(inventory);
    }
}

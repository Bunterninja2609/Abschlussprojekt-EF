package my_project.control;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Inventory;
import my_project.uI.Credits;
//import my_project.uI.EndScreen;
import my_project.uI.StartMenu;
import my_project.uI.Overlay;
import my_project.uI.Menu;

import java.awt.*;
import java.util.ArrayList;

public class UserInterfaceManager {

    private int fps = 0;
    private StartMenu startMenu;
    //private EndScreen endScreen;
    private Credits credits;
    private Overlay overlay;
    private Menu menu;
    private ArrayList<Inventory> inventoriesToDraw;

    public UserInterfaceManager() {
        startMenu = new StartMenu("src/my_project/resources/Background.png");
        credits = new Credits("src/my_project/resources/Background.png");
        inventoriesToDraw = new ArrayList<>();
        //endScreen = new EndScreen(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT,"src/my_project/resources/Background.png" );
        overlay = new Overlay();
        menu = new Menu();
    }

    public void update(double dt) {
        fps = (int) Math.floor(1/dt);
        switch(ProgramManager.getSCENE()){
            case 0:
                startMenu.update(dt);
                break;
            case 1:
                overlay.update(dt);
                break;
            case 2:
                menu.update(dt);
                break;
            case 3:
                //endScreen.update(dt);
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
        switch(ProgramManager.getSCENE()){
            case 0:
                startMenu.draw(drawTool);
                break;
            case 1:
                for(Inventory inv : inventoriesToDraw){
                    inv.draw(drawTool);
                }
                inventoriesToDraw.clear();
                overlay.draw(drawTool);
                break;
            case 2:
                menu.draw(drawTool);
                break;
            case 3:
                //endScreen.draw(drawTool);
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

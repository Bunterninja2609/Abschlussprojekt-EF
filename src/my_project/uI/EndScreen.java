package my_project.uI;
import my_project.Config;

import KAGO_framework.model.GraphicalObject;
import my_project.model.RespawnButton;
import my_project.model.MenuButton;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;


public class EndScreen extends GraphicalObject {

    protected int screenWidth;
    protected int screenHeight;
    protected boolean visible;

    protected RespawnButton respawnButton;
    protected MenuButton menuButton;

    public EndScreen(int screenWidth, int screenHeight, Runnable onRespawn, Runnable onMenu) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.visible = false;

        int buttonWidth = 200;
        int buttonHeight = 50;
        int centerX = screenWidth/2 - buttonWidth/2;

        respawnButton = new RespawnButton(centerX, screenHeight / 2, buttonWidth, buttonHeight, onRespawn);
        menuButton = new MenuButton(centerX, screenHeight / 2 + 70, buttonWidth, buttonHeight, onMenu);
    }

    public void setVisible(boolean visible) {
        //Muss noch ergänzt werden, wenn der spieler stirbt blablabla
    }

    @Override
    public void draw(DrawTool drawTool){
        if (visible == true) {

            //drawTool.setCurrentColor(0, 0, 0, 180, 50);
            drawTool.drawFilledRectangle(0, 0, screenWidth, screenHeight);

            drawTool.setCurrentColor(Color.RED);
            //drawTool.setCurrentFont(new Font("SansSerif", Font.BOLD, 60));
            drawTool.drawText(screenWidth / 2 - 160, screenHeight / 2 - 60, "Game Over");

            respawnButton.draw(drawTool);
            menuButton.draw(drawTool);
        }
    }


    //keine ahnung wie ich mouse event mache
    public void handleClick(MouseEvent mouseEvent) {
        if (visible == true) {

            respawnButton.handleClick(mouseEvent);
            menuButton.handleClick(mouseEvent);
        }
    }
}

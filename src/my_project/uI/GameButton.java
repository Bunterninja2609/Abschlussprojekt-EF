// === GameButton.java ===
package my_project.uI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramManager;

import java.awt.*;



public abstract class GameButton extends GraphicalObject {

    protected int x, y, width, height;
    protected String label;
    protected boolean onClick;
    protected int buttonType;

    public GameButton(int x, int y, int width, int height, String label, boolean onClick, int buttonType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.onClick = false;
        this.buttonType = buttonType;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledRectangle(x, y, width, height);

        drawTool.setCurrentColor(Color.BLACK);
        drawTool.formatText("monospaced", 3, 18);
        drawTool.drawText(x + 20, y + 30, label); //Button position
    }

    public void update(double dt) {

        if (ProgramManager.getMousePos().x > x && ProgramManager.getMousePos().x < x + width && ProgramManager.getMousePos().y > y && ProgramManager.getMousePos().y < y + height && ProgramManager.isMousePressed()) {
            onClick = true;
        } else {
            onClick = false;
        }


        if (onClick == true) {
            if (buttonType == 1) {
                ProgramManager.setScene(1);
            }
            if (buttonType == 2) {
                ProgramManager.setScene(0);
            }

        }
    }
}



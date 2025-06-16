// === GameButton.java ===
package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;



public abstract class GameButton extends GraphicalObject {

    protected int x, y, width, height;
    protected String label;
    //protected Runnable onClick;

    public GameButton(int x, int y, int width, int height, String label, Runnable onClick) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        //this.onClick = onClick;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledRectangle(x, y, width, height);

        drawTool.setCurrentColor(Color.BLACK);
        //drawTool.setCurrentFont(new Font("SansSerif", Font.BOLD, 20));//Keine Ahnung wie man Font macht
        drawTool.drawText( x + 20, y + 30, label); //Button position
    }

    public void handleClick(MouseEvent mouseEvent) {
        int mx = mouseEvent.getX();
        int my = mouseEvent.getY();
        if (mx >= x && mx <= x + width && my >= y && my <= y + height) {
            //onClick.run();//keine Ahnung wie man szene wechselt
        }
    }
}

package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;

public class Air extends Block {

    private double alpha = 255;
    private double time = 0;

    public Air(Vec2d gridPosition) {
        super(gridPosition);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0, 0, 0));
        drawHitbox(drawTool);
        drawTool.setCurrentColor(new Color(89, 208, 255, (int)alpha ));
        drawHitbox(drawTool);
    }

    @Override
    public void update(double dt) {
        time += dt;
        if (time > 300) {
            if (alpha > 0){
                alpha -= 255*dt;
            }
            if (alpha < 0){
                alpha = 0;
            }
        } else if (time > 600) {
            if (alpha < 255){
                alpha += 255*dt;
            }
            if (alpha >= 255){
                alpha = 255;
                time = 0;
            }
        }
    }
}

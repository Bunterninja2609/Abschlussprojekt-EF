package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockTextures;

public class Brick extends Block {
    public Brick(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 25;
        texture = BlockTextures.getTexture("brick");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,64,0, 255);
        drawTexture(drawTool);
    }
}

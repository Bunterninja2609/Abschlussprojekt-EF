package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockTextures;

public class Grass extends Block {
    public Grass(Vec2d gridPosition) {
        super(gridPosition, false);
        texture = BlockTextures.getTexture("grass");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(64,255,32, 255);
        drawTexture(drawTool);
    }

}

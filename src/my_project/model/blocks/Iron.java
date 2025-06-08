package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockTextures;

public class Iron extends Block {
    public Iron(Vec2d gridPosition) {
        super(gridPosition, false);
        texture = BlockTextures.getTexture("iron");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,100,80, 255);
        drawTexture(drawTool);
    }
}

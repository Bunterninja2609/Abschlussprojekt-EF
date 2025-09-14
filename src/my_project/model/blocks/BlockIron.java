package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.textureContainers.BlockTextures;

public class BlockIron extends Block {
    public BlockIron(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 20;
        texture = BlockTextures.getTexture("iron");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,100,80, 255);
        drawTexture(drawTool);
    }
}

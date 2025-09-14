package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.textureContainers.BlockTextures;
import my_project.model.items.SandItem;

public class BlockSand extends GravityBlock{
    public BlockSand(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 10;
        drop = new SandItem(1);
        texture = BlockTextures.getTexture("sand");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,64,0, 255);
        drawTexture(drawTool);
    }
}

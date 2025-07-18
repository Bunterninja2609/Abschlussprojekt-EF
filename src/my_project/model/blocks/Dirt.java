package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockTextures;
import my_project.model.items.DirtItem;

public class Dirt extends Block {
    public Dirt(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 10;
        drop = new DirtItem(1);
        texture = BlockTextures.getTexture("dirt");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,64,0, 255);
        drawTexture(drawTool);
    }
}
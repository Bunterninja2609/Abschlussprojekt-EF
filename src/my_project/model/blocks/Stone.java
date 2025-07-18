package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockTextures;
import my_project.model.items.DirtItem;
import my_project.model.items.StoneItem;

public class Stone extends Block {
    public Stone(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 18;
        drop = new StoneItem(1);
        texture = BlockTextures.getTexture("stone");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,128,128, 255);
        drawTexture(drawTool);
    }
}

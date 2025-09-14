package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.items.SandItem;
import my_project.model.textureContainers.BlockTextures;

public class BlockLava extends BlockLiquid{
    public BlockLava(Vec2d gridPosition) {
        super(gridPosition, 0.9);
        hitpoints = 10;
        drop = new SandItem(0);
        texture = BlockTextures.getTexture("sand");
    }
    public BlockLava(Vec2d gridPosition, double volume) {
        super(gridPosition, volume);
        hitpoints = 10;
        drop = new SandItem(0);
        texture = BlockTextures.getTexture("sand");
    }
}

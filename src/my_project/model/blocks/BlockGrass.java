package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramManager;
import my_project.model.textureContainers.BlockTextures;
import my_project.model.items.DirtItem;

public class BlockGrass extends Block {
    public BlockGrass(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 12;
        drop = new DirtItem(2);
        texture = BlockTextures.getTexture("grass");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(64,255,32, 255);
        drawTexture(drawTool);
    }
    public void update(double dt) {
        super.update(dt);
        if (!ProgramManager.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x, (int)gridPosition.y-1).isTransparent){
            ProgramManager.getBlockRenderer().getTerrain().setBlock((int)gridPosition.x, (int)gridPosition.y, BlockDirt.class);
        }
    }
}

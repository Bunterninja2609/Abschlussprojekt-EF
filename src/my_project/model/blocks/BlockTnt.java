package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.items.SandItem;
import my_project.model.textureContainers.BlockTextures;


public class BlockTnt extends Block{
    public BlockTnt(Vec2d gridPosition) {
        super(gridPosition, false);
        hitpoints = 10;
        drop = new SandItem(1);
        texture = BlockTextures.getTexture("tnt");
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(128,64,0, 255);
        drawTexture(drawTool);
    }
    @Override
    public void damage(double damage){
        explode(1000000000, 100000);
    }
    public void explode(int strength, int radius){
        getAdjacentBlock("up").damage(strength);
        getAdjacentBlock("down").damage(strength);
        getAdjacentBlock("left").damage(strength);
        getAdjacentBlock("right").damage(strength);
    }
}



package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;

public class Chunk {
    Vec2d gridPosition;
    Vec2d size;
    Block[][] blocks;
    public Chunk(Vec2d gridPosition, Vec2d size) {
        blocks = new Block[(int)(size.y)][(int)(size.x)];
        this.gridPosition = gridPosition;
        this.size = size;
    }
    public void draw(DrawTool drawTool) {
        for(Block[] row : blocks) {
            for (Block block : row) {
                block.draw(drawTool);
            }
        }
    }
}

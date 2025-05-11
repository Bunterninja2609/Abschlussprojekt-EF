package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;

public class Chunk {
    Vec2d position;
    Vec2d gridPosition;
    Vec2d size;
    Vec2d blockSize;
    Block[][] blocks;
    public Chunk(Vec2d gridPosition, Vec2d size) {
        blocks = new Block[(int)(size.y)][(int)(size.x)];
        this.gridPosition = gridPosition;
        this.size = size;
        this.position = new Vec2d(gridPosition.x * size.x, gridPosition.y * size.y);
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                blocks[x][y] = new Block();
            }
        }
    }
    public void draw(DrawTool drawTool) {
        for(Block[] row : blocks) {
            for (Block block : row) {
                block.draw(drawTool);
            }
        }
    }
}

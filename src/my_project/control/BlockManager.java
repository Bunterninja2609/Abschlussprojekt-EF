package my_project.control;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Terrain;

public class BlockManager {
    private Terrain terrain;
    public BlockManager() {
        terrain = new Terrain(new Vec2d(1000, 10), (int)(Math.random()* 10000));
        System.out.println("BlockRenderer created");
    }
    public void draw(DrawTool drawTool) {
        terrain.draw(drawTool);
    }

    public void update(double dt) {
        terrain.update(dt);
    }
    public void updateOnTick() {
        terrain.updateOnTick();
    }
    public Terrain getTerrain() {
        return terrain;
    }
}

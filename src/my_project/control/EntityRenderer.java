package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.model.entities.Entity;
import my_project.model.entities.*;

import java.util.ArrayList;

public class EntityRenderer {

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ViewController viewController;

    public EntityRenderer(ViewController viewController) {
        this.viewController = viewController;
        this.addEntity(new Player());
    }

    public void draw(DrawTool drawTool) {
        for (Entity entity : entities){
            //TODO Add Simulation Distance detection
            entity.draw(drawTool);
        }
    }

    public void update(double dt) {
        for (Entity entity : entities){
            //TODO Add Simulation Distance detection
            entity.update(dt);
        }
    }
    public void addEntity(Entity entity){
        entities.add(entity);
        if (entity instanceof Player){
            viewController.register(entity);
        }
    }
}
